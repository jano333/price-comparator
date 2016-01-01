package sk.hudak.pricecomparator.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.server.xml.service.PriceComparatorXmlService;

/**
 * Created by jan on 16. 10. 2015.
 */
public class ServiceLocator {

    public static final boolean XML_VERSION = true;

    private static PriceComparatorService service;

    private ServiceLocator() {
    }

    public static PriceComparatorService getService() {
        if (service == null) {
            if (XML_VERSION) {
                service = new PriceComparatorXmlService();
            } else {
                ApplicationContext context = new ClassPathXmlApplicationContext("ctx-server.xml");
                service = context.getBean(PriceComparatorService.class);
            }
        }
        return service;
    }
}
