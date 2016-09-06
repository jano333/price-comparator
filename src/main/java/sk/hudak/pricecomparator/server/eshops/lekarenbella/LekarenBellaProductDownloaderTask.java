package sk.hudak.pricecomparator.server.eshops.lekarenbella;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 22. 8. 2016.
 */
public class LekarenBellaProductDownloaderTask extends AbstractProductDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.LEKAREN_BELLA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new LekarenBellaProductParser();
    }
}
