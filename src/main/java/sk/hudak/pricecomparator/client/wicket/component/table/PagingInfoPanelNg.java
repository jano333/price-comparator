package sk.hudak.pricecomparator.client.wicket.component.table;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import sk.hudak.jef.paging.PageData;
import sk.hudak.pricecomparator.middle.to.FindDtoNg;

/**
 * Created by jan on 31. 12. 2016.
 */
public class PagingInfoPanelNg<T> extends Panel {

    public PagingInfoPanelNg(String id, final FindDtoNg filter, final IModel<PageData<T>> tableModel) {
        super(id);
        //TODO

    }
}
