package sk.hudak.pricecomparator.client.wicket.page.eshop;

import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;
import sk.hudak.pricecomparator.client.wicket.page.eshop.components.EshopListTable;

/**
 * Zoznam eshopov
 * <p/>
 * Created by jan on 19. 3. 2016.
 */
public class EshopListPage extends TableWithFilterPage {

    public EshopListPage() {
        add(new EshopListTable("table"));
    }
}

