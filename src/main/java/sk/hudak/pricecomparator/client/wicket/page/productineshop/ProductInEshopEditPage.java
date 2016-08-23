package sk.hudak.pricecomparator.client.wicket.page.productineshop;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.service.ProductInEshopService;

/**
 * Created by jan on 12. 8. 2016.
 */
public class ProductInEshopEditPage extends LayoutPage {

    public ProductInEshopEditPage(PageParameters params) {
        long productInEshopId = params.get("productInEshopId").toLong();

        ProductInEshopService service =  PriceComparatorApplication.getApi();
        //TODO

        add(new FeedbackPanel("feedback"));
    }
}
