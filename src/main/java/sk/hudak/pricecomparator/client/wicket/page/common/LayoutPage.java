package sk.hudak.pricecomparator.client.wicket.page.common;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.page.eshop.EshopCreatePage;
import sk.hudak.pricecomparator.client.wicket.page.eshop.EshopListPage;
import sk.hudak.pricecomparator.client.wicket.page.group.*;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductCreatePage;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductInEshopCreateByUrlPage_1;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductListPage;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductPricesPerEshopsListPage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductInEshopCreatePage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductListPerEshopPage;

/**
 * Created by jan on 12. 3. 2016.
 */
public abstract class LayoutPage extends BasicPage {

    public LayoutPage() {

        // len v delepment mode zobrazenie nazvu impl stranky
        Label pageClass = new Label("pageClass", new Model<>(getClass().getSimpleName()));
        pageClass.setVisible(WU.isDevelopmentMode());
        add(pageClass);

        //TODO menu urobit ako samostatny panel
        //Eshop
        Link<Void> eshopList = new Link<Void>("eshopList") {
            @Override
            public void onClick() {
                setResponsePage(EshopListPage.class);
            }
        };
        add(eshopList);

        Link<Void> eshopCreate = new Link<Void>("eshopCreate") {
            @Override
            public void onClick() {
                setResponsePage(EshopCreatePage.class);
            }
        };
        add(eshopCreate);

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
                setResponsePage(ProductPricesPerEshopsListPage.class);
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
        Link<Void> productCreateByURLProdukt = new Link<Void>("productCreateByURLProdukt") {
            @Override
            public void onClick() {
                setResponsePage(ProductInEshopCreateByUrlPage_1.class);
            }
        };



        Link<Void> productInEshopCreate = new Link<Void>("productInEshopCreate") {
            @Override
            public void onClick() {
                setResponsePage(ProductInEshopCreatePage.class);
            }
        };

        add(productListPage, productPricesPerEshops, productListPerEshop, productCreateProdukt,
                productInEshopCreate, productCreateByURLProdukt);

        //Skupiny
        Link<Void> groupList = new Link<Void>("groupList") {
            @Override
            public void onClick() {
                setResponsePage(GroupListPage.class);
            }
        };

        Link<Void> groupCreate = new Link<Void>("groupCreate") {
            @Override
            public void onClick() {
                setResponsePage(GroupCreatePage.class);
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
        Link<Void> groupAddingProduct = new Link<Void>("groupAddingProduct") {
            @Override
            public void onClick() {
                setResponsePage(GroupAddingProductPage.class);
            }
        };
        add(groupList, groupCreate, groupProductList, groupProductPriceList, groupAddingProduct);

    }

    //TODO overit co toto je za nastavenie
    @Override
    public boolean isVersioned() {
        return false;
    }

    @Override
    protected IModel<String> getTitleModel() {
        return new ResourceModel(getClass().getSimpleName() + ".title");
    }


}
