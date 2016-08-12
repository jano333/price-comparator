package sk.hudak.pricecomparator.server.eshops.feedo;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductParser;

/**
 * Created by jan on 13. 1. 2016.
 */
@Deprecated
public class FeedoProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public FeedoProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.FEEDO;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new FeedoEshopProductParser();
    }
}
