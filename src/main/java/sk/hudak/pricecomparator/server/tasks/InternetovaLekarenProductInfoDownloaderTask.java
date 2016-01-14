package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.parser.InternetovaLekarenProductParser;

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