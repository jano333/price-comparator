package sk.hudak.pricecomparator.client.wicket.page.group;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.client.wicket.page.group.components.GroupProductPriceListTable;

/**
 * Zoznam cien produktov v skupine
 * <p/>
 * Created by jan on 18. 3. 2016.
 */
public class GroupProductPriceListPage extends LayoutPage {

    public GroupProductPriceListPage() {
        add(new GroupProductPriceListTable("table"));
    }

    @Override
    protected IModel<String> getTitleModel() {
        return new ResourceModel("GroupProductPriceListPage.title");
    }
}
