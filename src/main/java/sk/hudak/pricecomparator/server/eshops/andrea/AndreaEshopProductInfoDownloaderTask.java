package sk.hudak.pricecomparator.server.eshops.andrea;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.tasks.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 13. 1. 2016.
 */
public class AndreaEshopProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public AndreaEshopProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.ANDREA_ESHOP;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new AndreaEshopProductParser();
    }
}
