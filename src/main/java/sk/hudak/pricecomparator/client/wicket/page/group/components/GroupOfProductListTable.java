package sk.hudak.pricecomparator.client.wicket.page.group.components;

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
import sk.hudak.pricecomparator.middle.to.GroupIdNameDto;
import sk.hudak.pricecomparator.middle.to.GroupOfProductFindDto;
import sk.hudak.pricecomparator.middle.to.ProductListDto;

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
    }
}
