package sk.jetty;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductListPerEshopPage;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {
    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new PriceComparatorApplication());
    }

    @Test
    public void homepageRendersSuccessfully() {
        //start and render the test page
        tester.startPage(ProductListPerEshopPage.class);

        //assert rendered page class
        tester.assertRenderedPage(ProductListPerEshopPage.class);
    }
}
