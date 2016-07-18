package sk.hudak.pricecomparator.server.eshops.gigalekarna;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 17. 7. 2016.
 */
public class GigaLekarnaProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.GIGA_LEKARNA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new GigaLekarnaProductParser();
    }
}
