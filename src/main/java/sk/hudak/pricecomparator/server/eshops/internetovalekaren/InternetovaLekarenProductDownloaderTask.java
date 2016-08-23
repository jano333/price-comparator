package sk.hudak.pricecomparator.server.eshops.internetovalekaren;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 23. 8. 2016.
 */
public class InternetovaLekarenProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.INTERNETOVA_LEKAREN;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new InternetovaLekarenProductParser();
    }
}
