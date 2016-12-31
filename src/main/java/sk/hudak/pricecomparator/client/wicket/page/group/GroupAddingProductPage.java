package sk.hudak.pricecomparator.client.wicket.page.group;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.to.group.GroupIdNameDto;
import sk.hudak.pricecomparator.middle.to.product.ProductIdNameDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jan on 13. 4. 2016.
 */
public class GroupAddingProductPage extends LayoutPage {

    private ProductIdNameDto selectedProduct;
    private GroupIdNameDto selectedGroup;

    public GroupAddingProductPage() {

        add(new FeedbackPanel("feedback"));

        // filter
        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                try {
                    if (selectedGroup == null || selectedProduct == null) {
                        return;
                    }
                    Set<Long> ids = new HashSet<>(1);
                    ids.add(selectedProduct.getId());
                    PriceComparatorApplication.getApi().addProductsToGroup(ids, selectedGroup.getId());

                    setResponsePage(GroupProductPriceListPage.class);

                } catch (Exception e) {
                    //TODO
                    e.printStackTrace();
                }
            }
        };
        add(form);

        DropDownChoice<GroupIdNameDto> group = new DropDownChoice<GroupIdNameDto>(
                "skupina",
                new PropertyModel<GroupIdNameDto>(this, "selectedGroup"),
                new LoadableDetachableModel<List<GroupIdNameDto>>() {
                    @Override
                    protected List<GroupIdNameDto> load() {
                        return PriceComparatorApplication.getApi().findAllProductGroupSelection();
                    }
                },
                new ChoiceRenderer<GroupIdNameDto>(GroupIdNameDto.AT_NAME)
        ) {
            @Override
            protected String getNullKey() {
                return "core.dropdown.nullValue.group";
            }
        };
        group.setRequired(true);
        form.add(group);


        DropDownChoice<ProductIdNameDto> product = new DropDownChoice<ProductIdNameDto>(
                "product",
                new PropertyModel<ProductIdNameDto>(this, "selectedProduct"),
                new LoadableDetachableModel<List<ProductIdNameDto>>() {
                    @Override
                    protected List<ProductIdNameDto> load() {
                        return PriceComparatorApplication.getApi().findAllProductForSelection();
                    }
                },
                new ChoiceRenderer<ProductIdNameDto>(ProductIdNameDto.AT_NAME)
        ) {
            @Override
            protected String getNullKey() {
                return "core.dropdown.nullValue.product";
            }
        };
        product.setRequired(true);
        form.add(product);
    }

    public ProductIdNameDto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductIdNameDto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public GroupIdNameDto getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(GroupIdNameDto selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
}
