package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

public class Icon extends WebComponent {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Icon(String id, IModel<String> model) {
        super(id);
        add(new ClassAppender(model));
    }


}
