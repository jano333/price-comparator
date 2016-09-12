package sk.hudak.pricecomparator.server.eshops.maderna;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 11. 9. 2016.
 */
public class MadernaProductDownloaderTask extends AbstractProductDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.MADERNA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new MadernaProductParser();
    }
}
