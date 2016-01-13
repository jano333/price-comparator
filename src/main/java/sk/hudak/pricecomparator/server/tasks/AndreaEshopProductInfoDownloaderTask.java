package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.AndreaEshopProductParser;

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
