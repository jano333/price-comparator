package sk.hudak.pricecomparator.client.wicket.page.group.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanel;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.middle.to.GroupIdNameDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceResultListDto;
import sk.hudak.pricecomparator.middle.to.ProductPriceInGroupFindDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jan on 18. 3. 2016.
 */
public class GroupProductPriceListTable extends Panel {

    private ProductPriceInGroupFindDto filter = new ProductPriceInGroupFindDto();

    //TODO FIXME
    private GroupIdNameDto selectedGroup = new GroupIdNameDto(1l, "haha");

    public GroupProductPriceListTable(String id) {
        super(id);

        IModel<PageList<ProductInEshopPriceResultListDto>> tableModel = new LoadableDetachableModel<PageList<ProductInEshopPriceResultListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<ProductInEshopPriceResultListDto> load() {
                return ServiceLocator.getService().findPriceInfoInEshopsForGroup(filter);
            }
        };

        // filter
        Form<Void> filterForm = new Form<Void>("filterForm") {
            @Override
            protected void onSubmit() {
                //FIXME skusit inak poriesit
                filter.setGroupId(selectedGroup.getId());
            }
        };
        add(filterForm);

        DropDownChoice<GroupIdNameDto> groupFilter = new DropDownChoice<>(
                "skupina",
                new PropertyModel<GroupIdNameDto>(this, "selectedGroup"),
                new LoadableDetachableModel<List<GroupIdNameDto>>() {
                    @Override
                    protected List<GroupIdNameDto> load() {
                        return PriceComparatorApplication.getApi().findAllProductGroupSelection();
                    }
                },
                new ChoiceRenderer<GroupIdNameDto>(GroupIdNameDto.AT_NAME)
        );
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
                //TODO
                return new ProductInEshopPriceResultListDto();
            }

            @Override
            protected void populateItem(IdListView.IdListItem<ProductInEshopPriceResultListDto> item) {
                IModel<ProductInEshopPriceResultListDto> product = item.getModel();

                Label eshopName = new Label("eshopName", new PropertyModel<String>(product, ProductInEshopPriceResultListDto.AT_ESHOP_NAME));
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
