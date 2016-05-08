package sk.hudak.pricecomparator.client.utils;

import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.alza.AlzaProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.amddrogeria.AmdDrogeriaProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.andrea.AndreaEshopProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.bambino.BambinoProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.drmax.DrMaxProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.feedo.FeedoProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.hej.HejProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.internetovalekaren.InternetovaLekarenProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.k24.K24ProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.mall.MallProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.metro.MetroProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.perinbaba.PerinbabaProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.pilulka.PilulkaProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.semistor.SemistorProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoProductInfoDownloaderTask;

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

    public void initTaks(PriceComparatorService service) {
        this.service = service;

        taskList = new ArrayList<>();
        taskList.add(new AlzaProductInfoDownloaderTask(service));
        taskList.add(new AndreaEshopProductInfoDownloaderTask(service));
        taskList.add(new AmdDrogeriaProductInfoDownloaderTask(service));
        taskList.add(new BambinoProductInfoDownloaderTask(service));
        taskList.add(new DrMaxProductInfoDownloaderTask(service));
        taskList.add(new FeedoProductInfoDownloaderTask(service));
        taskList.add(new HejProductInfoDownloaderTask(service));
        // hravo zdravo je nedostupne
//        taskList.add(new HravoZdravoProductInfoDownloaderTask(service));
        taskList.add(new InternetovaLekarenProductInfoDownloaderTask(service));
        taskList.add(new K24ProductInfoDownloaderTask(service));
        taskList.add(new MallProductInfoDownloaderTask(service));
        taskList.add(new MetroProductInfoDownloaderTask(service));
        taskList.add(new PerinbabaProductInfoDownloaderTask(service));
        taskList.add(new PilulkaProductInfoDownloaderTask(service));
        taskList.add(new SemistorProductInfoDownloaderTask(service));
        taskList.add(new TescoProductInfoDownloaderTask(service));
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
