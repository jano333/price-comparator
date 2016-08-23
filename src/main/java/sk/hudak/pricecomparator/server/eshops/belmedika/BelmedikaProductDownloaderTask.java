package sk.hudak.pricecomparator.server.eshops.belmedika;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 20. 8. 2016.
 */
public class BelmedikaProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.BEL_MEDIKA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new BelmedikaProductParser();
    }
}
