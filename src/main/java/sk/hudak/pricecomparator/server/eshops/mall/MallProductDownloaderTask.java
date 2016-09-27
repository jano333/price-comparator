package sk.hudak.pricecomparator.server.eshops.mall;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 19. 7. 2016.
 */
public class MallProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.MALL;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new MallProductParser();
    }
}