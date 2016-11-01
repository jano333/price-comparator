package sk.hudak.pricecomparator.server.eshops.maderna;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 11. 9. 2016.
 */
public class MadernaProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.MADERNA;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new MadernaProductParser();
    }
}
