package sk.hudak.pricecomparator.server.eshops.babetkovo;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 5. 8. 2016.
 */
public class BabetkovoProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.BABETKOVO;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new BabetkovoProductParser();
    }
}
