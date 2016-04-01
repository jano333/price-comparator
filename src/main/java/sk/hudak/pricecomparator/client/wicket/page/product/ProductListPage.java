package sk.hudak.pricecomparator.client.wicket.page.product;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.client.wicket.page.product.components.ProductListTable;

/**
 * Zoznam produktov
 * <p/>
 * Created by jan on 19. 3. 2016.
 */
public class ProductListPage extends LayoutPage {

    public ProductListPage() {
        add(new ProductListTable("table"));
    }

    @Override
    protected IModel<String> getTitleModel() {
        return new ResourceModel("ProductListPage.title");
    }
}
