package sk.hudak.pricecomparator.server.eshops.perinbaba;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 18. 7. 2016.
 */
public class PerinbabaProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.PERINBABA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new PerinbabaProductParser();
    }
}
