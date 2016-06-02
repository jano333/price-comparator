package sk.hudak.pricecomparator.server.eshops.novalekaren;

import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.core.EshopProductInfoDownloaderTask;

/**
 * Created by jan on 2. 6. 2016.
 */
public class NovaLekarenProductInfoDownloaderTask extends EshopProductInfoDownloaderTask {

    public NovaLekarenProductInfoDownloaderTask(PriceComparatorService service) {
        super(service);
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.NOVA_LEKAREN;
    }

    @Override
    protected EshopProductParser getEshopParser() {
        return new NovaLekarenProductParser();
    }
}
