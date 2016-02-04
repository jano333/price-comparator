package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.PerinbabaEshopProductParser;

/**
 * Created by jan on 13. 1. 2016.
 */
public class PerinbabaProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public PerinbabaProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.PERINBABA;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new PerinbabaEshopProductParser();
    }
}
