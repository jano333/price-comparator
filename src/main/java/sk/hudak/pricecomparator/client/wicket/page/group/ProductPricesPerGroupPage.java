package sk.hudak.pricecomparator.client.wicket.page.group;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.page.common.MyLayoutPage;

/**
 * Created by jan on 18. 3. 2016.
 */
public class ProductPricesPerGroupPage extends MyLayoutPage {

    public ProductPricesPerGroupPage() {
        add(new ProductPricesPerGroupTable("table"));
    }

    @Override
    protected IModel<String> getTitleModel() {
        return new ResourceModel("ProductPricesPerGroupPage.title");
    }
}
