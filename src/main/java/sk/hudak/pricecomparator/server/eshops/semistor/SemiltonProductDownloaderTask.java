package sk.hudak.pricecomparator.server.eshops.semistor;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 16. 7. 2016.
 */
public class SemiltonProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.SEMILTON;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new SemiltonProductParser();
    }
}
