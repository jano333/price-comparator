package sk.hudak.pricecomparator.server.eshops.ckdmarket;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 31. 8. 2016.
 */
public class CkdMarketProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.CKD_MARKET;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new CkdMarketProductParser();
    }
}
