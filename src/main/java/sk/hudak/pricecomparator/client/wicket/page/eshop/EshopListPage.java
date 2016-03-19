package sk.hudak.pricecomparator.client.wicket.page.eshop;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.page.common.MyLayoutPage;

/**
 * Created by jan on 19. 3. 2016.
 */
public class EshopListPage extends MyLayoutPage {

    public EshopListPage() {
        add(new EshopListTable("table"));
    }

    @Override
    protected IModel<String> getTitleModel() {
        return new ResourceModel("EshopListPage.title");
    }
}

