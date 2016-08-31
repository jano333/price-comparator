package sk.hudak.pricecomparator.server.eshops.ckdmarket;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 31. 8. 2016.
 */
public class CkdMarketProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.CKD_MARKET;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new CkdMarketProductParser();
    }
}
