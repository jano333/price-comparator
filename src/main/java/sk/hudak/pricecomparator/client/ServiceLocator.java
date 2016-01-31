package sk.hudak.pricecomparator.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.service.ProductInEshopService;
import sk.hudak.pricecomparator.server.xml.service.PriceComparatorXmlService;

/**
 * Created by jan on 16. 10. 2015.
 */
public class ServiceLocator {

    //    public static final boolean XML_VERSION = true;
    public static final boolean XML_VERSION = false;

    private static PriceComparatorService service;

    private ServiceLocator() {
    }

    public static PriceComparatorService getService() {
        if (service == null) {
            if (XML_VERSION) {
                service = new PriceComparatorXmlService();
            } else {
                service = new ClassPathXmlApplicationContext("ctx-server.xml").getBean(PriceComparatorService.class);
            }
        }
        return service;
    }

    public static ProductInEshopService getProductInEshopService() {
        return getService();
    }
}
