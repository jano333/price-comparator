package sk.hudak.pricecomparator.client.wicket.page.eshop.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanel;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductInEshopCreatePage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductListPerEshopPage;
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

//        AutoCompleteSettings autoCompleteSettings = new AutoCompleteSettings();
//        AutoCompleteTextField<String> eshopNameAutoCompleteFilter = new AutoCompleteTextField<String>("eshopName",
//                new PropertyModel<String>(filter, EshopFindDto.AT_ESHOP_NAME),
//                autoCompleteSettings) {
//
//            @Override
//            protected Iterator<String> getChoices(String input) {
//                if (StringUtils.isBlank(input)) {
//                    return Collections.emptyIterator();
//                }
//                if (input.startsWith("aaa")) {
//                    return Arrays.asList("ahoj", "cau", "aloha").iterator();
//                }
//                return Collections.emptyIterator();
//            }
//        };
//        filterForm.add(eshopNameAutoCompleteFilter);

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
                return PriceComparatorApplication.getApi().getEshopListById((Long) id);
            }

            @Override
            protected void populateItem(IdListView.IdListItem<EshopListDto> item) {
                IModel<EshopListDto> product = item.getModel();

                Label eshopName = new Label("name", new PropertyModel<String>(product, EshopListDto.AT_NAME));
                Label eshopHomePage = new Label("homePage", new PropertyModel<String>(product, EshopListDto.AT_HOME_PAGE));

                // actions
                Link<EshopListDto> addProductToEshop = new Link<EshopListDto>("addProductToEshop", product) {
                    @Override
                    public void onClick() {
                        setResponsePage(ProductInEshopCreatePage.class,
                                WU.param(ProductInEshopCreatePage.PARAM_ESHOP_ID, getModelObject().getId()));
                    }
                };

                Link<EshopListDto> listOfEshopProducts = new Link<EshopListDto>("listOfEshopProducts", product) {
                    @Override
                    public void onClick() {
                        setResponsePage(ProductListPerEshopPage.class,
                                WU.param(ProductListPerEshopPage.PARAM_ESHOP_ID, getModelObject().getId()));
                    }
                };


                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(eshopName, eshopHomePage,
                        listOfEshopProducts,
                        addProductToEshop);

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
