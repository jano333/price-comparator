package sk.hudak.pricecomparator.server.eshops.feedo;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 12. 8. 2016.
 */
public class FeedoProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.FEEDO;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new FeedoProductParser();
    }
}
