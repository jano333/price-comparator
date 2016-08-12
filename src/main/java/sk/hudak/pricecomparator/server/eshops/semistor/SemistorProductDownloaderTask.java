package sk.hudak.pricecomparator.server.eshops.semistor;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductInfoDownloaderTaskNg;

/**
 * Created by jan on 16. 7. 2016.
 */
public class SemistorProductDownloaderTask extends AbstractProductInfoDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.SEMISTOR;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new SemistorProductParser();
    }
}
