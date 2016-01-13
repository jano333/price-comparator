package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.AlzaEshopProductParser;

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
