package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.TescoEshopProductParser;

/**
 * Created by jan on 4. 1. 2016.
 */
public class TescoProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public TescoProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.TESCO;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new TescoEshopProductParser();
    }
}