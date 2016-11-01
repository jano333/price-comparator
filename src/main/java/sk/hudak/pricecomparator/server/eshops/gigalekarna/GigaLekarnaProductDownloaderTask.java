package sk.hudak.pricecomparator.server.eshops.gigalekarna;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 17. 7. 2016.
 */
public class GigaLekarnaProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.GIGA_LEKARNA;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new GigaLekarnaProductParser();
    }
}
