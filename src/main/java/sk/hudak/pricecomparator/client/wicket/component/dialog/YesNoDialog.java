package sk.hudak.pricecomparator.client.wicket.component.dialog;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

/**
 * Created by jan on 8. 5. 2016.
 */
public class YesNoDialog extends Panel {

    private String titleKey;
    private String questionKey;

    public YesNoDialog(String id, String titleKey, String questionKey) {
        super(id);
        setOutputMarkupId(true);

        this.titleKey = titleKey;
        this.questionKey = questionKey;

        add(new Label("title", new ResourceModel(titleKey)));
        add(new Label("question", new ResourceModel(questionKey)));



    }
}
