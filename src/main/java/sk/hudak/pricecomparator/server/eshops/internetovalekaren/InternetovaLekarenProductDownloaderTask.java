package sk.hudak.pricecomparator.server.eshops.internetovalekaren;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 23. 8. 2016.
 */
public class InternetovaLekarenProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.INTERNETOVA_LEKAREN;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new InternetovaLekarenProductParser();
    }
}
