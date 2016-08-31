package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 22. 5. 2016.
 */
@Deprecated
public class CkdMarketProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public CkdMarketProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.CKD_MARKET;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new CkdMarketProductParser();
    }
}
