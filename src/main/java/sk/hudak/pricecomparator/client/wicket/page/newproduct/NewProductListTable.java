package sk.hudak.pricecomparator.client.wicket.page.newproduct;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.paging.PageData;
import sk.hudak.jef.paging.Paging;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanelNg;
import sk.hudak.pricecomparator.client.wicket.component.table.TableNg;
import sk.hudak.pricecomparator.middle.to.NewProductFindDto;
import sk.hudak.pricecomparator.middle.to.NewProductListDto;

import java.io.Serializable;

/**
 * Created by jan on 31. 12. 2016.
 */
public class NewProductListTable extends Panel {

    private NewProductFindDto filter = new NewProductFindDto();

    public NewProductListTable(String id) {
        super(id);

        IModel<PageData<NewProductListDto>> tableModel = new LoadableDetachableModel<PageData<NewProductListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageData<NewProductListDto> load() {
                if (filter.getPaging() == null) {
                    //TODO ako to nastavit...
                    filter.setPaging(new Paging(0, 20));
                }

                return PriceComparatorApplication.getApi().findNewProducts(filter);
            }
        };

        // filter
        Form<Void> filterForm = new Form<Void>("filterForm") {
            @Override
            protected void onSubmit() {
            }
        };
        add(filterForm);

        TextField<String> newProductName = new TextField<>("newProductName", new PropertyModel<String>(filter, NewProductFindDto.AT_PRODUCT_NAME));
        filterForm.add(newProductName);

        //TODO aspon status ... a dalsie

        // pagging
        filterForm.add(new PagingInfoPanelNg("infoPaging", filter, tableModel));

        // table
        TableNg<NewProductListDto> table = new TableNg<NewProductListDto>("table", filter, tableModel) {

            @Override
            protected Serializable getObjectId(NewProductListDto object) {
                return object.getId();
            }

            @Override
            protected NewProductListDto loadLazyById(Serializable id) {
                //TODO
                return super.loadLazyById(id);
            }

            @Override
            protected void populateItem(IdListView.IdListItem<NewProductListDto> item) {
                IModel<NewProductListDto> model = item.getModel();

                Label newProductName = new Label("name", new PropertyModel<String>(model, NewProductListDto.AT_PRODUCT_NAME));

                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(newProductName);

                item.add(tr);
            }
        };
        add(table);
    }
}
