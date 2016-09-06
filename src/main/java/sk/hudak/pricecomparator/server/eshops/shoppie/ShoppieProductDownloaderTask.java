package sk.hudak.pricecomparator.server.eshops.shoppie;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 16. 7. 2016.
 */
public class ShoppieProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.SHOPPIE;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new ShoppieProductParser();
    }
}
