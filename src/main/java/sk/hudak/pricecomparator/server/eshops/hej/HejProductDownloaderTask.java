package sk.hudak.pricecomparator.server.eshops.hej;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 23. 8. 2016.
 */
public class HejProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.HEJ;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new HejProductParser();
    }
}
