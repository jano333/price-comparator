package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 13. 1. 2016.
 */
@Deprecated
public class MallProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public MallProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.MALL;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new MallEshopProductParser();
    }
}
