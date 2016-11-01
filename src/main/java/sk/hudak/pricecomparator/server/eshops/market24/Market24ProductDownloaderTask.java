package sk.hudak.pricecomparator.server.eshops.market24;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 14. 9. 2016.
 */
public class Market24ProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.MARKET24;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new Market24ProductParser();
    }
}
