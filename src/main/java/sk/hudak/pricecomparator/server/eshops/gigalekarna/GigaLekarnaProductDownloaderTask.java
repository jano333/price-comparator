package sk.hudak.pricecomparator.server.eshops.gigalekarna;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 17. 7. 2016.
 */
public class GigaLekarnaProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.GIGA_LEKARNA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new GigaLekarnaProductParser();
    }
}
