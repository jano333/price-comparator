package sk.hudak.pricecomparator.client.wicket.page.productineshop.components;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanel;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.client.wicket.component.table.column.PriceForOneItemInPackageColumn;
import sk.hudak.pricecomparator.client.wicket.component.table.column.PriceForPackageColumn;
import sk.hudak.pricecomparator.client.wicket.component.table.column.PriceForUnitColumn;
import sk.hudak.pricecomparator.middle.to.EshopIdNameDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopFindDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceInfoListDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 13. 3. 2016.
 */
public class ProductListPerEshopTable extends Panel {

    private ProductInEshopFindDto filter = new ProductInEshopFindDto();

    private EshopIdNameDto selectedEshop;

    public ProductListPerEshopTable(String id, Long selectedEshopId) {
        super(id);

        if (selectedEshopId != null) {
            selectedEshop = PriceComparatorApplication.getApi().getEshopIdNameById(selectedEshopId);
        }

        IModel<PageList<ProductInEshopPriceInfoListDto>> tableModel = new LoadableDetachableModel<PageList<ProductInEshopPriceInfoListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<ProductInEshopPriceInfoListDto> load() {
                if (filter.getEshopId() != null) {
                    return PriceComparatorApplication.getApi().findProductsInEshopPriceInfo(filter);
                }
                //FIXME empty page list
                // FIXME pocet stranok, verzus aktualna stranka
                return new PageList<>(Collections.EMPTY_LIST, 0, 0);
            }
        };

        // filter
        Form<Void> filterForm = new Form<Void>("filterForm") {
            @Override
            protected void onSubmit() {
                if (selectedEshop != null) {
                    filter.setEshopId(selectedEshop.getId());
                }
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
                return "core.dropdown.nullValue.eshop";
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

                ExternalLink productImageLink = new ExternalLink("productImageLink",
                        new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_ESHOP_PAGE));
                ContextImage productImage = WU.productImage(product.getObject().getPictureFullPath());
                productImageLink.add(productImage);

                ExternalLink productName = new ExternalLink("productName",
                        new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_ESHOP_PAGE),
                        new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_NAME)
                );
                productName.add(new AttributeAppender("target", "_blank"));


                PriceForPackageColumn priceForPackage = new PriceForPackageColumn("priceForPackage",
                        new PropertyModel<BigDecimal>(product, ProductInEshopPriceInfoListDto.AT_PRICE_FOR_PACKAGE));

                PriceForOneItemInPackageColumn priceForOneItemInPackage = new PriceForOneItemInPackageColumn("priceForOneItemInPackage",
                        new PropertyModel<BigDecimal>(product, ProductInEshopPriceInfoListDto.AT_PRICE_FOR_ONE_ITEM_IN_PACKAGE));

                PriceForUnitColumn priceForUnit = new PriceForUnitColumn("priceForUnit",
                        new PropertyModel<BigDecimal>(product, ProductInEshopPriceInfoListDto.AT_PRICE_FOR_UNIT));

                Label productAction = new Label("productAction", new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_ACTION));

                Label actionValidTo = new Label("actionValidTo", new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_ACTION_VALID_TO));

                Label lastUpdatedPrice = new Label("lastUpdatedPrice", new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_LAST_UPDATED_PRICE));

                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(productImageLink, productName,
                        priceForPackage, priceForOneItemInPackage, priceForUnit,
                        productAction, actionValidTo,
                        lastUpdatedPrice);

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
