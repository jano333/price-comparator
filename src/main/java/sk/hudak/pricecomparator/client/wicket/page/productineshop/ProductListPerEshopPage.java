package sk.hudak.pricecomparator.client.wicket.page.productineshop;

import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.components.ProductListPerEshopTable;

/**
 * Created by jan on 13. 3. 2016.
 */
public class ProductListPerEshopPage extends TableWithFilterPage {

    public ProductListPerEshopPage() {
        add(new ProductListPerEshopTable("table"));
    }
}
