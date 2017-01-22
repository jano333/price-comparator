package sk.hudak.pricecomparator.client.wicket.page.newproduct;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.jef.paging.PageData;
import sk.hudak.jef.paging.Paging;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.component.ExternalImageUrl;
import sk.hudak.pricecomparator.client.wicket.component.common.IdListView;
import sk.hudak.pricecomparator.client.wicket.component.table.PagingInfoPanelNg;
import sk.hudak.pricecomparator.client.wicket.component.table.TableNg;
import sk.hudak.pricecomparator.middle.to.NewProductFindDto;
import sk.hudak.pricecomparator.middle.to.NewProductListDto;
import sk.hudak.pricecomparator.server.to.NewProductStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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

        TextField<String> nameFilter = new TextField<>("newProductName", new PropertyModel<String>(filter, NewProductFindDto.AT_PRODUCT_NAME));
        filterForm.add(nameFilter);

        DropDownChoice<NewProductStatus> statusFilter = new DropDownChoice<>("status",
                new PropertyModel<NewProductStatus>(this.filter, NewProductFindDto.AT_STATUS),
                new LoadableDetachableModel<List<? extends NewProductStatus>>() {
                    @Override
                    protected List<? extends NewProductStatus> load() {
                        return Arrays.asList(NewProductStatus.values());
                    }
                }
        );
        filterForm.add(statusFilter);

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
                return PriceComparatorApplication.getApi().getNewProductListDtoById((Long) id);
            }

            @Override
            protected void populateItem(IdListView.IdListItem<NewProductListDto> item) {
                final IModel<NewProductListDto> model = item.getModel();
                String urlAsString = model.getObject().getProductPictureUrl();

                ExternalImageUrl image = new ExternalImageUrl("productImage", urlAsString);

                ExternalLink newProductName = new ExternalLink("name",
                        new PropertyModel<String>(model, NewProductListDto.AT_PRODUCT_URL),
                        new PropertyModel<String>(model, NewProductListDto.AT_PRODUCT_NAME)
                );
                newProductName.add(WU.atrTargetBlank());

                //actions
                Link<NewProductListDto> markAsNotInterestedLink = new Link<NewProductListDto>(
                        "markAsNotInterested", model) {
                    @Override
                    public void onClick() {
                        PriceComparatorApplication.getApi().changeNewProductStatus(model.getObject().getId(),
                                NewProductStatus.NOT_INTERESTED);
                    }
                };
                Link<NewProductListDto> markAsInterestedLink = new Link<NewProductListDto>(
                        "markAsInterested", model) {
                    @Override
                    public void onClick() {
                        PriceComparatorApplication.getApi().changeNewProductStatus(model.getObject().getId(),
                                NewProductStatus.INTERESTED);
                    }
                };

                WebMarkupContainer tr = new WebMarkupContainer("tr");
                tr.add(image);
                tr.add(newProductName);
                //actions
                tr.add(markAsNotInterestedLink);
                tr.add(markAsInterestedLink);

                item.add(tr);
            }
        };
        add(table);
    }
}
