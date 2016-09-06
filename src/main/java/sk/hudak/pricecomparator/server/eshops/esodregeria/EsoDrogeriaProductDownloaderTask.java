package sk.hudak.pricecomparator.server.eshops.esodregeria;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParserNg;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTaskNg;

/**
 * Created by jan on 6. 9. 2016.
 */
public class EsoDrogeriaProductDownloaderTask extends AbstractProductDownloaderTaskNg {

    @Override
    public EshopType getEshopType() {
        return EshopType.ESO_DROGERIA;
    }

    @Override
    protected EshopProductParserNg createEshopProductParser() {
        return new EsoDrogeriaProductParser();
    }
}
