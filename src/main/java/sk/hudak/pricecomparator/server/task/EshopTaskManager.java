package sk.hudak.pricecomparator.server.task;

import sk.hudak.jef.ssl.JefSslManager;
import sk.hudak.pricecomparator.client.utils.TaskManager;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskManagerNg;
import sk.hudak.pricecomparator.server.async.ng.impl.EshopTaskManagerImpNg;
import sk.hudak.pricecomparator.server.async.ng.tesco.TescoProductPictureDownloaderTaskNg;
import sk.hudak.pricecomparator.server.async.ng.tesco.TescoProductPriceUpdaterCallbackNg;
import sk.hudak.pricecomparator.server.eshops.metro.MetroProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.pilulka.PilulkaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.semistor.SemistorProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.shoppie.ShoppieProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoProductDownloaderTask;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 5. 4. 2016.
 */
@Named
public class EshopTaskManager {

    @Inject
    private PriceComparatorService service;

    private TaskManager taskManager;

    @PostConstruct
    public void init() {
        System.out.println("init call");

        // custom ssl to ignore SSL check for specific domains
        JefSslManager.getInstance().init();

//        taskManager = new TaskManager();
//        taskManager.initTaks(service);
//        taskManager.startDownloading();

        EshopTaskManagerNg manager = new EshopTaskManagerImpNg(service);

        // M
        manager.registerEshopTask(new MetroProductDownloaderTask());

        // P
        manager.registerEshopTask(new PilulkaProductDownloaderTask());

        // S
        manager.registerEshopTask(new SemistorProductDownloaderTask());
        manager.registerEshopTask(new ShoppieProductDownloaderTask());

        // T
        manager.registerEshopTaskWithCallback(
                new TescoProductDownloaderTask(),
                new TescoProductPriceUpdaterCallbackNg());
        manager.registerEshopTask(new TescoProductPictureDownloaderTaskNg());

        manager.startAllTasks();
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy call");

        taskManager.stopDownloading();
    }
}
