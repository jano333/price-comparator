package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jan on 13. 1. 2016.
 */
@Deprecated
public class TaskManager {

    private PriceComparatorService service;
    private List<EshopProductInfoDownloaderTask> taskList;

    public void initTaks(PriceComparatorService service) {
        this.service = service;

        taskList = new ArrayList<>();

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
//        ExecutorService executorService = Executors.newFixedThreadPool(1);

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
