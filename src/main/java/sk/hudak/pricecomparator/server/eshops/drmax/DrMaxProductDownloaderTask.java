package sk.hudak.pricecomparator.server.eshops.drmax;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 23. 8. 2016.
 */
public class DrMaxProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.DR_MAX;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new DrMaxProductParser();
    }
}
