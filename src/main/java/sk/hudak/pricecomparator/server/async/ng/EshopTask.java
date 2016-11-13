package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 3. 7. 2016.
 */
@Deprecated
public interface EshopTask {

    EshopType getEshopType();

    void run(PriceComparatorService service, EshopTaskCallback callback);
}
