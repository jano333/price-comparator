package sk.hudak.pricecomparator.client.wicket.page.common;

import org.apache.wicket.markup.html.link.Link;
import sk.hudak.pricecomparator.client.wicket.page.group.ProductPricesPerGroupPage;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductPricesPerEshopsPage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductListPerEshopPage;

/**
 * Created by jan on 12. 3. 2016.
 */
public class MyLayoutPage extends MyRootPage {

    public MyLayoutPage() {
        //TODO menu urobit ako samostatny panel

        // Produkty
        Link<Void> productPricesPerEshops = new Link<Void>("productPricesPerEshops") {
            @Override
            public void onClick() {
                setResponsePage(ProductPricesPerEshopsPage.class);
            }
        };

        Link<Void> productListPerEshop = new Link<Void>("productListPerEshop") {
            @Override
            public void onClick() {
                setResponsePage(ProductListPerEshopPage.class);
            }
        };
        add(productPricesPerEshops, productListPerEshop);

        //Skupiny
        Link<Void> groupOfProduct = new Link<Void>("productGroupList") {
            @Override
            public void onClick() {
                setResponsePage(ProductPricesPerGroupPage.class);
            }
        };
        add(groupOfProduct);

    }

    //TODO overit co toto je za nastavenie
    @Override
    public boolean isVersioned() {
        return false;
    }

}
