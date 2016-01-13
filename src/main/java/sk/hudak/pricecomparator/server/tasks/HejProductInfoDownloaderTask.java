package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.HejEshopProductParser;

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
