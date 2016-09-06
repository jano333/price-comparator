package sk.hudak.pricecomparator.server.eshops.bugy;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 29. 8. 2016.
 */
public class BugyProductDownloaderTask extends AbstractProductDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.BUGY;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new BugyProductParser();
    }
}
