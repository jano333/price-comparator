package sk.hudak.pricecomparator.server.eshops.lekarenbella;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 22. 8. 2016.
 */
public class LekarenBellaProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.LEKAREN_BELLA;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new LekarenBellaProductParser();
    }
}
