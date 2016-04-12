package sk.hudak.pricecomparator.client.wicket.page.product;

import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;
import sk.hudak.pricecomparator.client.wicket.page.product.components.ProductListTable;

/**
 * Zoznam produktov
 * <p/>
 * Created by jan on 19. 3. 2016.
 */
public class ProductListPage extends TableWithFilterPage {

    public ProductListPage() {
        add(new ProductListTable("table"));
    }

}
