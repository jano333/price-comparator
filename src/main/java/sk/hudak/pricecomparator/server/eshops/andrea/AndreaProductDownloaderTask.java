package sk.hudak.pricecomparator.server.eshops.andrea;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 28. 7. 2016.
 */
public class AndreaProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.ANDREA_ESHOP;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new AndreaProductParser();
    }
}
