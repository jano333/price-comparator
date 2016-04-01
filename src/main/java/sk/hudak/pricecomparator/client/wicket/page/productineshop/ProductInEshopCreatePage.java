package sk.hudak.pricecomparator.client.wicket.page.productineshop;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.to.EshopIdNameDto;
import sk.hudak.pricecomparator.middle.to.ProductIdNameDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopCreateDto;

import java.util.List;

/**
 * Created by jan on 1. 4. 2016.
 */
public class ProductInEshopCreatePage extends LayoutPage {

    private ProductInEshopCreateDto createDto = new ProductInEshopCreateDto();

    private ProductIdNameDto selectedProduct;
    private EshopIdNameDto selectedEshop;


    public ProductInEshopCreatePage() {

        add(new FeedbackPanel("feedback"));

        // filter
        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                try {
                    createDto.setProductId(selectedProduct.getId());
                    createDto.setEshopId(selectedEshop.getId());
                    Long productInEshopId = PriceComparatorApplication.getApi().createProductInEshop(createDto);

                    createDto = new ProductInEshopCreateDto();
                    setResponsePage(ProductListPerEshopPage.class);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        add(form);

        DropDownChoice<ProductIdNameDto> product = new DropDownChoice<>(
                "product",
                new PropertyModel<ProductIdNameDto>(this, "selectedProduct"),
                new LoadableDetachableModel<List<ProductIdNameDto>>() {
                    @Override
                    protected List<ProductIdNameDto> load() {
                        return PriceComparatorApplication.getApi().findAllProductForSelection();
                    }
                },
                new ChoiceRenderer<ProductIdNameDto>(ProductIdNameDto.AT_NAME)
        );
        form.add(product);

        DropDownChoice<EshopIdNameDto> eshop = new DropDownChoice<>(
                "eshop",
                new PropertyModel<EshopIdNameDto>(this, "selectedEshop"),
                new LoadableDetachableModel<List<EshopIdNameDto>>() {
                    @Override
                    protected List<EshopIdNameDto> load() {
                        return PriceComparatorApplication.getApi().findAllEshopsForSelection();
                    }
                },
                new ChoiceRenderer<EshopIdNameDto>(EshopIdNameDto.AT_NAME)
        );
        form.add(eshop);

        TextField<String> productEshopPageUrl = new TextField<>("productEshopPageUrl",
                new PropertyModel<String>(createDto, ProductInEshopCreateDto.AT_ESHOP_PRODUCT_PAGE));
        productEshopPageUrl.setRequired(true);
        form.add(productEshopPageUrl);
    }

    @Override
    protected IModel<String> getTitleModel() {
        return new ResourceModel("ProductInEshopCreatePage.title");
    }

    public ProductIdNameDto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductIdNameDto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public EshopIdNameDto getSelectedEshop() {
        return selectedEshop;
    }

    public void setSelectedEshop(EshopIdNameDto selectedEshop) {
        this.selectedEshop = selectedEshop;
    }
}
