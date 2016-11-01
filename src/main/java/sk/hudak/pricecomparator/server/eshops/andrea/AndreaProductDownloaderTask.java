package sk.hudak.pricecomparator.server.eshops.andrea;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 28. 7. 2016.
 */
public class AndreaProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.ANDREA_ESHOP;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new AndreaProductParser();
    }
}
