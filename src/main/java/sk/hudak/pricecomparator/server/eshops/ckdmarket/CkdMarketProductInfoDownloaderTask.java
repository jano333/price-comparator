package sk.hudak.pricecomparator.server.eshops.ckdmarket;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 22. 5. 2016.
 */
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
