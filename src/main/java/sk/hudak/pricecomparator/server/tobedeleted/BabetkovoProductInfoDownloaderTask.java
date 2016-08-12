package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 8. 5. 2016.
 */
@Deprecated
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
