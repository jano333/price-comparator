package sk.hudak.pricecomparator.client.wicket.page.group.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.middle.to.GroupOfProductFindDto;
import sk.hudak.pricecomparator.middle.to.GroupOfProductListDto;

import java.io.Serializable;

/**
 * Created by jan on 20. 3. 2016.
 */
public class GroupListTable extends Panel {

    private GroupOfProductFindDto filter = new GroupOfProductFindDto();

    public GroupListTable(String id) {
        super(id);

        IModel<PageList<GroupOfProductListDto>> tableModel = new LoadableDetachableModel<PageList<GroupOfProductListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<GroupOfProductListDto> load() {
                return ServiceLocator.getService().findGroupOfProduct(filter);
            }
        };

        // filter
        Form<Void> filterForm = new Form<Void>("filterForm") {
            @Override
            protected void onSubmit() {
            }
        };
        add(filterForm);

        TextField<String> groupNameFilter = new TextField<>("groupName", new PropertyModel<String>(filter, GroupOfProductFindDto.AT_GROUP_NAME));
        filterForm.add(groupNameFilter);

        // mesage line

        Label allPageCount = new Label("allPageCount", new PropertyModel<String>(tableModel, PageList.AT_ALL_PAGE_COUNT));
        filterForm.add(allPageCount);

        Label currentPage = new Label("currentPage", new PropertyModel<String>(tableModel, PageList.AT_CURRENT_PAGE));
        filterForm.add(currentPage);

        Table<GroupOfProductListDto> table = new Table<GroupOfProductListDto>("table", filter, tableModel) {

            @Override
            protected Serializable getObjectId(GroupOfProductListDto dto) {
                return dto.getId();
            }

            @Override
            protected GroupOfProductListDto loadLazyById(Serializable id) {
                //TODO
                return new GroupOfProductListDto();
            }

            @Override
            protected void populateItem(IdListView.IdListItem<GroupOfProductListDto> item) {
                IModel<GroupOfProductListDto> groupOfProduct = item.getModel();

                Label groupName = new Label("name", new PropertyModel<String>(groupOfProduct, GroupOfProductListDto.AT_NAME));

                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(groupName);

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
}
