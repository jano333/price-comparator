package sk.hudak.pricecomparator.client.utils;

import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.eshops.esodregeria.EsoDrogeriaProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.k24.K24ProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.tobedeleted.*;

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

//        taskList.add(new AlzaProductInfoDownloaderTask(service));
//        taskList.add(new AndreaEshopProductInfoDownloaderTask(service));
//        taskList.add(new AmdDrogeriaProductInfoDownloaderTask(service));

//        taskList.add(new BabetkovoProductInfoDownloaderTask(service));
//        taskList.add(new BambinoProductInfoDownloaderTask(service));
        taskList.add(new BelmedikaEshopProductInfoDownloaderTask(service));
        taskList.add(new BugyEshopProductInfoDownloaderTask(service));

        taskList.add(new CkdMarketProductInfoDownloaderTask(service));

        taskList.add(new DrMaxProductInfoDownloaderTask(service));
        taskList.add(new DrogeriaVmdProductInfoDowlnoaderTask(service));

        taskList.add(new EsoDrogeriaProductInfoDownloaderTask(service));

//        taskList.add(new FeedoProductInfoDownloaderTask(service));

        taskList.add(new HejProductInfoDownloaderTask(service));

        // hravo zdravo je nedostupne
//        taskList.add(new HravoZdravoProductInfoDownloaderTask(service));

        taskList.add(new InternetovaLekarenProductInfoDownloaderTask(service));

        taskList.add(new K24ProductInfoDownloaderTask(service));

        taskList.add(new LekarenBellaProductInfoDownloaderTask(service));

//        taskList.add(new MallProductInfoDownloaderTask(service));
//        taskList.add(new MetroProductInfoDownloaderTask(service));

//        taskList.add(new NovaLekarenProductInfoDownloaderTask(service));

//        taskList.add(new PerinbabaProductInfoDownloaderTask(service));
//        taskList.add(new PilulkaProductInfoDownloaderTask(service));

//        taskList.add(new SemistorProductInfoDownloaderTask(service));
//        taskList.add(new ShoppieProductInfoDownloaderTask(service));


//        taskList.add(new TescoProductInfoDownloaderTask(service));
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
