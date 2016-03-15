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

                PageList<ProductInEshopPriceInfoListDto> result = api.findProductsInEshopPriceInfoJh(filter);
                return result;
            }

        };

        // mesage line
        Label allPageCount = new Label("allPageCount", new PropertyModel<String>(productsInEshop, PageList.AT_ALL_PAGE_COUNT));
        add(allPageCount);

        Label currentPage = new Label("currentPage", new PropertyModel<String>(productsInEshop, PageList.AT_CURRENT_PAGE));
        add(currentPage);

        //currentPage


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
                Label priceForPackage = new Label("priceForPackage", new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRICE_FOR_PACKAGE));
                Label priceForUnit = new Label("priceForUnit", new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRICE_FOR_UNIT));
                Label productAction = new Label("productAction", new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_ACTION));
                Label actionValidTo = new Label("actionValidTo", new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_ACTION_VALID_TO));
                Label lastUpdatedPrice = new Label("lastUpdatedPrice", new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_LAST_UPDATED_PRICE));


                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(productName, priceForPackage, priceForUnit, productAction, actionValidTo, lastUpdatedPrice);

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
