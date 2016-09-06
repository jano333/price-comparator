package sk.hudak.pricecomparator.server.eshops.esodregeria;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductParser;
import sk.hudak.pricecomparator.server.tobedeleted.EsoDrogeriaProductParser;

/**
 * Created by jan on 29. 5. 2016.
 */
@Deprecated
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
