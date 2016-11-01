package sk.hudak.pricecomparator.server.eshops.semistor;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 16. 7. 2016.
 */
public class SemiltonProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.SEMILTON;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new SemiltonProductParser();
    }
}
