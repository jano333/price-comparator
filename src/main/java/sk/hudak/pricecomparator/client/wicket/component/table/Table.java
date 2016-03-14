package sk.hudak.pricecomparator.client.wicket.component.table;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.jef.BasicFilter;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.common.VisibilityModifier;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings("unchecked")
public class Table<T> extends Border {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Table(String id, BasicFilter filter, final IModel<PageList<T>> model) {
        this(id, filter, model, new Model<>(true));
    }

    //TODO remove search
    public Table(String id, BasicFilter filter, final IModel<PageList<T>> model, IModel<Boolean> searched) {
        super(id);

        PropertyModel<List<T>> entriesModel = new PropertyModel<>(model, PageList.AT_ENTRIES);

        ListView<T> list = new IdListView<T>("list", entriesModel) {
            @Override
            protected void populateItem(IdListItem<T> item) {
                Table.this.populateItem(item);
            }

            protected Serializable getObjectId(T object) {
                return Table.this.getObjectId(object);
            }

            ;

            @Override
            protected T loadLazyById(Serializable id) {
                return Table.this.loadLazyById(id);
            }

        };

        WebMarkupContainer searchedView = new WebMarkupContainer("searchedView");
        searchedView.add(getBodyContainer().add(list));
        searchedView.add(new PagingPanel<>("paging", filter, model));
        searchedView.add(VisibilityModifier.ofModel(searched));
        searchedView.add(VisibilityModifier.notZeroSize(entriesModel));
//		add(searchedView);
        addToBorder(searchedView);

        WebMarkupContainer noEntriesFound = new WebMarkupContainer("noEntriesFound");
        noEntriesFound.add(new Label("label", new ResourceModel("no.entries.found")));
        noEntriesFound.add(VisibilityModifier.ofModel(searched));
        noEntriesFound.add(VisibilityModifier.zeroSize(entriesModel));
//		add(noEntriesFound);
        addToBorder(noEntriesFound);

        WebMarkupContainer notSearchedYet = new WebMarkupContainer("notSearchedYet");
        notSearchedYet.add(new Label("label", new ResourceModel("not.searched.yet")));
        notSearchedYet.add(VisibilityModifier.ofModel(searched).setInvert(true));
//		add(notSearchedYet);
        addToBorder(notSearchedYet);

    }


    protected void populateItem(IdListView.IdListItem<T> item) {

    }

    protected Serializable getObjectId(T object) {
        return null;
    }

    protected T loadLazyById(Serializable id) {
        return null;
    }


}
