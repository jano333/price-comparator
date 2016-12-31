package sk.hudak.pricecomparator.client.wicket.page.group.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
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
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanel;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.middle.to.group.GroupIdNameDto;
import sk.hudak.pricecomparator.middle.to.group.GroupOfProductFindDto;
import sk.hudak.pricecomparator.middle.to.product.ProductListDto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 20. 3. 2016.
 */
public class GroupOfProductListTable extends Panel {

    private GroupOfProductFindDto filter = new GroupOfProductFindDto();

    private GroupIdNameDto selectedGroup;

    public GroupOfProductListTable(String id) {
        super(id);

        IModel<PageList<ProductListDto>> tableModel = new LoadableDetachableModel<PageList<ProductListDto>>() {

            @Override
            protected PageList<ProductListDto> load() {
                if (filter.getGroupId() != null) {
                    return PriceComparatorApplication.getApi().findProductsInGroup(filter);
                } else {
                    return new PageList<>(Collections.EMPTY_LIST, 0, 0);
                }
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
                return PriceComparatorApplication.getApi().getProductListById((Long) id);
            }

            @Override
            protected void populateItem(IdListView.IdListItem<ProductListDto> item) {
                final IModel<ProductListDto> product = item.getModel();


                Label productName = new Label("name", new PropertyModel<String>(product, ProductListDto.AT_NAME));

//                NonCachingImage image = new NonCachingImage("image", Model.of("/images/mypic" + item.getIndex() + ".png"));
//                ContextImage image = new ContextImage("image", "/images/mypic" + product.getObject().getId() + ".png");

                ContextImage image = WU.productImage(product.getObject().getImagePath());

                AjaxLink<Void> deleteProduct = new AjaxLink<Void>("deleteProduct") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        Long id = product.getObject().getId();
                        System.out.println("Produkt id je " + id);


                    }
                };

                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(productName, image, deleteProduct);

                item.add(tr);
            }
        };
        add(table);


//        add(new YesNoDialog("removeProductDialog",
//                "Odstranenie produktu zo skupiny",
//                "Skutocne si prajete odstranit produkt zo skupiny?"));

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
