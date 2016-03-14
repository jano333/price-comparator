package sk.hudak.pricecomparator.client.wicket.page.productineshop;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.page.common.MyLayoutPage;

/**
 * Created by jan on 13. 3. 2016.
 */
public class ProductListPerEshopPage extends MyLayoutPage {

    public ProductListPerEshopPage() {
        add(new ProductListPerEshopTable("table"));
    }

    @Override
    protected IModel<String> getTitleModel() {
        //TODO
        return new ResourceModel("paymentHistory.title");
    }

}
