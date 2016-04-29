package sk.hudak.pricecomparator.client.wicket.page.product;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.to.internal.StepOneRequestDto;
import sk.hudak.pricecomparator.middle.to.internal.StepOneResponseDto;

/**
 * Created by jan on 25. 4. 2016.
 */
public class ProductInEshopCreateByUrlPage_1 extends LayoutPage {

    private StepOneRequestDto stepOneRequestDto = new StepOneRequestDto();

    public ProductInEshopCreateByUrlPage_1() {
        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                try {
                    StepOneResponseDto stepOneResponseDto = PriceComparatorApplication.getApi().analyzeProductUrl(stepOneRequestDto);
                    System.out.println(stepOneResponseDto);

                    //TODO
                    setResponsePage(ProductListPage.class);

                } catch (Exception e) {
                    //TODO
                    e.printStackTrace();
                }

            }
        };
        add(form);

        TextField<String> productUrl = new TextField<>("productUrl",
                new PropertyModel<String>(stepOneRequestDto, StepOneRequestDto.AT_PRODUCT_URL));
        productUrl.setRequired(true);
        form.add(productUrl);

    }
}
