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
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.WU;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.client.wicket.page.productineshop.ProductListPerEshopPage;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.to.internal.ProductByUrlAnalyzatorRequestDto;
import sk.hudak.pricecomparator.middle.to.internal.ProductByUrlAnalyzatorResponseDto;
import sk.hudak.pricecomparator.middle.to.internal.StepTwoRequestDto;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * Created by jan on 25. 4. 2016.
 */
public class ProductInEshopCreateByUrlPage_1 extends LayoutPage {

    private AjaxButton stepOneSubmitBt;
    private Form<Void> stepOneForm;
    private ProductByUrlAnalyzatorRequestDto stepOneRequestDto = new ProductByUrlAnalyzatorRequestDto();

    private AjaxButton stepTwoSubmitBt;
    private Form<Void> stepTwoForm;
    private StepTwoRequestDto stepTwoRequestDto = new StepTwoRequestDto();

    public ProductInEshopCreateByUrlPage_1() {

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupPlaceholderTag(true);
        add(feedback);

        // step 1
        stepOneForm = new Form<>("stepOneForm");
        stepOneForm.setOutputMarkupId(true);
        add(stepOneForm);

        TextField<String> productUrl = new TextField<>("productUrl",
                new PropertyModel<String>(stepOneRequestDto, ProductByUrlAnalyzatorRequestDto.AT_PRODUCT_URL));
        productUrl.setRequired(true);
        stepOneForm.add(productUrl);

        // step 2
        stepTwoForm = new Form<>("stepTwoForm");
        stepTwoForm.setOutputMarkupPlaceholderTag(true);
        stepTwoForm.setVisible(false);
        add(stepTwoForm);

        TextField<String> productUrlStep2 = new TextField<>("productUrlStep2",
                new PropertyModel<String>(stepTwoRequestDto, StepTwoRequestDto.AT_PRODUCT_URL));
        productUrlStep2.setRequired(true);
        stepTwoForm.add(productUrlStep2);

        TextField<EshopType> eshopType = new TextField<EshopType>("eshopType",
                new PropertyModel<EshopType>(stepTwoRequestDto, StepTwoRequestDto.AT_ESHOP_TYPE)) {
            @Override
            public <C> IConverter<C> getConverter(Class<C> type) {
                return (IConverter<C>) new IConverter<EshopType>() {
                    @Override
                    public EshopType convertToObject(String value, Locale locale) throws ConversionException {
                        return EshopType.valueOf(value);
                    }

                    @Override
                    public String convertToString(EshopType value, Locale locale) {
                        return value.toString();
                    }
                };
            }
        };
        eshopType.setRequired(true);
        stepTwoForm.add(eshopType);

        TextField<String> productName = new TextField<>("productName",
                new PropertyModel<String>(stepTwoRequestDto, StepTwoRequestDto.AT_PRODUCT_NAME));
        productName.setRequired(true);
        stepTwoForm.add(productName);

        RadioGroup<Unit> group = new RadioGroup<>("group",
                new PropertyModel<Unit>(stepTwoRequestDto, StepTwoRequestDto.AT_UNIT));
        stepTwoForm.add(group);

        Radio<Unit> kus = new Radio<>("kus", Model.of(Unit.KUS));
        Radio<Unit> vaha = new Radio<>("vaha", Model.of(Unit.KILOGRAM));
        Radio<Unit> objem = new Radio<>("objem", Model.of(Unit.LITER));
        Radio<Unit> dlzka = new Radio<>("dlzka", Model.of(Unit.METER));
        Radio<Unit> davka = new Radio<>("davka", Model.of(Unit.DAVKA));
        group.add(kus, vaha, objem, dlzka, davka);

        TextField<BigDecimal> countOfUnit = new TextField<>("countOfUnit",
                new PropertyModel<BigDecimal>(stepTwoRequestDto, StepTwoRequestDto.AT_COUNT_OF_UNIT));
        countOfUnit.setRequired(true);
        stepTwoForm.add(countOfUnit);

        TextField<Integer> countOfItemInOnePackage = new TextField<>("countOfItemInOnePackage",
                new PropertyModel<Integer>(stepTwoRequestDto, StepTwoRequestDto.AT_COUNT_OF_ITEM_IN_ONE_PACKAGE));
        countOfItemInOnePackage.setRequired(true);
        stepTwoForm.add(countOfItemInOnePackage);

        stepOneSubmitBt = new AjaxButton("stepOneSubmitBt", stepOneForm) {

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(feedback);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                try {
                    ProductByUrlAnalyzatorResponseDto tmp = PriceComparatorApplication.getApi().analyzeProductUrl(stepOneRequestDto);
                    // preklopenie
                    stepTwoRequestDto.setProductName(tmp.getProductName());
                    stepTwoRequestDto.setEshopType(tmp.getEshopType());
                    stepTwoRequestDto.setProductUrl(tmp.getProductUrl());
                    stepTwoRequestDto.setCountOfUnit(tmp.getCountOfUnit());
                    stepTwoRequestDto.setCountOfItemInPackage(tmp.getCountOfItemInPackage());
                    stepTwoRequestDto.setUnit(tmp.getUnit());

                    stepOneForm.setVisible(false);
                    stepOneSubmitBt.setVisible(false);
                    stepTwoForm.setVisible(true);
                    stepTwoSubmitBt.setVisible(true);

                    target.add(stepOneSubmitBt, stepOneForm, stepTwoForm, stepTwoSubmitBt);
                    // musi tu byt aby sa skryl ak bola predtym chyba a bol zobrazeny
                    target.add(feedback);

                } catch (Exception e) {
                    //TODO
                    e.printStackTrace();
                }

            }
        };
        stepOneSubmitBt.setOutputMarkupId(true);
        add(stepOneSubmitBt);

        stepTwoSubmitBt = new AjaxButton("stepTwoSubmitBt", stepTwoForm) {

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                //FIXME toto nizsie tu musi byt lebo inak sa nedozviem ze je chyba napr validacna pred
                // sabmitom, fixme je tu preto lebo to treba pridat vsase kde pouzivam ajax na submit
                target.add(feedback);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                try {
                    ProductInEshopDto result = PriceComparatorApplication.getApi().createNewProductAndAddToEshop(stepTwoRequestDto);

                    setResponsePage(ProductListPerEshopPage.class,
                            WU.param(ProductListPerEshopPage.PARAM_ESHOP_ID, result.getEshopId()));
                    //TODO presmerovanie kam ???

                } catch (PriceComparatorBusinesException e) {
                    e.printStackTrace();
                }
            }
        };
        stepTwoSubmitBt.setOutputMarkupPlaceholderTag(true);
        stepTwoSubmitBt.setVisible(false);
        add(stepTwoSubmitBt);
    }
}
