package sk.hudak.pricecomparator.server.eshops.lekarenbella;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 23. 5. 2016.
 */
public class LekarenBellaProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public LekarenBellaProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.LEKAREN_BELLA;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new LekarenBellaProductParser();
    }
}
