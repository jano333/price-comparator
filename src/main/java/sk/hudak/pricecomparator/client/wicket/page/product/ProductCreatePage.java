package sk.hudak.pricecomparator.client.wicket.page.product;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.wicket.page.CreatePageMarker;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.to.ProductCreateDto;

import java.math.BigDecimal;

/**
 * Created by jan on 24. 3. 2016.
 */
public class ProductCreatePage extends LayoutPage implements CreatePageMarker {

    private ProductCreateDto createDto = new ProductCreateDto();

    public ProductCreatePage() {

        add(new FeedbackPanel("feedback"));

        // filter
        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                try {
                    Long productId = ServiceLocator.getService().createProduct(createDto);

                    createDto = new ProductCreateDto();
                    setResponsePage(ProductListPage.class);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        add(form);

        TextField<String> productName = new TextField<>("productName",
                new PropertyModel<String>(createDto, ProductCreateDto.AT_NAME));
        productName.setRequired(true);
        form.add(productName);

        RadioGroup<Unit> group = new RadioGroup<>("group",
                new PropertyModel<Unit>(createDto, ProductCreateDto.AT_UNIT));
        form.add(group);

        Radio<Unit> kus = new Radio<>("kus", Model.of(Unit.KUS));
        Radio<Unit> vaha = new Radio<>("vaha", Model.of(Unit.KILOGRAM));
        Radio<Unit> objem = new Radio<>("objem", Model.of(Unit.LITER));
        Radio<Unit> dlzka = new Radio<>("dlzka", Model.of(Unit.METER));
        Radio<Unit> davka = new Radio<>("davka", Model.of(Unit.DAVKA));
        group.add(kus, vaha, objem, dlzka, davka);

        TextField<BigDecimal> countOfUnit = new TextField<>("countOfUnit",
                new PropertyModel<BigDecimal>(createDto, ProductCreateDto.AT_COUNT_OF_UNIT));
        countOfUnit.setRequired(true);
        form.add(countOfUnit);

        TextField<Integer> countOfItemInOnePackage = new TextField<>("countOfItemInOnePackage",
                new PropertyModel<Integer>(createDto, ProductCreateDto.AT_COUNT_OF_ITEM_IN_ONE_PACKAGE));
        countOfItemInOnePackage.setRequired(true);
        form.add(countOfItemInOnePackage);

    }

}
