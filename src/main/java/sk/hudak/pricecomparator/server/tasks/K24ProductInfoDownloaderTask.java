package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.K24EshopProductParser;

/**
 * Created by jan on 14. 1. 2016.
 */
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
