package sk.hudak.pricecomparator.server.eshops.feedo;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 12. 8. 2016.
 */
public class FeedoProductDownloaderTask extends AbstractProductDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.FEEDO;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new FeedoProductParser();
    }
}