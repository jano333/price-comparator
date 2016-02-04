package sk.hudak.pricecomparator.server.eshops.pilulka;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 13. 1. 2016.
 */
public class PilulkaProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public PilulkaProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.PILULKA;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new PilulkaEshopProductParser();
    }
}
