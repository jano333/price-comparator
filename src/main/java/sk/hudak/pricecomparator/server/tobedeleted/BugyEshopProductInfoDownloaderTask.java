package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 19. 5. 2016.
 */
@Deprecated
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
