package sk.hudak.pricecomparator.client.wicket.page.group;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.client.wicket.page.group.components.GroupOfProductListTable;

/**
 * Zoznam produktov v skupine
 * <p/>
 * Created by jan on 20. 3. 2016.
 */
public class GroupOfProductListPage extends LayoutPage {


    public GroupOfProductListPage() {
        add(new GroupOfProductListTable("table"));
    }

    @Override
    protected IModel<String> getTitleModel() {
        //TODO
        return new ResourceModel("GroupOfProductListPage.title");
    }
}
