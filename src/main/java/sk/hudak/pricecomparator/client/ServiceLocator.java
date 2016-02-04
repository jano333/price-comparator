package sk.hudak.pricecomparator.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.service.ProductInEshopService;

/**
 * Created by jan on 16. 10. 2015.
 */
public class ServiceLocator {

    private static PriceComparatorService service;

    private ServiceLocator() {
    }

    public static PriceComparatorService getService() {
        if (service == null) {
            service = new ClassPathXmlApplicationContext("ctx-server.xml").getBean(PriceComparatorService.class);
        }
        return service;
    }

    public static ProductInEshopService getProductInEshopService() {
        return getService();
    }
}
