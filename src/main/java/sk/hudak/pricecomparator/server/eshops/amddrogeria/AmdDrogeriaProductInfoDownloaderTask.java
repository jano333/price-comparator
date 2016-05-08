package sk.hudak.pricecomparator.server.eshops.amddrogeria;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 7. 5. 2016.
 */
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
