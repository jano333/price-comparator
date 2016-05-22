package sk.hudak.pricecomparator.server.eshops.shoppie;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 22. 5. 2016.
 */
public class ShoppieProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public ShoppieProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.SHOPPIE;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new ShoppieProductParser();
    }
}
