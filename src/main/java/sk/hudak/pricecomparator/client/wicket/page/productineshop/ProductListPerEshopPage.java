package sk.hudak.pricecomparator.client.wicket.page.productineshop;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.components.ProductListPerEshopTable;

/**
 * Created by jan on 13. 3. 2016.
 */
public class ProductListPerEshopPage extends TableWithFilterPage {

    public static final transient String PARAM_ESHOP_ID = "eshopId";

    public ProductListPerEshopPage() {
        this(new PageParameters());
    }

    public ProductListPerEshopPage(PageParameters params) {
        add(new ProductListPerEshopTable("table", WU.paramAsLong(params, PARAM_ESHOP_ID)));
    }


}
