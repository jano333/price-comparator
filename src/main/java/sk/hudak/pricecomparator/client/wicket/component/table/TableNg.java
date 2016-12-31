package sk.hudak.pricecomparator.client.wicket.component.table;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.jef.paging.PageData;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.common.VisibilityModifier;
import sk.hudak.pricecomparator.middle.to.FindDtoNg;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hudak on 31.12.2016.
 */
public class TableNg<T> extends Border {

    private static final long serialVersionUID = 1L;

    public TableNg(String id, FindDtoNg filter, final IModel<PageData<T>> model) {
        this(id, filter, model, new Model<>(true));
    }

    //TODO remove search
    public TableNg(String id, FindDtoNg filter, final IModel<PageData<T>> model, IModel<Boolean> searched) {
        super(id);

//        PropertyModel<List<T>> entriesModel = new PropertyModel<>(model, PageList.AT_ENTRIES);
        PropertyModel<List<T>> entriesModel = new PropertyModel<>(model, "result");

        ListView<T> list = new IdListView<T>("list", entriesModel) {
            @Override
            protected void populateItem(IdListItem<T> item) {
                TableNg.this.populateItem(item);
            }

            protected Serializable getObjectId(T object) {
                return TableNg.this.getObjectId(object);
            }

            @Override
            protected T loadLazyById(Serializable id) {
                return TableNg.this.loadLazyById(id);
            }

        };

        WebMarkupContainer searchedView = new WebMarkupContainer("searchedView");
        searchedView.add(getBodyContainer().add(list));
//        searchedView.add(new PagingPanel<>("paging", filter, model));
//        searchedView.add(VisibilityModifier.ofModel(searched));
        searchedView.add(VisibilityModifier.notZeroSize(entriesModel));
        addToBorder(searchedView);

        WebMarkupContainer noEntriesFound = new WebMarkupContainer("noEntriesFound");
        noEntriesFound.add(new Label("label", new ResourceModel("no.entries.found")));
        noEntriesFound.add(VisibilityModifier.ofModel(searched));
        noEntriesFound.add(VisibilityModifier.zeroSize(entriesModel));
        addToBorder(noEntriesFound);

//        WebMarkupContainer notSearchedYet = new WebMarkupContainer("notSearchedYet");
//        notSearchedYet.add(new Label("label", new ResourceModel("not.searched.yet")));
//        notSearchedYet.add(VisibilityModifier.ofModel(searched).setInvert(true));
//        addToBorder(notSearchedYet);


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
