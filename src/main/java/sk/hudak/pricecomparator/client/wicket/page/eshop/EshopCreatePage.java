package sk.hudak.pricecomparator.client.wicket.page.eshop;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.CreatePageMarker;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.to.eshop.EshopCreateDto;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jan on 6. 5. 2016.
 */
public class EshopCreatePage extends LayoutPage implements CreatePageMarker {

    private EshopCreateDto createDto = new EshopCreateDto();

    private EshopType selectedType;

    public EshopCreatePage() {

        //TODO dat do bazovaj triedy pre vsetky create page !!!
        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {

                try {
                    createDto.setEshopType(selectedType);
                    PriceComparatorApplication.getApi().createEshop(createDto);

                    createDto = new EshopCreateDto();
                    setResponsePage(EshopListPage.class);

                } catch (Exception e) {
                    //TODO
                    e.printStackTrace();
                }
            }
        };
        add(form);

        TextField<String> name = new TextField<>("name",
                new PropertyModel<String>(createDto, EshopCreateDto.AT_NAME));
        name.setRequired(true);
        form.add(name);

        DropDownChoice<EshopType> type = new DropDownChoice<EshopType>(
                "type",
                new PropertyModel<EshopType>(this, "selectedType"),
                new LoadableDetachableModel<List<EshopType>>() {
                    @Override
                    protected List<EshopType> load() {
                        return Arrays.asList(EshopType.values());
                    }
                },
                new ChoiceRenderer<EshopType>()
        ){
            @Override
            protected String getNullKey() {
                return "core.dropdown.nullValue.eshop.type";
            }
        };
        type.setRequired(true);
        form.add(type);

        TextField<String> homePage = new TextField<>("homePage",
                new PropertyModel<String>(createDto, EshopCreateDto.AT_HOME_PAGE));
        homePage.setRequired(true);
        form.add(homePage);


    }
}

