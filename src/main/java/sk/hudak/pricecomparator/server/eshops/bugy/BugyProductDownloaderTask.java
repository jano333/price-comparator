package sk.hudak.pricecomparator.server.eshops.bugy;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 29. 8. 2016.
 */
public class BugyProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.BUGY;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new BugyProductParser();
    }
}
