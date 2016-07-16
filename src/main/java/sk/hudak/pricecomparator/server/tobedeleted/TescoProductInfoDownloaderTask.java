package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 4. 1. 2016.
 */
@Deprecated
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
