package sk.hudak.pricecomparator.server.tobedeleted;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.service.ProductInEshopService;

/**
 * Created by jan on 16. 10. 2015.
 */
@Deprecated
public class ServiceLocator {

    private static PriceComparatorService service;
    private static ApplicationContext ctx;

    static {
        ctx = new ClassPathXmlApplicationContext("ctx-server.xml");
    }

    private ServiceLocator() {
    }

    public static PriceComparatorService getService() {
        if (service == null) {
            service = ctx.getBean(PriceComparatorService.class);
        }
        return service;
    }

    public static ProductInEshopService getProductInEshopService() {
        return getService();
    }
}
