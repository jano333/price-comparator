package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 13. 1. 2016.
 */
@Deprecated
public class InternetovaLekarenProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public InternetovaLekarenProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.INTERNETOVA_LEKAREN;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new InternetovaLekarenProductParser();
    }
}
