package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.HravoZdravoProductParser;

/**
 * Created by jan on 13. 1. 2016.
 */
public class HravoZdravoProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {
    public HravoZdravoProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.HRAVO_ZDRAVO;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new HravoZdravoProductParser();
    }
}