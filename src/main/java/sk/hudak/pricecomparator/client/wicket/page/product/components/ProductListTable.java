package sk.hudak.pricecomparator.client.wicket.page.product.components;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanel;
import sk.hudak.pricecomparator.client.wicket.component.table.Table;
import sk.hudak.pricecomparator.client.wicket.page.product.ProductPricesPerEshopsListPage;
import sk.hudak.pricecomparator.middle.to.ProductFindDto;
import sk.hudak.pricecomparator.middle.to.ProductListDto;

import java.io.Serializable;

/**
 * Created by jan on 19. 3. 2016.
 */
public class ProductListTable extends Panel {

    private ProductFindDto filter = new ProductFindDto();

    public ProductListTable(String id) {
        super(id);
        IModel<PageList<ProductListDto>> tableModel = new LoadableDetachableModel<PageList<ProductListDto>>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected PageList<ProductListDto> load() {
                return PriceComparatorApplication.getApi().findProducts(filter);
            }
        };

        // filter
        Form<Void> filterForm = new Form<Void>("filterForm") {
            @Override
            protected void onSubmit() {
            }
        };
        add(filterForm);

        TextField<String> productNameFilter = new TextField<>("productName", new PropertyModel<String>(filter, ProductFindDto.AT_NAME));
        filterForm.add(productNameFilter);

        // pagging
        filterForm.add(new PagingInfoPanel("infoPaging", filter, tableModel));

        Table<ProductListDto> table = new Table<ProductListDto>("table", filter, tableModel) {

            @Override
            protected Serializable getObjectId(ProductListDto dto) {
                return dto.getId();
            }

            @Override
            protected ProductListDto loadLazyById(Serializable id) {
                return PriceComparatorApplication.getApi().getProductListById((Long) id);
            }

            @Override
            protected void populateItem(IdListView.IdListItem<ProductListDto> item) {
                IModel<ProductListDto> product = item.getModel();


                Label productName = new Label("name", new PropertyModel<String>(product, ProductListDto.AT_NAME));
//                NonCachingImage image = new NonCachingImage("image", Model.of("/images/mypic" + item.getIndex() + ".png"));
                //TODO nacitavat to cez property model nie takto:
                ContextImage image = WU.productImage(product.getObject().getImagePath());

                // actions
                Link<ProductListDto> listOfEshopsPerProduct = new Link<ProductListDto>("listOfEshopsPerProduct", product) {
                    @Override
                    public void onClick() {

                        PageParameters params = WU.param(ProductPricesPerEshopsListPage.PARAM_PRODUCT_ID, getModelObject().getId());
                        setResponsePage(ProductPricesPerEshopsListPage.class, params);
                    }
                };

                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(productName, image);
                tr.add(listOfEshopsPerProduct);

                item.add(tr);
            }
        };
        add(table);


    }

    public ProductFindDto getFilter() {
        return filter;
    }

    public void setFilter(ProductFindDto filter) {
        this.filter = filter;
    }
}
