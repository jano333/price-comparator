package sk.hudak.pricecomparator.client.wicket.page.productineshop.components;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanel;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.middle.to.EshopWithoutProductListDto;
import sk.hudak.pricecomparator.middle.to.ProductFindDto;
import sk.hudak.pricecomparator.middle.to.ProductIdNameDto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 17. 6. 2016.
 */
public class EshopWithoutProductTable extends Panel {

    private ProductFindDto filter = new ProductFindDto();

    private ProductIdNameDto selectedProduct;

    public EshopWithoutProductTable(String id, Long selectedProductId) {
        super(id);

        if (selectedProductId != null) {
            selectedProduct = PriceComparatorApplication.getApi().getProductIdNameById(selectedProductId);
        }

        IModel<PageList<EshopWithoutProductListDto>> tableModel = new LoadableDetachableModel<PageList<EshopWithoutProductListDto>>() {
            @Override
            protected PageList<EshopWithoutProductListDto> load() {
                if (filter.getProductId() != null) {
                    return PriceComparatorApplication.getApi().findEshopWithoutProduct(filter);
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

        Table<EshopWithoutProductListDto> table = new Table<EshopWithoutProductListDto>("table", filter, tableModel) {

            @Override
            protected Serializable getObjectId(EshopWithoutProductListDto object) {
                return object.getProductInEshopId();
            }

            @Override
            protected EshopWithoutProductListDto loadLazyById(Serializable id) {
                //TODO
                return new EshopWithoutProductListDto();
            }

            @Override
            protected void populateItem(IdListView.IdListItem<EshopWithoutProductListDto> item) {
                IModel<EshopWithoutProductListDto> model = item.getModel();
                //TODO


            }
        };

        add(table);

    }
}
