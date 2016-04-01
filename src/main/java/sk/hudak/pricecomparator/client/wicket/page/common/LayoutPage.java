package sk.hudak.pricecomparator.client.wicket.page.common;

import org.apache.wicket.markup.html.link.Link;
import sk.hudak.pricecomparator.client.wicket.page.eshop.EshopListPage;
import sk.hudak.pricecomparator.client.wicket.page.group.GroupListPage;
import sk.hudak.pricecomparator.client.wicket.page.group.GroupOfProductListPage;
import sk.hudak.pricecomparator.client.wicket.page.group.GroupProductPriceListPage;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductCreatePage;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductListPage;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductPricesPerEshopsPage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductInEshopCreatePage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductListPerEshopPage;

/**
 * Created by jan on 12. 3. 2016.
 */
public class LayoutPage extends BasicPage {

    public LayoutPage() {
        //TODO menu urobit ako samostatny panel

        //Eshop
        Link<Void> eshopList = new Link<Void>("eshopList") {
            @Override
            public void onClick() {
                setResponsePage(EshopListPage.class);
            }
        };
        add(eshopList);


        // Produkty
        Link<Void> productListPage = new Link<Void>("productList") {
            @Override
            public void onClick() {
                setResponsePage(ProductListPage.class);
            }
        };

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
        Link<Void> productCreateProdukt = new Link<Void>("productCreateProdukt") {
            @Override
            public void onClick() {
                setResponsePage(ProductCreatePage.class);
            }
        };

        Link<Void> productInEshopCreate = new Link<Void>("productInEshopCreate") {
            @Override
            public void onClick() {
                setResponsePage(ProductInEshopCreatePage.class);
            }
        };

        add(productListPage, productPricesPerEshops, productListPerEshop, productCreateProdukt, productInEshopCreate);

        //Skupiny
        Link<Void> groupList = new Link<Void>("groupList") {
            @Override
            public void onClick() {
                setResponsePage(GroupListPage.class);
            }
        };

        Link<Void> groupProductList = new Link<Void>("groupProductList") {
            @Override
            public void onClick() {
                setResponsePage(GroupOfProductListPage.class);
            }
        };
        Link<Void> groupProductPriceList = new Link<Void>("groupProductPriceList") {
            @Override
            public void onClick() {
                setResponsePage(GroupProductPriceListPage.class);
            }
        };
        add(groupList, groupProductList, groupProductPriceList);

    }

    //TODO overit co toto je za nastavenie
    @Override
    public boolean isVersioned() {
        return false;
    }

}
