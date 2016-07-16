package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.server.async.ng.impl.EshopTaskManagerImpNg;
import sk.hudak.pricecomparator.server.async.ng.tesco.TescoProductPictureDownloaderTaskNg;
import sk.hudak.pricecomparator.server.async.ng.tesco.TescoProductPriceUpdaterCallbackNg;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoProductDownloaderTask;

/**
 * Created by jan on 3. 7. 2016.
 */
public class NgStart {

    public static void main(String[] args) {

        EshopTaskManagerNg manager = new EshopTaskManagerImpNg(null);

        // Tesco
        manager.registerEshopTaskWithCallback(
                new TescoProductDownloaderTask(),
                new TescoProductPriceUpdaterCallbackNg());

        manager.registerEshopTask(new TescoProductPictureDownloaderTaskNg());

        // Metro
        //TODO

        // bud
        manager.startAllTasks();
        // alebo
//        manager.startTask(EshopType.TESCO);

    }
}
