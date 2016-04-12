package sk.hudak.pricecomparator.client.wicket.page.eshop.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanel;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.middle.to.EshopFindDto;
import sk.hudak.pricecomparator.middle.to.EshopListDto;

import java.io.Serializable;

/**
 * Created by jan on 19. 3. 2016.
 */
public class EshopListTable extends Panel {

    private EshopFindDto filter = new EshopFindDto();

    public EshopListTable(String id) {
        super(id);

        IModel<PageList<EshopListDto>> tableModel = new LoadableDetachableModel<PageList<EshopListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<EshopListDto> load() {
                return PriceComparatorApplication.getApi().findEshops(filter);
            }
        };

        // filter
        Form<Void> filterForm = new Form<Void>("filterForm") {
            @Override
            protected void onSubmit() {
            }
        };
        add(filterForm);

        TextField<String> eshopNameFilter = new TextField<>("eshopName", new PropertyModel<String>(filter, EshopFindDto.AT_ESHOP_NAME));
        filterForm.add(eshopNameFilter);

        // pagging
        filterForm.add(new PagingInfoPanel("infoPaging", filter, tableModel));

        // table
        Table<EshopListDto> table = new Table<EshopListDto>("table", filter, tableModel) {

            @Override
            protected Serializable getObjectId(EshopListDto dto) {
                return dto.getId();
            }

            @Override
            protected EshopListDto loadLazyById(Serializable id) {
                //TODO
                return new EshopListDto();
            }

            @Override
            protected void populateItem(IdListView.IdListItem<EshopListDto> item) {
                IModel<EshopListDto> product = item.getModel();

                Label eshopName = new Label("name", new PropertyModel<String>(product, EshopListDto.AT_NAME));
                Label eshopHomePage = new Label("homePage", new PropertyModel<String>(product, EshopListDto.AT_HOME_PAGE));

                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(eshopName, eshopHomePage);

                item.add(tr);
            }
        };
        add(table);


    }

    public EshopFindDto getFilter() {
        return filter;
    }

    public void setFilter(EshopFindDto filter) {
        this.filter = filter;
    }
}
