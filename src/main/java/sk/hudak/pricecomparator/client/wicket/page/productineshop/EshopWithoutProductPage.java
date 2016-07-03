package sk.hudak.pricecomparator.client.wicket.page.productineshop;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.components.EshopWithoutProductTable;

/**
 * Created by jan on 17. 6. 2016.
 */
public class EshopWithoutProductPage extends TableWithFilterPage {

    public static final transient String PARAM_PRODUCT_ID = "productId";

    public EshopWithoutProductPage() {
        this(new PageParameters());
    }

    public EshopWithoutProductPage(PageParameters params) {
        add(new EshopWithoutProductTable("table", WU.paramAsLong(params, PARAM_PRODUCT_ID)));
    }
}
