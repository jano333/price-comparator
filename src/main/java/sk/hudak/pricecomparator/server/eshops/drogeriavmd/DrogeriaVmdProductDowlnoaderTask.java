package sk.hudak.pricecomparator.server.eshops.drogeriavmd;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 2. 9. 2016.
 */
public class DrogeriaVmdProductDowlnoaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.DROGERIA_VMD;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new DrogeriaVmdProductParser();
    }
}
