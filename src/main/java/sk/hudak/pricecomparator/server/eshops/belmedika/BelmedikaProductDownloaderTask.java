package sk.hudak.pricecomparator.server.eshops.belmedika;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 20. 8. 2016.
 */
public class BelmedikaProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.BEL_MEDIKA;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new BelmedikaProductParser();
    }
}
