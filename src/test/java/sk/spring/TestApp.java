package sk.spring;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import sk.connection.TestHttpGet;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.productineshop.ProductInEshopListDto;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

/**
 * Created by jan on 20. 1. 2017.
 */
@ContextConfiguration(locations = {"classpath:test-config.xml"})
public class TestApp extends AbstractTestNGSpringContextTests {

    @Inject
    private PriceComparatorService service;

    @Test
    public void testHH() {
        System.out.println("ahoj");
        List<ProductInEshopListDto> allProductInEshop = service.findAllProductInEshop();
        System.out.println(allProductInEshop.size());

        for (ProductInEshopListDto productInEshopListDto : allProductInEshop) {
            try {
                TestHttpGet.showHttpCode(productInEshopListDto.getEshopProductPage());
                sleepFor();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    protected void sleepFor() {
        int minSecond = 5;
        int maxSecond = 10;

        int result = new Random().nextInt((maxSecond - minSecond) + 1) + minSecond;

        try {
            logger.debug("zacinam cakat " + result + " sekund");
            Thread.currentThread().sleep(result * 1000);
            logger.debug("skoncil som cakat");

        } catch (InterruptedException e) {
            logger.error("sleeping was inteapted", e);
        }
    }
}
