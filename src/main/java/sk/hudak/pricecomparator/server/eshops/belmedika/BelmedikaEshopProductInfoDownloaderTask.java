package sk.hudak.pricecomparator.server.eshops.belmedika;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 30. 5. 2016.
 */
public class BelmedikaEshopProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public BelmedikaEshopProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.BEL_MEDIKA;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new BelmedikaEshopProductParser();
    }

}