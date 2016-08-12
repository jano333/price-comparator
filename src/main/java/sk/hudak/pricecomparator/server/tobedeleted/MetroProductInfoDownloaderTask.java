package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

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
