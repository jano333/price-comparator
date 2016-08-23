package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 30. 5. 2016.
 */
@Deprecated
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