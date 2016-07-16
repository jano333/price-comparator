package sk.hudak.pricecomparator.server.async.ng;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;

/**
 * Created by jan on 3. 7. 2016.
 */
public interface EshopTaskNg {

    EshopType getEshopType();

    void run(PriceComparatorService service, EshopTaskCallbackNg callback);
}
