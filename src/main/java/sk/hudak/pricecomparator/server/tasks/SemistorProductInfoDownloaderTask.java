package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.SemistorEshopProductParser;

/**
 * Created by jan on 13. 1. 2016.
 */
public class SemistorProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public SemistorProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.SEMISTOR;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new SemistorEshopProductParser();
    }
}