package sk.hudak.pricecomparator.server.eshops.internetovalekaren;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfoDownloaderTask;
import sk.hudak.pricecomparator.server.tobedeleted.EshopProductParser;

/**
 * Created by jan on 13. 1. 2016.
 */
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
