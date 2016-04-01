package sk.hudak.pricecomparator.client.wicket.page.group;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.client.wicket.page.group.components.GroupListTable;

/**
 * Zoznam skupin
 * <p/>
 * Created by jan on 20. 3. 2016.
 */
public class GroupListPage extends LayoutPage {

    public GroupListPage() {
        add(new GroupListTable("table"));
    }

    @Override
    protected IModel<String> getTitleModel() {
        return new ResourceModel("GroupListPage.title");
    }
}
