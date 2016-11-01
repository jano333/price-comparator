package sk.hudak.pricecomparator.server.async.ng.impl;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.async.ng.EshopTask;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskCallback;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskManager;

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
public class EshopTaskManagerImp implements EshopTaskManager {

    private Map<EshopType, List<TaskWithCallback>> registeredTasks = new HashMap<>(EshopType.values().length);

    private PriceComparatorService service;

    public EshopTaskManagerImp(PriceComparatorService service) {
        this.service = service;
    }

    /**
     * zoznam taskov ktore uz boli spustene
     * TODO kedy bydem odoberat z runningTasks danej mapy?
     */
    private Map<EshopType, Future<?>> runningTasks = new HashMap<>();

    @Override
    public void registerEshopTask(EshopTask eshopTask) {
        registerEshopTaskWithCallback(eshopTask, null);
    }

    @Override
    public void registerEshopTaskWithCallback(EshopTask eshopTask, EshopTaskCallback callback) {
        //TODO validate input params
        if (!registeredTasks.containsKey(eshopTask.getEshopType())) {
            registeredTasks.put(eshopTask.getEshopType(), new ArrayList<TaskWithCallback>(1));
        }
        registeredTasks.get(eshopTask.getEshopType()).add(new TaskWithCallback(eshopTask, callback));
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
        EshopInternalRunnable internalTask = new EshopInternalRunnable(service, eshopType, taskWithCallbacks);
        Future<?> future = executorService.submit(internalTask);

        runningTasks.put(eshopType, future);

        executorService.shutdown();
    }

    public static class TaskWithCallback {
        private EshopTask task;
        private EshopTaskCallback callback;

        public TaskWithCallback(EshopTask task, EshopTaskCallback callback) {
            this.task = task;
            this.callback = callback;
        }

        public EshopTask getTask() {
            return task;
        }

        public EshopTaskCallback getCallback() {
            return callback;
        }

        public boolean hasCallback() {
            return callback != null;
        }
    }
}
