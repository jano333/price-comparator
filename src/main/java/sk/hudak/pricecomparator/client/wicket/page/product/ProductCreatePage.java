package sk.hudak.pricecomparator.client.wicket.page.product;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.to.ProductCreateDto;

import java.math.BigDecimal;

/**
 * Created by jan on 24. 3. 2016.
 */
public class ProductCreatePage extends LayoutPage {

    private ProductCreateDto createDto = new ProductCreateDto();

    public ProductCreatePage() {

        // filter
        Form<Void> createForm = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                System.out.println("submit");
                createDto = new ProductCreateDto();
            }
        };
        add(createForm);

        TextField<String> productName = new TextField<>("productName", new PropertyModel<String>(createDto, ProductCreateDto.AT_NAME));
        createForm.add(productName);

        RadioGroup<Unit> group = new RadioGroup<>("group", new PropertyModel<Unit>(createDto, ProductCreateDto.AT_UNIT));
        createForm.add(group);

        Radio<Unit> kus = new Radio<>("kus", Model.of(Unit.KUS));
        Radio<Unit> vaha = new Radio<>("vaha", Model.of(Unit.KILOGRAM));
        Radio<Unit> objem = new Radio<>("objem", Model.of(Unit.LITER));
        Radio<Unit> dlzka = new Radio<>("dlzka", Model.of(Unit.METER));
        Radio<Unit> davka = new Radio<>("davka", Model.of(Unit.DAVKA));
        group.add(kus, vaha, objem, dlzka, davka);

        TextField<BigDecimal> countOfUnit = new TextField<>("countOfUnit", new PropertyModel<BigDecimal>(createDto, ProductCreateDto.AT_COUNT_OF_UNIT));
        createForm.add(countOfUnit);

        TextField<Integer> countOfItemInOnePackage = new TextField<>("countOfItemInOnePackage", new PropertyModel<Integer>(createDto, ProductCreateDto.AT_COUNT_OF_ITEM_IN_ONE_PACKAGE));
        createForm.add(countOfItemInOnePackage);

    }

    @Override
    protected IModel<String> getTitleModel() {
        return new ResourceModel("ProductCreatePage.title");
    }
}
