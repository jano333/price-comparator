package sk.hudak.pricecomparator.server.eshops.hravozdravo;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

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
