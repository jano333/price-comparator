package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 14. 1. 2016.
 */
@Deprecated
public class K24ProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public K24ProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.K24;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new K24EshopProductParser();
    }
}
