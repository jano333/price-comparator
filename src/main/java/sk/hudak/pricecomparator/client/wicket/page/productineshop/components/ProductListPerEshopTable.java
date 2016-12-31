package sk.hudak.pricecomparator.client.wicket.page.productineshop.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.*;
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
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductInEshopUpdatePage;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.eshop.EshopIdNameDto;
import sk.hudak.pricecomparator.middle.to.productineshop.ProductInEshopFindDto;
import sk.hudak.pricecomparator.middle.to.productineshop.ProductInEshopPriceInfoListDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
//                if (filter.getEshopId() != null) {
                    return PriceComparatorApplication.getApi().findProductsInEshopPriceInfo(filter);
//                }
                //FIXME empty page list
                // FIXME pocet stranok, verzus aktualna stranka
//                return new PageList<>(Collections.EMPTY_LIST, 0, 0);
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

        TextField<String> urlProduktuFilter = new TextField<>("urlProduktu",
                new PropertyModel<String>(filter, ProductInEshopFindDto.AT_PRODUCT_IN_ESHOP_URL));
        filterForm.add(urlProduktuFilter);


        // pagging
        filterForm.add(new PagingInfoPanel("infoPaging", filter, tableModel));

        Table<ProductInEshopPriceInfoListDto> table = new Table<ProductInEshopPriceInfoListDto>("table", filter, tableModel) {

            @Override
            protected Serializable getObjectId(ProductInEshopPriceInfoListDto dto) {
                return dto.getId();
            }

            @Override
            protected ProductInEshopPriceInfoListDto loadLazyById(Serializable id) {
                return PriceComparatorApplication.getApi().getProductInEhopPriceInfoListDto((Long) id);
            }

            @Override
            protected void populateItem(IdListView.IdListItem<ProductInEshopPriceInfoListDto> item) {
                IModel<ProductInEshopPriceInfoListDto> product = item.getModel();

                ExternalLink productImageLink = new ExternalLink("productImageLink",
                        new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_ESHOP_PAGE));
                productImageLink.add(WU.productImage(product.getObject().getPictureFullPath()));
                productImageLink.add(WU.atrTargetBlank());

                ExternalLink productName = new ExternalLink("productName",
                        new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_ESHOP_PAGE),
                        new PropertyModel<String>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_NAME)
                );
                productName.add(WU.atrTargetBlank());

                PriceForPackageColumn priceForPackage = new PriceForPackageColumn("priceForPackage",
                        new PropertyModel<BigDecimal>(product, ProductInEshopPriceInfoListDto.AT_PRICE_FOR_PACKAGE));

                PriceForOneItemInPackageColumn priceForOneItemInPackage = new PriceForOneItemInPackageColumn("priceForOneItemInPackage",
                        new PropertyModel<BigDecimal>(product, ProductInEshopPriceInfoListDto.AT_PRICE_FOR_ONE_ITEM_IN_PACKAGE));

                PriceForUnitColumn priceForUnit = new PriceForUnitColumn("priceForUnit",
                        new PropertyModel<BigDecimal>(product, ProductInEshopPriceInfoListDto.AT_PRICE_FOR_UNIT),
                        new PropertyModel<Unit>(product, ProductInEshopPriceInfoListDto.AT_UNIT));

                ProductActionColumn productAction = new ProductActionColumn("productAction",
                        new PropertyModel<ProductAction>(product, ProductInEshopPriceInfoListDto.AT_PRODUCT_ACTION));

                ActionValidToColumn actionValidTo = new ActionValidToColumn("actionValidTo",
                        new PropertyModel<Date>(product, ProductInEshopPriceInfoListDto.AT_ACTION_VALID_TO));

                LastUpdatedPriceColumn lastUpdatedPrice = new LastUpdatedPriceColumn("lastUpdatedPrice",
                        new PropertyModel<Date>(product, ProductInEshopPriceInfoListDto.AT_LAST_UPDATED_PRICE));

                //actions
                Link<ProductInEshopPriceInfoListDto> updateProductInEshop = new Link<ProductInEshopPriceInfoListDto>("updateProductInEshop", product) {
                    @Override
                    public void onClick() {
                        setResponsePage(ProductInEshopUpdatePage.class,
                                WU.param(ProductInEshopUpdatePage.PARAM_PRODUCT_IN_ESHOP_ID, getModelObject().getId()));
                    }
                };
                Link<ProductInEshopPriceInfoListDto> removeProductFromEshopLink = new Link<ProductInEshopPriceInfoListDto>("removeProductFromEshopLink", product) {
                    @Override
                    public void onClick() {
                        Long productInEshopId = getModelObject().getId();
                        try {
                            PriceComparatorApplication.getApi().deleteProductInEshop(productInEshopId);
                            System.out.println("uspesne odstraneny");

                        } catch (PriceComparatorBusinesException e) {
                            //TODO
                            e.printStackTrace();
                        }
                    }
                };


                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(productImageLink, productName,
                        priceForPackage, priceForOneItemInPackage, priceForUnit,
                        productAction, actionValidTo,
                        lastUpdatedPrice);
                //actions
                tr.add(updateProductInEshop);
                tr.add(removeProductFromEshopLink);

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
