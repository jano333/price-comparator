package sk.hudak.pricecomparator.server.eshops.hej;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 23. 8. 2016.
 */
public class HejProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.HEJ;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new HejProductParser();
    }
}
