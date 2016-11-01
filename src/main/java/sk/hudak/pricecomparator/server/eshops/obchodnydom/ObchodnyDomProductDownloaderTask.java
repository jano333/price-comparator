package sk.hudak.pricecomparator.server.eshops.obchodnydom;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 15. 9. 2016.
 */
public class ObchodnyDomProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.OBCHODNY_DOM;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new ObchodnyDomProductParser();
    }
}
