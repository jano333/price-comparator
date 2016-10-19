package sk.hudak.pricecomparator.client.wicket.component.table;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.BasicFilter;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.component.common.VisibilityModifier;

import java.util.Arrays;

/**
 * Created by jan on 25. 3. 2016.
 */
public class PagingInfoPanel<T> extends Panel {

    //TODO prerobit visibilitu na enabilitu pre linky first, previos, next, last...

    public PagingInfoPanel(String id, final BasicFilter filter, final IModel<PageList<T>> tableModel) {
        super(id);


        Label currentPage = new Label("currentPage", new PropertyModel<String>(tableModel, PageList.AT_CURRENT_PAGE));
        add(currentPage);

        Label allPageCount = new Label("allPageCount", new PropertyModel<String>(tableModel, PageList.AT_ALL_PAGE_COUNT));
        add(allPageCount);


        Link<Void> firstLink = new Link<Void>("firstLink") {

            @Override
            public void onClick() {
                filter.setOffset(BasicFilter.OFFSET_OF_FIRST_ENTRIES);
            }
        };
        firstLink.add(VisibilityModifier.notEqual(tableModel, PageList.AT_CURRENT_PAGE, 1));

        Link<Void> prevLink = new Link<Void>("prevLink") {

            @Override
            public void onClick() {
                filter.setOffset(filter.getOffset() - filter.getCount());
            }
        };
        prevLink.add(VisibilityModifier.notEqual(tableModel, PageList.AT_CURRENT_PAGE, 1));

        Link<Void> nextLink = new Link<Void>("nextLink") {

            @Override
            public void onClick() {
                filter.setOffset(filter.getOffset() + filter.getCount());
            }
        };
        nextLink.add(new VisibilityModifier() {
            protected boolean isVisible(Component component) {
                PageList<T> p = tableModel.getObject();
                return p.getCurrentPage() != p.getAllPageCount();
            }
        });


        Link<Void> lastLink = new Link<Void>("lastLink") {

            @Override
            public void onClick() {
                filter.setOffset(BasicFilter.OFFSET_OF_LAST_ENTRIES);
            }
        };
        lastLink.add(new VisibilityModifier() {
            protected boolean isVisible(Component component) {
                PageList<T> p = tableModel.getObject();
                return p.getCurrentPage() != p.getAllPageCount();
            }
        });

        add(firstLink, prevLink, nextLink, lastLink);


//        DropDownChoice<Integer> perPage = new DropDownChoice<>("perPage",
//                new PropertyModel<Integer>(filter, BasicFilter.AT_COUNT),
//                Arrays.asList(5, 10, 20, 50, 100));
//        add(perPage);

        //TODO tu by malo byt len read only model...
        Label curretCountPerPage = new Label("offset", new PropertyModel<>(filter, BasicFilter.AT_COUNT));
        //[WARN ][16.10 16:00:09][qtp9517133-16]o.a.w.m.ChainingModel: It is not a good idea to reference the Session instance in models directly as it may lead to serialization problems. If you need to access a property of the session via the model use the page instance as the model object and 'session.attribute' as the path.
//        Label curretCountPerPage = new Label("offset", new PropertyModel<>(((PrcoSession) getWebSession()), "countPerPage"));
        add(curretCountPerPage);

        ListView<Integer> perPage = new ListView<Integer>("perPage", Arrays.asList(5, 10, 20, 50, 100)) {
            @Override
            protected void populateItem(final ListItem<Integer> item) {
                final IModel<Integer> model = item.getModel();

                Link<Void> perPageSelection = new Link<Void>("choice") {
                    @Override
                    public void onClick() {
                        Integer newValue = model.getObject();

                        filter.setCount(newValue);
                        filter.setOffset(BasicFilter.OFFSET_OF_FIRST_ENTRIES);
                    }
                };

                Label choiceValue = new Label("choiceValue", model);
                choiceValue.setRenderBodyOnly(true);
                perPageSelection.add(choiceValue);

                item.add(perPageSelection);
            }
        };
        add(perPage);

    }
}
