package sk.hudak.pricecomparator.server.eshops.bambino;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 11. 8. 2016.
 */
public class BambinoProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.BAMBINO;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new BambinoProductParser();
    }
}
