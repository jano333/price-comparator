package sk.hudak.pricecomparator.server.eshops.babetkovo;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 5. 8. 2016.
 */
public class BabetkovoProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.BABETKOVO;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new BabetkovoProductParser();
    }
}
