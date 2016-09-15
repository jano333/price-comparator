package sk.hudak.pricecomparator.server.eshops.obchodnydom;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 15. 9. 2016.
 */
public class ObchodnyDomProductDownloaderTask extends AbstractProductDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.OBCHODNY_DOM;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new ObchodnyDomProductParser();
    }
}
