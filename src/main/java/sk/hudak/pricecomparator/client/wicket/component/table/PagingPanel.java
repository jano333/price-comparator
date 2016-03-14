package sk.hudak.pricecomparator.client.wicket.component.table;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import sk.hudak.jef.BasicFilter;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.component.common.VisibilityModifier;

public class PagingPanel<T> extends Panel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PagingPanel(String id, final BasicFilter filter, final IModel<PageList<T>> paging) {
        super(id);

        Link<Void> firstLink = new Link<Void>("firstLink") {

            @Override
            public void onClick() {
                filter.setOffset(BasicFilter.OFFSET_OF_FIRST_ENTRIES);
            }
        };
        firstLink.add(VisibilityModifier.notEqual(paging, PageList.AT_CURRENT_PAGE, 1));

        Link<Void> prevLink = new Link<Void>("prevLink") {

            @Override
            public void onClick() {
                filter.setOffset(filter.getOffset() - filter.getCount());
            }
        };
        prevLink.add(VisibilityModifier.notEqual(paging, PageList.AT_CURRENT_PAGE, 1));

        Link<Void> nextLink = new Link<Void>("nextLink") {

            @Override
            public void onClick() {
                filter.setOffset(filter.getOffset() + filter.getCount());
            }
        };
        nextLink.add(new VisibilityModifier() {
            protected boolean isVisible(Component component) {
                PageList<T> p = paging.getObject();
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
                PageList<T> p = paging.getObject();
                return p.getCurrentPage() != p.getAllPageCount();
            }
        });

        add(firstLink, prevLink, nextLink, lastLink);

    }


}
