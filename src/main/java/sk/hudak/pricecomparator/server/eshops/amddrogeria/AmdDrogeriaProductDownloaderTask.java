package sk.hudak.pricecomparator.server.eshops.amddrogeria;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 3. 8. 2016.
 */
public class AmdDrogeriaProductDownloaderTask extends AbstractProductDownloaderTask {
    @Override
    public EshopType getEshopType() {
        return EshopType.AMD_DROGERIA;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new AmdDrogeriaProductParser();
    }
}
