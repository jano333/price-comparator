package sk.hudak.pricecomparator.server.eshops.pilulka;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 16. 7. 2016.
 */
public class PilulkaProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.PILULKA;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new PilulkaProductParser();
    }
}
