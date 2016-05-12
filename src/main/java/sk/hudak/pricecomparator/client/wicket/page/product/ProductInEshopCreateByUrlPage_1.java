package sk.hudak.pricecomparator.client.wicket.page.product;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.to.internal.StepOneRequestDto;
import sk.hudak.pricecomparator.middle.to.internal.StepOneResponseDto;

import java.math.BigDecimal;

/**
 * Created by jan on 25. 4. 2016.
 */
public class ProductInEshopCreateByUrlPage_1 extends LayoutPage {

    private Form<Void> stepOneForm;
    private Form<Void> stepTwoForm;

    private StepOneRequestDto stepOneRequestDto = new StepOneRequestDto();
    private StepOneResponseDto stepOneResponseDto = new StepOneResponseDto();

    public ProductInEshopCreateByUrlPage_1() {

        add(new FeedbackPanel("feedback"));

        // step 1
        stepOneForm = new Form<>("stepOneForm");
        stepOneForm.setOutputMarkupId(true);
        add(stepOneForm);

        TextField<String> productUrl = new TextField<>("productUrl",
                new PropertyModel<String>(stepOneRequestDto, StepOneRequestDto.AT_PRODUCT_URL));
        productUrl.setRequired(true);
        stepOneForm.add(productUrl);

        // step 2
        stepTwoForm = new Form<>("stepTwoForm");
        stepTwoForm.setOutputMarkupPlaceholderTag(true);
        stepTwoForm.setVisible(false);
        add(stepTwoForm);

        TextField<String> productName = new TextField<>("productName",
                new PropertyModel<String>(stepOneResponseDto, StepOneResponseDto.AT_PRODUCT_NAME));
        productName.setRequired(true);
        stepTwoForm.add(productName);

        RadioGroup<Unit> group = new RadioGroup<>("group",
                new PropertyModel<Unit>(stepOneResponseDto, StepOneResponseDto.AT_UNIT));
        stepTwoForm.add(group);

        Radio<Unit> kus = new Radio<>("kus", Model.of(Unit.KUS));
        Radio<Unit> vaha = new Radio<>("vaha", Model.of(Unit.KILOGRAM));
        Radio<Unit> objem = new Radio<>("objem", Model.of(Unit.LITER));
        Radio<Unit> dlzka = new Radio<>("dlzka", Model.of(Unit.METER));
        Radio<Unit> davka = new Radio<>("davka", Model.of(Unit.DAVKA));
        group.add(kus, vaha, objem, dlzka, davka);

        TextField<BigDecimal> countOfUnit = new TextField<>("countOfUnit",
                new PropertyModel<BigDecimal>(stepOneResponseDto, StepOneResponseDto.AT_COUNT_OF_UNIT));
        countOfUnit.setRequired(true);
        stepTwoForm.add(countOfUnit);

        TextField<Integer> countOfItemInOnePackage = new TextField<>("countOfItemInOnePackage",
                new PropertyModel<Integer>(stepOneResponseDto, StepOneResponseDto.AT_COUNT_OF_ITEM_IN_ONE_PACKAGE));
        countOfItemInOnePackage.setRequired(true);
        stepTwoForm.add(countOfItemInOnePackage);

        AjaxButton stepOneSubmitBt = new AjaxButton("stepOneSubmitBt", stepOneForm) {

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                System.out.println("su tu chyby");

            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                try {
                    stepOneResponseDto = PriceComparatorApplication.getApi().analyzeProductUrl(stepOneRequestDto);
                    System.out.println(stepOneResponseDto);

                    stepOneForm.setVisible(false);
                    stepTwoForm.setVisible(true);

                    target.add(stepOneForm, stepTwoForm);

//                    setResponsePage(ProductListPage.class);

                } catch (Exception e) {
                    //TODO
                    e.printStackTrace();
                }

            }
        };
        stepOneSubmitBt.setOutputMarkupId(true);
        add(stepOneSubmitBt);


    }
}
