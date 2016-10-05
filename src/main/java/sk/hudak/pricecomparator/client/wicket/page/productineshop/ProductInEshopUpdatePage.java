package sk.hudak.pricecomparator.client.wicket.page.productineshop;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.to.EshopIdNameDto;
import sk.hudak.pricecomparator.middle.to.ProductIdNameDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopUpdateDto;

import java.util.List;

/**
 * Produkt v eshope edit page.
 * <p>
 * Created by jan on 12. 8. 2016.
 */
public class ProductInEshopUpdatePage extends LayoutPage {

    public static final transient String PARAM_PRODUCT_IN_ESHOP_ID = "productInEshopId";

    private EshopIdNameDto selectedEshop;
    private ProductIdNameDto selectedProduct;
    private String eshopProductPage;

    public ProductInEshopUpdatePage(PageParameters params) {
        final Long productInEshopId = params.get(PARAM_PRODUCT_IN_ESHOP_ID).toLongObject();

        ProductInEshopDto productInEshop = PriceComparatorApplication.getApi().getProductInEshop(productInEshopId);
        selectedEshop = productInEshop.getEshopId();
        selectedProduct = productInEshop.getProductId();
        eshopProductPage = productInEshop.getEshopProductPage();

        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                try {
                    ProductInEshopUpdateDto updateDto = new ProductInEshopUpdateDto();
                    updateDto.setId(productInEshopId);
                    updateDto.setProductId(selectedProduct.getId());
                    updateDto.setEshopId(selectedEshop.getId());
                    updateDto.setEshopProductPage(eshopProductPage);

                    PriceComparatorApplication.getApi().updateProductInEshop(updateDto);

                    setResponsePage(ProductListPerEshopPage.class);

                } catch (Exception e) {
                    //TODO vynimka
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
        product.setRequired(true);
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
        eshop.setRequired(true);
        form.add(eshop);

        TextField<String> productEshopPageUrl = new TextField<>("productEshopPageUrl",
                new PropertyModel<String>(this, "eshopProductPage"));
        productEshopPageUrl.setRequired(true);
        form.add(productEshopPageUrl);
    }
}
