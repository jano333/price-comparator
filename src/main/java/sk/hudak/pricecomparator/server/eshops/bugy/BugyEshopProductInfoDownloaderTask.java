package sk.hudak.pricecomparator.server.eshops.bugy;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 19. 5. 2016.
 */
public class BugyEshopProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public BugyEshopProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.BUGY;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new BugyEshopProductParser();
    }
}
