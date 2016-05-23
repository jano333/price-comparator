package sk.hudak.pricecomparator.server.eshops.drogeriavmd;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 23. 5. 2016.
 */
public class DrogeriaVmdProductInfoDowlnoaderTask extends EshopProductInfoDownloaderTask {

    public DrogeriaVmdProductInfoDowlnoaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.DROGERIA_VMD;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new DrogeriaVmdProductParser();
    }
}
