package sk.hudak.pricecomparator.client.wicket.page.group;

import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;
import sk.hudak.pricecomparator.client.wicket.page.group.components.GroupOfProductListTable;

/**
 * Zoznam produktov v skupine
 * <p/>
 * Created by jan on 20. 3. 2016.
 */
public class GroupOfProductListPage extends TableWithFilterPage {


    public GroupOfProductListPage() {
        add(new GroupOfProductListTable("table"));
    }

}
