package sk.hudak.pricecomparator.server.eshops.esodregeria;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductParser;

/**
 * Created by jan on 29. 5. 2016.
 */
public class EsoDrogeriaProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public EsoDrogeriaProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.ESO_DROGERIA;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new EsoDrogeriaProductParser();
    }
}
