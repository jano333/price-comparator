package sk.hudak.pricecomparator.server.eshops.hravozdravo;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductParser;

/**
 * Created by jan on 13. 1. 2016.
 */
@Deprecated
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
