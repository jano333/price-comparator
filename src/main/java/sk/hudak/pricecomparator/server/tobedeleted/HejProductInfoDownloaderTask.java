package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 13. 1. 2016.
 */
@Deprecated
public class HejProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public HejProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.HEJ;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new HejEshopProductParser();
    }
}
