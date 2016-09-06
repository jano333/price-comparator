package sk.hudak.pricecomparator.server.eshops.babetkovo;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 5. 8. 2016.
 */
public class BabetkovoProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.BABETKOVO;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new BabetkovoProductParser();
    }
}
