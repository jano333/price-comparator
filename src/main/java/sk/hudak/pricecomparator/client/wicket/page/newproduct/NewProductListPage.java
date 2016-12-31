package sk.hudak.pricecomparator.client.wicket.page.newproduct;

import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;

/**
 * Created by hudak on 31.12.2016.
 */
public class NewProductListPage extends TableWithFilterPage {

    public NewProductListPage() {
        add(new NewProductListTable("table"));
    }

}
