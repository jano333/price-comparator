package sk.hudak.pricecomparator.server.task;

import sk.hudak.jef.ssl.JefSslManager;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.async.ng.EshopTaskManagerNg;
import sk.hudak.pricecomparator.server.async.ng.impl.EshopTaskManagerImpNg;
import sk.hudak.pricecomparator.server.async.ng.tesco.TescoProductPictureDownloaderTaskNg;
import sk.hudak.pricecomparator.server.async.ng.tesco.TescoProductPriceUpdaterCallbackNg;
import sk.hudak.pricecomparator.server.eshops.alza.AlzaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.amddrogeria.AmdDrogeriaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.andrea.AndreaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.babetkovo.BabetkovoProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.bambino.BambinoProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.belmedika.BelmedikaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.bugy.BugyProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.ckdmarket.CkdMarketProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.drmax.DrMaxProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.drogeriavmd.DrogeriaVmdProductDowlnoaderTask;
import sk.hudak.pricecomparator.server.eshops.esodregeria.EsoDrogeriaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.feedo.FeedoProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.hej.HejProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.internetovalekaren.InternetovaLekarenProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.lekarenbella.LekarenBellaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.maderna.MadernaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.mall.MallProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.market24.Market24ProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.metro.MetroProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.mojalekaren.MojalekarenProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.novalekaren.NovaLekarenProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.obchodnydom.ObchodnyDomProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.perinbaba.PerinbabaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.pilulka.PilulkaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.retrogeria.RetrogeriaProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.semistor.SemiltonProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.shoppie.ShoppieProductDownloaderTask;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoProductDownloaderTask;
import sk.hudak.pricecomparator.server.tobedeleted.TaskManager;

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
        manager.registerEshopTask(new BambinoProductDownloaderTask());
        manager.registerEshopTask(new BelmedikaProductDownloaderTask());
        manager.registerEshopTask(new BugyProductDownloaderTask());
        // C
        manager.registerEshopTask(new CkdMarketProductDownloaderTask());
        // D
        manager.registerEshopTask(new DrMaxProductDownloaderTask());
        manager.registerEshopTask(new DrogeriaVmdProductDowlnoaderTask());
        // E
        manager.registerEshopTask(new EsoDrogeriaProductDownloaderTask());
        // F
        manager.registerEshopTask(new FeedoProductDownloaderTask());
        // G
        // TODO zakomentovanie koli tomu ze dava Kcs nie Eur
//        manager.registerEshopTask(new GigaLekarnaProductDownloaderTask());
        // H
        manager.registerEshopTask(new HejProductDownloaderTask());
        // TODO hravozdravo teraz to je https://www.zdravyvyvoj.sk/
        // I
        manager.registerEshopTask(new InternetovaLekarenProductDownloaderTask());
        // K
        // TODO je len jeden produkt aj ten je zla url:
        // http://www.k24.sk/product/288078/Elektronika/Sport_a_hobby/Pampers_Premium_Care_VP_Mini_80_ks_.html
//        manager.registerEshopTask(new K24ProductDownloaderTask());
        // L
        manager.registerEshopTask(new LekarenBellaProductDownloaderTask());
        // M
        manager.registerEshopTask(new MadernaProductDownloaderTask());
        manager.registerEshopTask(new MallProductDownloaderTask());
        manager.registerEshopTask(new Market24ProductDownloaderTask());
        manager.registerEshopTask(new MetroProductDownloaderTask());
        manager.registerEshopTask(new MojalekarenProductDownloaderTask());
        // N
        manager.registerEshopTask(new NovaLekarenProductDownloaderTask());
        // O
        manager.registerEshopTask(new ObchodnyDomProductDownloaderTask());
        // P
        manager.registerEshopTask(new PerinbabaProductDownloaderTask());
        manager.registerEshopTask(new PilulkaProductDownloaderTask());
        // R
        manager.registerEshopTask(new RetrogeriaProductDownloaderTask());
        // S
        manager.registerEshopTask(new SemiltonProductDownloaderTask());
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
