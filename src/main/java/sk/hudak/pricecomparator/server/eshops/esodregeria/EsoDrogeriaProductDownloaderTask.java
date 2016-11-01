package sk.hudak.pricecomparator.server.eshops.esodregeria;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.async.ng.EshopProductParser;
import sk.hudak.pricecomparator.server.async.ng.impl.AbstractProductDownloaderTask;

/**
 * Created by jan on 6. 9. 2016.
 */
public class EsoDrogeriaProductDownloaderTask extends AbstractProductDownloaderTask {

    @Override
    public EshopType getEshopType() {
        return EshopType.ESO_DROGERIA;
    }

    @Override
    protected EshopProductParser createEshopProductParser() {
        return new EsoDrogeriaProductParser();
    }
}
