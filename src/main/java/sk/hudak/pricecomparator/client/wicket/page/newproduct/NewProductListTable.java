package sk.hudak.pricecomparator.client.wicket.page.newproduct;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.paging.PageData;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.middle.to.NewProductFindDto;
import sk.hudak.pricecomparator.middle.to.NewProductListDto;

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
        //TODO zatial nemam..
        //filterForm.add(new PagingInfoPanelNg("infoPaging", filter, tableModel));


    }
}
