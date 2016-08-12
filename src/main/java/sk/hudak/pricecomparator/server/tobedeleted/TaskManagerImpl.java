package sk.hudak.pricecomparator.server.tobedeleted;


import sk.hudak.pricecomparator.middle.canonical.EshopType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * Poradie:
 * <ol>
 * <li> zaregistrovanie eshop tasku: {@link #registerTask(EshopType, EshopSimpleTask)},
 * {@link #registerTasks(EshopType, List)}</li>
 * <li> spustenie eshop tasku: {@link #startTask(EshopType)}</li>
 * </ol>
 * <p/>
 * <p/>
 * <p/>
 * Created by hudak on 10.05.2016.
 */
@Deprecated
public class TaskManagerImpl implements AsyncEshopTaskManager {

    /**
     * zoznam zaregistrovanych taskov per eshop
     */
    private Map<EshopType, List<EshopSimpleTask>> registeredTasks = new HashMap<>(EshopType.values().length);


    /**
     * zoznam taskov ktore uz boli spustene
     * TODO kedy bydem odoberat z runningTasks danej mapy?
     */
    private Map<EshopType, Future<?>> runningTasks = new HashMap<>();

    @Override
    public void registerTask(EshopType type, EshopSimpleTask task) {
        validateEshopType(type);
        validateEshopTask(task);

        List<EshopSimpleTask> tasks = new ArrayList<>(1);
        tasks.add(task);

        internal_registerTasks(type, tasks);
    }

    @Override
    public void registerTasks(EshopType type, List<EshopSimpleTask> tasks) {
        validateEshopType(type);
        validateEshopTasks(tasks);

        internal_registerTasks(type, tasks);
    }

    @Override
    public boolean isTaskRegistred(EshopType eshopType) {
        validateEshopType(eshopType);

        synchronized (this) {
            return registeredTasks.containsKey(eshopType);
        }
    }


    /**
     * @param eshopType
     * @param waitUntilFinishIfRunning true, ak sa ma pockat na ukoncenie daneho tasku ak bezi, false ak ma byt preruseny ak bezi
     * @see #isTaskRunning(EshopType)
     * @see #isAnyTaskRunning()
     */
    public void unregisterTask(EshopType eshopType, boolean waitUntilFinishIfRunning) {
        //TODO impl
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void startTask(EshopType eshopType) {
        validateEshopType(eshopType);

        synchronized (this) {

            // musi byt uz zaregistrovany
            checkIfTypeIsAllreadyRegistered(eshopType);

            internal_startTask(eshopType);

        }
    }

    @Override
    public void startTasks() {
        synchronized (this) {
            for (EshopType registeredTask : registeredTasks.keySet()) {
                internal_startTask(registeredTask);
            }
        }
    }

    @Override
    public boolean isTaskRunning(EshopType type) {
        validateEshopType(type);

        synchronized (this) {
            checkIfTypeIsAllreadyRegistered(type);
            return !runningTasks.get(type).isDone();
        }
    }


    @Override
    public boolean isAnyTaskRunning() {
        synchronized (this) {
            for (Future<?> future : runningTasks.values()) {
                if (!future.isDone()) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void stopTask(EshopType type) {
        validateEshopType(type);

        synchronized (this) {

            checkIfTypeIsAllreadyRegistered(type);

            internal_stopTask(type);
        }
    }

    /**
     * Musi byt volana v synchronized bloku.
     *
     * @param type
     */
    private void internal_stopTask(EshopType type) {
        // ak nebol este nikdy spusteny, tak nerob nic
        if (!runningTasks.containsKey(type)) {
            return;
        }

        Future<?> future = runningTasks.get(type);

        // ak uz skoncil, tak nerob nic.
        if (future.isDone()) {
            return;
        }

        boolean cancel = future.cancel(true);
        if (!cancel) {
            LogUtil.log("nepodarilo sa prerusit task " + type);
        } else {
            LogUtil.log("uspesne preruseny task " + type);
        }
    }

    @Override
    public void stopTasks() {
        //ak bol pusteny musi byt v zozname zaregistrovanych najprv

        for (EshopType registredTask : registeredTasks.keySet()) {
            //TODO nevolat public metody
            stopTask(registredTask);
        }
    }

    //    /**
//     * Zaregistruje task, ak este nie je registorvany.
//     *
//     * @param eshopType
//     */
//    @Deprecated
//    @Override
//    public void registerTask(EshopType eshopType) {
//        validateEshopType(eshopType);
//
//        synchronized (this) {
//
//            // ak uz je registrovany, tak koncim
//            if (old_registeredTasks.contains(eshopType)) {
//                return;
//            }
//            // inak, pridam do zoznamu zaregistrovanych
//            old_registeredTasks.add(eshopType);
//
//        }
//    }
//
//
//    @Deprecated
//    @Override
//    public void registerTasks(List<EshopType> eshopTypes) {
//        validateEshopTypes(eshopTypes);
//
//        synchronized (this) {
//
//            for (EshopType eshopType : eshopTypes) {
//                // ak uz je registrovany, tak chod na dalsi
//                if (old_registeredTasks.contains(eshopType)) {
//                    continue;
//                }
//                // inak, pridam do zoznamu zaregistrovanych
//                old_registeredTasks.add(eshopType);


    private void internal_registerTasks(EshopType eshopType, List<EshopSimpleTask> tasks) {

        synchronized (this) {

            // ak uz je registrovany, nerob nic
            if (registeredTasks.containsKey(eshopType)) {
                return;
            }

            // pridam do zoznamu zaregistrovanych
            registeredTasks.put(eshopType, tasks);
        }
    }


    private void validateEshopTasks(List<EshopSimpleTask> tasks) {
        //TODO
    }


    private void validateEshopTask(EshopSimpleTask task) {
        //TODO Not null
    }


    /**
     * Musi byt volane v synchronized bloku.
     *
     * @param eshopType
     */
    private void internal_startTask(final EshopType eshopType) {

        // ak task pre dany eshop bezi, tak ho najprv zastavim
        //TODO nevolat public metody !!!!
        stopTask(eshopType);

        ThreadFactory tf = new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, eshopType.name());
            }
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor(tf);

        Future<?> future = executorService.submit(new EshopMainTaskImpl(eshopType, registeredTasks.get(eshopType)));

        runningTasks.put(eshopType, future);

        executorService.shutdown();

    }


//            }


    private void checkIfTypeIsAllreadyRegistered(EshopType type) {
        if (!registeredTasks.containsKey(type)) {
            throw new RuntimeException("None task for eshop " + type + " is registred.");
        }
    }

    // --------------------

//


//        }

    //-----------------

    /**
     * zoznam zaregistrovanych taskov
     */
    private List<EshopType> old_registeredTasks = new ArrayList<>(EshopType.values().length);

//    }

    /**
     * Musi byt volane v synchronized bloku.
     *
     * @param eshopType
     * @return true, ak dany task je uz registrovany
     */
    @Deprecated
    private boolean internal_isTaskAllreadyRegistred(EshopType eshopType) {
        return old_registeredTasks.contains(eshopType);
    }

    private void validateEshopType(EshopType type) {
        if (type == null) {
            throw new RuntimeException("Eshop type is null");
        }
    }

    private void validateEshopTypes(List<EshopType> eshopTypes) {
        if (eshopTypes == null || eshopTypes.isEmpty()) {
            throw new RuntimeException("Eshop types is null or empty.");
        }
        //FIXME este ci kazdy jeden prvok nie je null
    }
}
