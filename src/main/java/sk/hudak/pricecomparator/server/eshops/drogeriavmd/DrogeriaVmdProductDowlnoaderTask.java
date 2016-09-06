package sk.hudak.pricecomparator.server.eshops.drogeriavmd;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 2. 9. 2016.
 */
public class DrogeriaVmdProductDowlnoaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.DROGERIA_VMD;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new DrogeriaVmdProductParser();
    }
}
