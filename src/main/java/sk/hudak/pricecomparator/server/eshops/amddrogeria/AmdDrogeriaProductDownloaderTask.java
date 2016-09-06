package sk.hudak.pricecomparator.server.eshops.amddrogeria;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 3. 8. 2016.
 */
public class AmdDrogeriaProductDownloaderTask extends AbstractProductDownloaderTaskNg {
    @Override
    public EshopType getEshopType() {
        return EshopType.AMD_DROGERIA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new AmdDrogeriaProductParser();
    }
}
