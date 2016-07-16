package sk.hudak.pricecomparator.server.async.ng.impl;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskCallbackNg;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskManagerNg;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskNg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * Created by jan on 3. 7. 2016.
 */
public class EshopTaskManagerImpNg implements EshopTaskManagerNg {

    private Map<EshopType, List<TaskWithCallback>> registeredTasks = new HashMap<>(EshopType.values().length);

    private PriceComparatorService service;

    public EshopTaskManagerImpNg(PriceComparatorService service) {
        this.service = service;
    }

    /**
     * zoznam taskov ktore uz boli spustene
     * TODO kedy bydem odoberat z runningTasks danej mapy?
     */
    private Map<EshopType, Future<?>> runningTasks = new HashMap<>();

    @Override
    public void registerEshopTask(EshopTaskNg eshopTaskNg) {
        registerEshopTaskWithCallback(eshopTaskNg, null);
    }

    @Override
    public void registerEshopTaskWithCallback(EshopTaskNg eshopTaskNg, EshopTaskCallbackNg callback) {
        //TODO validate input params
        if (!registeredTasks.containsKey(eshopTaskNg.getEshopType())) {
            registeredTasks.put(eshopTaskNg.getEshopType(), new ArrayList<TaskWithCallback>(1));
        }
        registeredTasks.get(eshopTaskNg.getEshopType()).add(new TaskWithCallback(eshopTaskNg, callback));
    }

    @Override
    public void startAllTasks() {
        for (EshopType eshopType : registeredTasks.keySet()) {
            startTask(eshopType);
        }
    }

    @Override
    public void startTask(final EshopType eshopType) {
        //TODO validate input params
        //TODO overit ci nebezi

        ThreadFactory tf = new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, eshopType.name());
            }
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor(tf);

        List<TaskWithCallback> taskWithCallbacks = registeredTasks.get(eshopType);
        EshopInternalRunnableNg internalTask = new EshopInternalRunnableNg(service, eshopType, taskWithCallbacks);
        Future<?> future = executorService.submit(internalTask);

        runningTasks.put(eshopType, future);

        executorService.shutdown();
    }

    public static class TaskWithCallback {
        private EshopTaskNg task;
        private EshopTaskCallbackNg callback;

        public TaskWithCallback(EshopTaskNg task, EshopTaskCallbackNg callback) {
            this.task = task;
            this.callback = callback;
        }

        public EshopTaskNg getTask() {
            return task;
        }

        public EshopTaskCallbackNg getCallback() {
            return callback;
        }

        public boolean hasCallback() {
            return callback != null;
        }
    }
}
