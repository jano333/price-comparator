package sk.hudak.pricecomparator.client.wicket.page.group.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.ContextImage;
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
import sk.hudak.pricecomparator.client.wicket.page.product.components.WU;
import sk.hudak.pricecomparator.middle.to.GroupIdNameDto;
import sk.hudak.pricecomparator.middle.to.GroupOfProductFindDto;
import sk.hudak.pricecomparator.middle.to.ProductListDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jan on 20. 3. 2016.
 */
public class GroupOfProductListTable extends Panel {

    private GroupOfProductFindDto filter = new GroupOfProductFindDto();

    //TODO
    private GroupIdNameDto selectedGroup = new GroupIdNameDto(1l, "haha");

    public GroupOfProductListTable(String id) {
        super(id);

        IModel<PageList<ProductListDto>> tableModel = new LoadableDetachableModel<PageList<ProductListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<ProductListDto> load() {
                return ServiceLocator.getService().findProductsInGroup(filter);
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

        DropDownChoice<GroupIdNameDto> groupFilter = new DropDownChoice<>("skupina",
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

        // pagging
        filterForm.add(new PagingInfoPanel("infoPaging", filter, tableModel));

        Table<ProductListDto> table = new Table<ProductListDto>("table", filter, tableModel) {

            @Override
            protected Serializable getObjectId(ProductListDto dto) {
                return dto.getId();
            }

            @Override
            protected ProductListDto loadLazyById(Serializable id) {
                //TODO
                return new ProductListDto();
            }

            @Override
            protected void populateItem(IdListView.IdListItem<ProductListDto> item) {
                IModel<ProductListDto> product = item.getModel();


                Label productName = new Label("name", new PropertyModel<String>(product, ProductListDto.AT_NAME));

//                NonCachingImage image = new NonCachingImage("image", Model.of("/images/mypic" + item.getIndex() + ".png"));
//                ContextImage image = new ContextImage("image", "/images/mypic" + product.getObject().getId() + ".png");

                ContextImage image = WU.productImage(product.getObject().getImagePath());

                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(productName, image);

                item.add(tr);
            }
        };
        add(table);

    }

    public GroupOfProductFindDto getFilter() {
        return filter;
    }

    public void setFilter(GroupOfProductFindDto filter) {
        this.filter = filter;
    }

    public GroupIdNameDto getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(GroupIdNameDto selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
}
