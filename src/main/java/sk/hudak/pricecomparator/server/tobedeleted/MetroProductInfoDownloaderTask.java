package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 13. 1. 2016.
 */
@Deprecated
public class MetroProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public MetroProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.METRO;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new MetroEshopProductParserOld();
    }
}
