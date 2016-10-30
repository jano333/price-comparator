package sk.hudak.pricecomparator.client.wicket.page.group.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
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
import sk.hudak.pricecomparator.client.wicket.component.table.column.*;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductUpdatePage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductInEshopUpdatePage;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.to.GroupIdNameDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceResultListDto;
import sk.hudak.pricecomparator.middle.to.ProductPriceInGroupFindDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by jan on 18. 3. 2016.
 */
public class GroupProductPriceListTable extends Panel {

    private ProductPriceInGroupFindDto filter = new ProductPriceInGroupFindDto();

    private GroupIdNameDto selectedGroup;

    public GroupProductPriceListTable(String id) {
        super(id);

        IModel<PageList<ProductInEshopPriceResultListDto>> tableModel = new LoadableDetachableModel<PageList<ProductInEshopPriceResultListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<ProductInEshopPriceResultListDto> load() {
                if (filter.getGroupId() != null) {
                    return PriceComparatorApplication.getApi().findPriceInfoInEshopsForGroup(filter);
                } else {
                    return new PageList<>(Collections.EMPTY_LIST, 0, 0);
                }
            }
        };

        // filter
        Form<Void> filterForm = new Form<Void>("filterForm") {
            @Override
            protected void onSubmit() {
                if (selectedGroup != null) {
                    filter.setGroupId(selectedGroup.getId());
                }
            }
        };
        add(filterForm);

        DropDownChoice<GroupIdNameDto> groupFilter = new DropDownChoice<GroupIdNameDto>("skupina",
                new PropertyModel<GroupIdNameDto>(this, "selectedGroup"),
                new LoadableDetachableModel<List<GroupIdNameDto>>() {
                    @Override
                    protected List<GroupIdNameDto> load() {
                        return PriceComparatorApplication.getApi().findAllProductGroupSelection();
                    }
                },
                new ChoiceRenderer<GroupIdNameDto>(GroupIdNameDto.AT_NAME)
        ) {
            @Override
            protected String getNullKey() {
                return "core.dropdown.nullValue.group";
            }
        };
        filterForm.add(groupFilter);

        // mesage line

        // pagging
        filterForm.add(new PagingInfoPanel("infoPaging", filter, tableModel));


        Table<ProductInEshopPriceResultListDto> table = new Table<ProductInEshopPriceResultListDto>("table", filter, tableModel) {

            @Override
            protected Serializable getObjectId(ProductInEshopPriceResultListDto dto) {
                return dto.getId();
            }

            @Override
            protected ProductInEshopPriceResultListDto loadLazyById(Serializable id) {
                return PriceComparatorApplication.getApi().getProductInEshopPriceResultListDto((Long) id);
            }

            @Override
            protected void populateItem(IdListView.IdListItem<ProductInEshopPriceResultListDto> item) {
                IModel<ProductInEshopPriceResultListDto> model = item.getModel();

                ExternalLink productImageLink = new ExternalLink("productImageLink",
                        new PropertyModel<String>(model, ProductInEshopPriceResultListDto.AT_PRODUCT_ESHOP_PAGE));
                productImageLink.add(WU.productImage(model.getObject().getImagePath()));

                ExternalLink productName = new ExternalLink("productName",
                        new PropertyModel<String>(model, ProductInEshopPriceResultListDto.AT_PRODUCT_ESHOP_PAGE),
                        new PropertyModel<String>(model, ProductInEshopPriceResultListDto.AT_PRODUCT_NAME)
                );
                productName.add(WU.atrTargetBlank());

                ExternalLink productInEshopPage = new ExternalLink("productInEshopPage",
                        new PropertyModel<String>(model, ProductInEshopPriceResultListDto.AT_PRODUCT_ESHOP_PAGE),
                        new PropertyModel<String>(model, ProductInEshopPriceResultListDto.AT_ESHOP_NAME)
                );
                productInEshopPage.add(WU.atrTargetBlank());

                PriceForPackageColumn priceForPackage = new PriceForPackageColumn("priceForPackage",
                        new PropertyModel<BigDecimal>(model, ProductInEshopPriceResultListDto.AT_PRICE_FOR_PACKAGE));

                PriceForOneItemInPackageColumn priceForOneItemInPackage = new PriceForOneItemInPackageColumn("priceForOneItemInPackage",
                        new PropertyModel<BigDecimal>(model, ProductInEshopPriceResultListDto.AT_PRICE_FOR_ONE_ITEM_IN_PACKAGE));

                PriceForUnitColumn priceForUnit = new PriceForUnitColumn("priceForUnit",
                        new PropertyModel<BigDecimal>(model, ProductInEshopPriceResultListDto.AT_PRICE_FOR_UNIT),
                        new PropertyModel<Unit>(model, ProductInEshopPriceResultListDto.AT_UNIT));

                ProductActionColumn productAction = new ProductActionColumn("productAction",
                        new PropertyModel<ProductAction>(model, ProductInEshopPriceResultListDto.AT_PRODUCT_ACTION));

                ActionValidToColumn actionValidTo = new ActionValidToColumn("actionValidTo",
                        new PropertyModel<Date>(model, ProductInEshopPriceResultListDto.AT_ACTION_VALID_TO));

                LastUpdatedPriceColumn lastUpdatedPrice = new LastUpdatedPriceColumn("lastUpdatedPrice",
                        new PropertyModel<Date>(model, ProductInEshopPriceResultListDto.AT_LAST_UPDATED_PRICE));

                //actions
                Link<ProductInEshopPriceResultListDto> updateProductInEshop = new Link<ProductInEshopPriceResultListDto>(
                        "updateProductInEshop", model) {
                    @Override
                    public void onClick() {
                        setResponsePage(ProductInEshopUpdatePage.class,
                                WU.param(ProductInEshopUpdatePage.PARAM_PRODUCT_IN_ESHOP_ID, getModelObject().getId()));
                    }
                };

                Link<ProductInEshopPriceResultListDto> updateProduct = new Link<ProductInEshopPriceResultListDto>(
                        "updateProduct", model) {
                    @Override
                    public void onClick() {
                        setResponsePage(ProductUpdatePage.class,
                                WU.param(ProductUpdatePage.PARAM_PRODUCT_ID, getModelObject().getProduct().getId()));
                    }
                };


                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(productImageLink,
                        productName,
                        productInEshopPage,
                        priceForPackage, priceForOneItemInPackage, priceForUnit,
                        productAction, actionValidTo,
                        lastUpdatedPrice);
                //actions
                tr.add(updateProductInEshop);
                tr.add(updateProduct);

                item.add(tr);
            }

        };
        add(table);
    }

    public ProductPriceInGroupFindDto getFilter() {
        return filter;
    }

    public void setFilter(ProductPriceInGroupFindDto filter) {
        this.filter = filter;
    }

    public GroupIdNameDto getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(GroupIdNameDto selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
}
