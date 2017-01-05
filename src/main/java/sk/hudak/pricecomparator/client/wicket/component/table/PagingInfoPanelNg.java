package sk.hudak.pricecomparator.client.wicket.component.table;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.paging.PageData;
import sk.hudak.pricecomparator.client.wicket.component.common.VisibilityModifier;
import sk.hudak.pricecomparator.middle.to.FindDtoNg;

import java.util.Arrays;

/**
 * Created by jan on 31. 12. 2016.
 */
public class PagingInfoPanelNg<T> extends Panel {

    public static final String AT_CURRENT_PAGE = "currentPage";
    public static final String AT_ALL_PAGE_COUNT = "allPageCount";

    private int currentPage = 0;
    private int allPageCount;


    public PagingInfoPanelNg(String id, final FindDtoNg filter, final IModel<PageData<T>> tableModel) {
        super(id);

        currentPage = 0;
        //TODO dopocitat...
        allPageCount = 55;

        final Label allRecordsCountLb = new Label("allRecordsCount", new PropertyModel<String>(tableModel, "allRecordsCount"));
        add(allRecordsCountLb);

        final Label currentPageLb = new Label("currentPage", new PropertyModel<String>(this, AT_CURRENT_PAGE));
        add(currentPageLb);

        Label allPageCountLb = new Label("allPageCount", new PropertyModel<String>(this, AT_ALL_PAGE_COUNT));
        add(allPageCountLb);

        Link<Void> firstLink = new Link<Void>("firstLink") {

            @Override
            public void onClick() {
                currentPage = 0;
                filter.getPaging().setOffset(currentPage * filter.getPaging().getPageSize());
            }
        };
        firstLink.add(VisibilityModifier.notEqual(this, AT_CURRENT_PAGE, 0));

        Link<Void> prevLink = new Link<Void>("prevLink") {

            @Override
            public void onClick() {
                currentPage = currentPage - 1;
                filter.getPaging().setOffset(currentPage * filter.getPaging().getPageSize());
            }
        };
        prevLink.add(VisibilityModifier.notEqual(this, AT_CURRENT_PAGE, 0));

        Link<Void> nextLink = new Link<Void>("nextLink") {

            @Override
            public void onClick() {
                currentPage = currentPage + 1;
                filter.getPaging().setOffset(currentPage * filter.getPaging().getPageSize());
            }
        };
        nextLink.add(new VisibilityModifier() {
            protected boolean isVisible(Component component) {
                return currentPage != allPageCount - 1;
            }
        });

        Link<Void> lastLink = new Link<Void>("lastLink") {

            @Override
            public void onClick() {
                currentPage = allPageCount - 1;
                filter.getPaging().setOffset(currentPage * filter.getPaging().getPageSize());
            }
        };
        lastLink.add(new VisibilityModifier() {
            protected boolean isVisible(Component component) {
                return currentPage != allPageCount - 1;
            }
        });

        add(firstLink, prevLink, nextLink, lastLink);

        Label pageSize = new Label("pageSize", new PropertyModel<>(filter, "paging.pageSize"));
        //[WARN ][16.10 16:00:09][qtp9517133-16]o.a.w.m.ChainingModel: It is not a good idea to reference the Session instance in models directly as it may lead to serialization problems. If you need to access a property of the session via the model use the page instance as the model object and 'session.attribute' as the path.
//        Label pageSize = new Label("offset", new PropertyModel<>(((PrcoSession) getWebSession()), "countPerPage"));
        add(pageSize);

        ListView<Integer> perPage = new ListView<Integer>("perPage", Arrays.asList(5, 10, 20, 50, 100)) {
            @Override
            protected void populateItem(final ListItem<Integer> item) {
                final IModel<Integer> model = item.getModel();

                Link<Void> perPageSelection = new Link<Void>("choice") {
                    @Override
                    public void onClick() {
                        Integer newValue = model.getObject();

                        currentPage = 0;
                        filter.getPaging().setPageSize(newValue);
                        filter.getPaging().setOffset(currentPage * filter.getPaging().getPageSize());
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
