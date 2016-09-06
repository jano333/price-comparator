package sk.hudak.pricecomparator.server.eshops.bambino;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 11. 8. 2016.
 */
public class BambinoProductDownloaderTask extends AbstractProductDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.BAMBINO;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new BambinoProductParser();
    }
}
