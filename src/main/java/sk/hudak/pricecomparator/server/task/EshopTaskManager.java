package sk.hudak.pricecomparator.server.task;

import sk.hudak.jef.ssl.JefSslManager;
import sk.hudak.pricecomparator.client.utils.TaskManager;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskManagerNg;
import sk.hudak.pricecomparator.server.async.ng.impl.EshopTaskManagerImpNg;
import sk.hudak.pricecomparator.server.async.ng.tesco.TescoProductPictureDownloaderTaskNg;
import sk.hudak.pricecomparator.server.async.ng.tesco.TescoProductPriceUpdaterCallbackNg;
import sk.hudak.pricecomparator.server.eshops.alza.AlzaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.amddrogeria.AmdDrogeriaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.andrea.AndreaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.babetkovo.BabetkovoProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.mall.MallProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.metro.MetroProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.novalekaren.NovaLekarenProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.perinbaba.PerinbabaProductDownloaderTask;
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

        // A
        manager.registerEshopTask(new AlzaProductDownloaderTask());
        manager.registerEshopTask(new AmdDrogeriaProductDownloaderTask());
        manager.registerEshopTask(new AndreaProductDownloaderTask());

        // B
        manager.registerEshopTask(new BabetkovoProductDownloaderTask());

        // C

        // D

        // E

        // F

        // G
        // zakomentovanie koli tomu ze dava Kcs nie Eur
//        manager.registerEshopTask(new GigaLekarnaProductDownloaderTask());

        // H

        // M
        manager.registerEshopTask(new MallProductDownloaderTask());
        manager.registerEshopTask(new MetroProductDownloaderTask());
        // N
        manager.registerEshopTask(new NovaLekarenProductDownloaderTask());
        // P
        manager.registerEshopTask(new PerinbabaProductDownloaderTask());
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
