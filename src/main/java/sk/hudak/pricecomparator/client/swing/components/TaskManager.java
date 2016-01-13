package sk.hudak.pricecomparator.client.swing.components;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.tasks.EshopProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.tasks.MetroProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.tasks.TescoProductInfoDowlnoaderTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jan on 13. 1. 2016.
 */
public class TaskManager {

    private PriceComparatorService service;
    private List<EshopProductInfoDownloaderTask> taskList;

    public void initTaks() {
        service = ServiceLocator.getService();

        taskList = new ArrayList<>();
        taskList.add(new TescoProductInfoDowlnoaderTask(service));
        taskList.add(new MetroProductInfoDownloaderTask(service));
    }

    public void startDownloading() {
        //FIXME doriesit ako nastavit nazov pre thread name podla nazvu eshopu pre ktory je spustany...
//        ThreadFactory tf = new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                EshopProductInfoDownloaderTask task = (EshopProductInfoDownloaderTask) r;
//                return new Thread(task, task.getEshopType().name());
//            }
//        };
        ExecutorService executorService = Executors.newFixedThreadPool(taskList.size()/*, tf*/);

        for (EshopProductInfoDownloaderTask infoDownloaderTask : taskList) {
            executorService.execute(infoDownloaderTask);
        }
        executorService.shutdown();
    }

    public void stopDownloading() {
        for (EshopProductInfoDownloaderTask infoDownloaderTask : taskList) {
            infoDownloaderTask.stopTask();
        }
    }
}
