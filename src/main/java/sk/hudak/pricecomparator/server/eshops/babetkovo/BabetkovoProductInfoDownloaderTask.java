package sk.hudak.pricecomparator.server.eshops.babetkovo;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 8. 5. 2016.
 */
public class BabetkovoProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public BabetkovoProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.BABETKOVO;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new BabetkovoEshopProductParser();
    }
}
