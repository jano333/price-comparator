package sk.hudak.pricecomparator.server.eshops.market24;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 14. 9. 2016.
 */
public class Market24ProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.MARKET24;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new Market24ProductParser();
    }
}
