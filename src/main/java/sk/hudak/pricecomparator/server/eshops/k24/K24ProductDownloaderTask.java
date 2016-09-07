package sk.hudak.pricecomparator.server.eshops.k24;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 7. 9. 2016.
 */
public class K24ProductDownloaderTask extends AbstractProductDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.K24;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new K24ProductParser();
    }
}
