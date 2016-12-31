package sk.hudak.pricecomparator.client.wicket.page.product;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.product.ProductDto;
import sk.hudak.pricecomparator.middle.to.product.ProductUpdateDto;

import java.math.BigDecimal;

/**
 * Created by jan on 30. 10. 2016.
 */
public class ProductUpdatePage extends LayoutPage {

    public static final String PARAM_PRODUCT_ID = "productId";

    private ProductUpdateDto updateDto;

    public ProductUpdatePage(PageParameters params) {
        final Long productId = params.get(PARAM_PRODUCT_ID).toLongObject();

        ProductDto product = null;
        try {
            product = PriceComparatorApplication.getApi().getProduct(productId);
        } catch (PriceComparatorBusinesException e) {
            //TODO
            e.printStackTrace();
        }

        updateDto = new ProductUpdateDto();
        updateDto.setId(productId);
        updateDto.setName(product.getName());
        updateDto.setUnit(product.getUnit());
        updateDto.setCountOfUnit(product.getCountOfUnit());
        updateDto.setCountOfItemInOnePackage(product.getCountOfItemInOnePackage());

        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                try {
                    PriceComparatorApplication.getApi().updateProduct(updateDto);
                    setResponsePage(ProductListPage.class);

                } catch (Exception e) {
                    //TODO
                    e.printStackTrace();
                }

            }
        };
        add(form);

        TextField<String> productName = new TextField<>("productName",
                new PropertyModel<String>(updateDto, ProductUpdateDto.AT_NAME));
        productName.setRequired(true);
        form.add(productName);

        RadioGroup<Unit> group = new RadioGroup<>("group",
                new PropertyModel<Unit>(updateDto, ProductUpdateDto.AT_UNIT));
        form.add(group);

        Radio<Unit> kus = new Radio<>("kus", Model.of(Unit.KUS));
        Radio<Unit> vaha = new Radio<>("vaha", Model.of(Unit.KILOGRAM));
        Radio<Unit> objem = new Radio<>("objem", Model.of(Unit.LITER));
        Radio<Unit> dlzka = new Radio<>("dlzka", Model.of(Unit.METER));
        Radio<Unit> davka = new Radio<>("davka", Model.of(Unit.DAVKA));
        group.add(kus, vaha, objem, dlzka, davka);

        TextField<BigDecimal> countOfUnit = new TextField<>("countOfUnit",
                new PropertyModel<BigDecimal>(updateDto, ProductUpdateDto.AT_COUNT_OF_UNIT));
        countOfUnit.setRequired(true);
        form.add(countOfUnit);

        TextField<Integer> countOfItemInOnePackage = new TextField<>("countOfItemInOnePackage",
                new PropertyModel<Integer>(updateDto, ProductUpdateDto.AT_COUNT_OF_ITEM_IN_ONE_PACKAGE));
        countOfItemInOnePackage.setRequired(true);
        form.add(countOfItemInOnePackage);


    }
}
