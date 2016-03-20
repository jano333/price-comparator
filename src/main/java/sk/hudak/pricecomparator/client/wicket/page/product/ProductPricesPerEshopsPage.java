package sk.hudak.pricecomparator.client.wicket.page.product;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;

/**
 * Created by jan on 17. 3. 2016.
 */
public class ProductPricesPerEshopsPage extends LayoutPage {

    public ProductPricesPerEshopsPage() {
        add(new ProductPricesPerEshopsTable("table"));
    }

    @Override
    protected IModel<String> getTitleModel() {
        //TODO
        return new ResourceModel("productpricespereshopspage.title");
    }

}
