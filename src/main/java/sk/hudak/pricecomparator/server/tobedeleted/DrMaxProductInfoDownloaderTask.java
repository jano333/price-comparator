package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 14. 1. 2016.
 */
@Deprecated
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
