package sk.hudak.pricecomparator.client.wicket.page.group;

import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;
import sk.hudak.pricecomparator.client.wicket.page.group.components.GroupListTable;

/**
 * Zoznam skupin
 * <p/>
 * Created by jan on 20. 3. 2016.
 */
public class GroupListPage extends TableWithFilterPage {

    public GroupListPage() {
        add(new GroupListTable("table"));
    }

}
