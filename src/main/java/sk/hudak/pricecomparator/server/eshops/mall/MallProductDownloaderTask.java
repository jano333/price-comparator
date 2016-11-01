package sk.hudak.pricecomparator.server.eshops.mall;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 19. 7. 2016.
 */
public class MallProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.MALL;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new MallProductParser();
    }
}
