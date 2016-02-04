package sk.hudak.pricecomparator.server.eshops.alza;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.tasks.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 13. 1. 2016.
 */
public class AlzaProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {
    public AlzaProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.ALZA;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new AlzaEshopProductParser();
    }
}
