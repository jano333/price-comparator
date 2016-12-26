package sk.hudak.pricecomparator.server.eshops.bambino;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 11. 8. 2016.
 */
@Deprecated
public class BambinoProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.BAMBINO;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new BambinoProductParser();
    }
}
