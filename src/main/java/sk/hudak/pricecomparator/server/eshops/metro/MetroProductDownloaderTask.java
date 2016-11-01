package sk.hudak.pricecomparator.server.eshops.metro;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 11. 7. 2016.
 */
public class MetroProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.METRO;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new MetroProductParser();
    }
}
