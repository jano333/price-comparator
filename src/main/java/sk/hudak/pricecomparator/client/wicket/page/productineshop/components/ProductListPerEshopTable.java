package sk.hudak.pricecomparator.client.wicket.page.productineshop.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanel;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.middle.to.EshopIdNameDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopFindDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceInfoListDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jan on 13. 3. 2016.
 */
public class ProductListPerEshopTable extends Panel {

    private ProductInEshopFindDto filter = new ProductInEshopFindDto();

    //TODO prednastaveny eshop...
    private EshopIdNameDto selectedEshop;

    public ProductListPerEshopTable(String id) {
        super(id);

        IModel<PageList<ProductInEshopPriceInfoListDto>> tableModel = new LoadableDetachableModel<PageList<ProductInEshopPriceInfoListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<ProductInEshopPriceInfoListDto> load() {
                return PriceComparatorApplication.getApi().findProductsInEshopPriceInfo(filter);
            }
        };

        // filter
        Form<Void> filterForm = new Form<Void>("filterForm") {
            @Override
            protected void onSubmit() {
                //FIXME skusit inak poriesit
                filter.setEshopId(selectedEshop.getId());
            }
        };
        add(filterForm);

        DropDownChoice<EshopIdNameDto> eshopFilter = new DropDownChoice<EshopIdNameDto>(
                "eshop",
                new PropertyModel<EshopIdNameDto>(this, "selectedEshop"),
                new LoadableDetachableModel<List<EshopIdNameDto>>() {
                    @Override
                    protected List<EshopIdNameDto> load() {
                        return PriceComparatorApplication.getApi().findAllEshopsForSelection();
                    }
                },
                new ChoiceRenderer<EshopIdNameDto>(EshopIdNameDto.AT_NAME)
        ) {
            @Override
            protected String getNullKey() {
                //FIXME zatial dava 'Vyberte jeden'
                return super.getNullKey();
            }
        };
        filterForm.add(eshopFilter);

        TextField<String> productNameFilter = new TextField<>("productName",
                new PropertyModel<String>(filter, ProductInEshopFindDto.AT_PRODUCT_NAME));
        filterForm.add(productNameFilter);

        CheckBox onlyInActionFilter = new CheckBox("onlyInAction",
                new PropertyModel<Boolean>(filter, ProductInEshopFindDto.AT_ONLY_IN_ACTION));
        filterForm.add(onlyInActionFilter);

        // pagging
        filterForm.add(new PagingInfoPanel("infoPaging", filter, tableModel));

        Table<ProductInEshopPriceInfoListDto> table = new Table<ProductInEshopPriceInfoListDto>("table", filter, tableModel) {
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

    public EshopIdNameDto getSelectedEshop() {
        return selectedEshop;
    }

    public void setSelectedEshop(EshopIdNameDto selectedEshop) {
        this.selectedEshop = selectedEshop;
    }
}
