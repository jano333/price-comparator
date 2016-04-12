package sk.hudak.pricecomparator.client.wicket.page.product;

import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;
import sk.hudak.pricecomparator.client.wicket.page.product.components.ProductPricesPerEshopsTable;

/**
 * Zoznam eshopov per produkt
 * <p/>
 * Created by jan on 17. 3. 2016.
 */
public class ProductPricesPerEshopsListPage extends TableWithFilterPage {

    public ProductPricesPerEshopsListPage() {
        add(new ProductPricesPerEshopsTable("table"));
    }
}
