package sk.hudak.pricecomparator.server.eshops.retrogeria;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 15. 9. 2016.
 */
public class RetrogeriaProductDownloaderTask extends AbstractProductDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.RETROGERIA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new RetrogeriaProductParser();
    }
}
