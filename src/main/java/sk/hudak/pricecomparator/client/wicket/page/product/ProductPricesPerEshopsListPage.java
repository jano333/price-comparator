package sk.hudak.pricecomparator.client.wicket.page.product;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.page.TableWithFilterPage;
import sk.hudak.pricecomparator.client.wicket.page.product.components.ProductPricesPerEshopsTable;

/**
 * Zoznam eshopov per produkt
 * <p/>
 * Created by jan on 17. 3. 2016.
 */
public class ProductPricesPerEshopsListPage extends TableWithFilterPage {

    public static final transient String PARAM_PRODUCT_ID = "productId";

    public ProductPricesPerEshopsListPage() {
        this(new PageParameters());
    }

    public ProductPricesPerEshopsListPage(PageParameters params) {
        add(new ProductPricesPerEshopsTable("table", WU.paramAsLong(params, PARAM_PRODUCT_ID)));
    }
}
