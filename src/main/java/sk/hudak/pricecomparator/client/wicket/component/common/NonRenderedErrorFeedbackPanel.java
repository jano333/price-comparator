package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import java.util.ArrayList;
import java.util.List;


/**
 * Zobrazuje error feedback message, ktore nie su akceptovane ziadnym inym feedback panelom
 */
public class NonRenderedErrorFeedbackPanel extends FeedbackPanel {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NonRenderedErrorFeedbackPanel(String id) {
        super(id);
        setFilter(new AllExceptFeedbackFilter() {
            private static final long serialVersionUID = 1L;

            @Override
            protected IFeedbackMessageFilter[] getFilters() {
                final List<IFeedbackMessageFilter> filters = new ArrayList<>();
                getPage().visitChildren(FeedbackPanel.class, new IVisitor<FeedbackPanel, Void>() {
                    @Override
                    public void component(FeedbackPanel component, IVisit<Void> visit) {
                        if (NonRenderedErrorFeedbackPanel.this.equals(component)) {
                            visit.dontGoDeeper();
                        }
                        filters.add(component.getFilter());
                    }

//                    @Override
//                    public Object component(FeedbackPanel component) {
//                        if (NonRenderedErrorFeedbackPanel.this.equals(component)) {
//                            return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
//                        }
//                        filters.add(component.getFilter());
//                        return CONTINUE_TRAVERSAL;
//                    }
                });
                return filters.toArray(new IFeedbackMessageFilter[filters.size()]);
            }
        });
    }


    public static class AllExceptFeedbackFilter implements IFeedbackMessageFilter {
        private static final long serialVersionUID = 1L;

        private IFeedbackMessageFilter[] filters = null;

        public AllExceptFeedbackFilter() {
            this(null);
        }

        public AllExceptFeedbackFilter(IFeedbackMessageFilter[] filters) {
            this.filters = filters;
        }

        @Override
        public boolean accept(FeedbackMessage message) {
            IFeedbackMessageFilter[] localFilters = getFilters();
            for (IFeedbackMessageFilter filter : localFilters) {
                if (filter.accept(message)) {
                    return false;
                }
            }
            return true;
        }

        protected IFeedbackMessageFilter[] getFilters() {
            return filters;
        }

    }

}
