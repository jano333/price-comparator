package sk.hudak.pricecomparator.client.wicket.page.product.components;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanel;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.middle.to.ProductFindDto;
import sk.hudak.pricecomparator.middle.to.ProductIdNameDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceResultListDto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 17. 3. 2016.
 */
public class ProductPricesPerEshopsTable extends Panel {

    private ProductFindDto filter = new ProductFindDto();


    private ProductIdNameDto selectedProduct;

    public ProductPricesPerEshopsTable(String id, Long selectedProductId) {
        super(id);

        if(selectedProductId !=null){
            selectedProduct = PriceComparatorApplication.getApi().getProductIdNameDto(selectedProductId);
        }

        IModel<PageList<ProductInEshopPriceResultListDto>> tableModel = new LoadableDetachableModel<PageList<ProductInEshopPriceResultListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<ProductInEshopPriceResultListDto> load() {
                if (filter.getProductId() != null) {
                    return PriceComparatorApplication.getApi().findPriceInfoInEshopsForProduct(filter);
                } else {
                    return new PageList<>(Collections.EMPTY_LIST, 0, 0);
                }

            }
        };

        // filter
        Form<Void> filterForm = new Form<Void>("filterForm") {
            @Override
            protected void onSubmit() {
                if (selectedProduct != null) {
                    filter.setProductId(selectedProduct.getId());
                }
            }
        };
        add(filterForm);

        DropDownChoice<ProductIdNameDto> productFilter = new DropDownChoice<>(
                "product",
                new PropertyModel<ProductIdNameDto>(this, "selectedProduct"),
                new LoadableDetachableModel<List<ProductIdNameDto>>() {
                    @Override
                    protected List<ProductIdNameDto> load() {
                        return PriceComparatorApplication.getApi().findAllProductForSelection();
                    }
                },
                new ChoiceRenderer<ProductIdNameDto>(ProductIdNameDto.AT_NAME)
        );
        filterForm.add(productFilter);

        // pagging
        filterForm.add(new PagingInfoPanel("infoPaging", filter, tableModel));


        Table<ProductInEshopPriceResultListDto> table = new Table<ProductInEshopPriceResultListDto>("table", filter, tableModel) {

            @Override
            protected Serializable getObjectId(ProductInEshopPriceResultListDto dto) {
                return dto.getId();
            }

            @Override
            protected ProductInEshopPriceResultListDto loadLazyById(Serializable id) {
                //TODO
                return new ProductInEshopPriceResultListDto();
            }

            @Override
            protected void populateItem(IdListView.IdListItem<ProductInEshopPriceResultListDto> item) {
                IModel<ProductInEshopPriceResultListDto> product = item.getModel();

                ExternalLink eshopName = new ExternalLink("eshopName",
                        new PropertyModel<String>(product, ProductInEshopPriceResultListDto.AT_PRODUCT_ESHOP_PAGE),
                        new PropertyModel<String>(product, ProductInEshopPriceResultListDto.AT_ESHOP_NAME)
                );
                eshopName.add(new AttributeAppender("target", "_blank"));


                Label priceForPackage = new Label("priceForPackage", new PropertyModel<String>(product, ProductInEshopPriceResultListDto.AT_PRICE_FOR_PACKAGE));
                Label priceForUnit = new Label("priceForUnit", new PropertyModel<String>(product, ProductInEshopPriceResultListDto.AT_PRICE_FOR_UNIT));
                Label productAction = new Label("productAction", new PropertyModel<String>(product, ProductInEshopPriceResultListDto.AT_PRODUCT_ACTION));
                Label actionValidTo = new Label("actionValidTo", new PropertyModel<String>(product, ProductInEshopPriceResultListDto.AT_ACTION_VALID_TO));
                Label lastUpdatedPrice = new Label("lastUpdatedPrice", new PropertyModel<String>(product, ProductInEshopPriceResultListDto.AT_LAST_UPDATED_PRICE));


                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(eshopName, priceForPackage, priceForUnit, productAction, actionValidTo, lastUpdatedPrice);

                item.add(tr);
            }
        };
        add(table);

    }

    public ProductFindDto getFilter() {
        return filter;
    }

    public void setFilter(ProductFindDto filter) {
        this.filter = filter;
    }

    public ProductIdNameDto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductIdNameDto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
