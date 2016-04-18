package sk.hudak.pricecomparator.client.wicket.page.group;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import sk.hudak.pricecomparator.client.wicket.PriceComparatorApplication;
import sk.hudak.pricecomparator.client.wicket.page.CreatePageMarker;
import sk.hudak.pricecomparator.client.wicket.page.common.LayoutPage;
import sk.hudak.pricecomparator.middle.to.GroupOfProductCreateDto;

/**
 * Created by jan on 17. 4. 2016.
 */
public class GroupCreatePage extends LayoutPage implements CreatePageMarker {

    private GroupOfProductCreateDto createDto = new GroupOfProductCreateDto();

    public GroupCreatePage() {
        add(new FeedbackPanel("feedback"));

        Form<Void> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                try {
                    PriceComparatorApplication.getApi().createGroupOfProduct(createDto);

                    createDto = new GroupOfProductCreateDto();
                    setResponsePage(GroupListPage.class);

                } catch (Exception e) {
                    //TODO
                    e.printStackTrace();
                }

            }
        };
        add(form);

        TextField<String> groupName = new TextField<>("groupName",
                new PropertyModel<String>(createDto, GroupOfProductCreateDto.AT_NAME));
        groupName.setRequired(true);
        form.add(groupName);
    }
}
