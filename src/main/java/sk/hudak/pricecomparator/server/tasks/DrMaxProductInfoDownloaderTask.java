package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.DrMaxEshopProductParser;

/**
 * Created by jan on 14. 1. 2016.
 */
public class DrMaxProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public DrMaxProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.DR_MAX;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new DrMaxEshopProductParser();
    }
}
