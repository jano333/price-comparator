package sk.hudak.pricecomparator.server.eshops.hej;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 13. 1. 2016.
 */
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
