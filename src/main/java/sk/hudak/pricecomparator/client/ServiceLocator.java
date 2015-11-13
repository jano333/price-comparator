package sk.hudak.pricecomparator.client;

import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.xml.service.PriceComparatorXmlService;

/**
 * Created by jan on 16. 10. 2015.
 */
public class ServiceLocator {

    private static PriceComparatorService service;

    private ServiceLocator() {
    }

    public static PriceComparatorService getService() {
        if (service == null) {
            service = new PriceComparatorXmlService();
        }
        return service;
    }
}
