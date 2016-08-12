package sk.hudak.pricecomparator.server.tobedeleted;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 7. 5. 2016.
 */
@Deprecated
public class AmdDrogeriaProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public AmdDrogeriaProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.AMD_DROGERIA;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new AmdDrogeriaProductParser();
    }
}
