package sk.hudak.pricecomparator.server.eshops.retrogeria;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 15. 9. 2016.
 */
public class RetrogeriaProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.RETROGERIA;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new RetrogeriaProductParser();
    }
}
