package sk.hudak.pricecomparator.client.wicket.page.productineshop;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.ProductInEshopFindDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceInfoListDto;

import java.io.Serializable;

/**
 * Created by jan on 13. 3. 2016.
 */
public class ProductListPerEshopTable extends Panel {

    private ProductInEshopFindDto filter = new ProductInEshopFindDto();

    public ProductListPerEshopTable(String id) {
        super(id);

        IModel<PageList<ProductInEshopPriceInfoListDto>> productsInEshop = new LoadableDetachableModel<PageList<ProductInEshopPriceInfoListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<ProductInEshopPriceInfoListDto> load() {

                PriceComparatorService api = PriceComparatorApplication.getApi();

                return api.findProductsInEshopPriceInfoJh(filter);
            }

        };

        Table<ProductInEshopPriceInfoListDto> table = new Table<ProductInEshopPriceInfoListDto>("table", filter, productsInEshop) {
            private static final long serialVersionUID = 1L;

            @Override
            protected Serializable getObjectId(ProductInEshopPriceInfoListDto dto) {
                return dto.getId();
            }

            @Override
            protected ProductInEshopPriceInfoListDto loadLazyById(Serializable id) {
                //TODO
                return new ProductInEshopPriceInfoListDto();
            }

            @Override
            protected void populateItem(IdListView.IdListItem<ProductInEshopPriceInfoListDto> item) {
                IModel<ProductInEshopPriceInfoListDto> product = item.getModel();

                Label productName = new Label("productName", new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_NAME));


                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(productName);

                item.add(tr);
            }
        };
        add(table);

    }

    public ProductInEshopFindDto getFilter() {
        return filter;
    }

    public void setFilter(ProductInEshopFindDto filter) {
        this.filter = filter;
    }
}
