package sk.hudak.pricecomparator.server.eshops.metro;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 11. 7. 2016.
 */
public class MetroProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.METRO;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new MetroProductParser();
    }
}
