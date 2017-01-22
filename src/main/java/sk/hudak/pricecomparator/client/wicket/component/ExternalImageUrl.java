package sk.hudak.pricecomparator.client.wicket.component;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.Model;

/**
 * Created by jan on 16. 1. 2017.
 */
public class ExternalImageUrl extends WebComponent {

    public ExternalImageUrl(String id, String imageUrl) {
        super(id);
        add(new AttributeModifier("src", new Model(imageUrl)));
        setVisible(!(imageUrl == null || imageUrl.equals("")));
    }

    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag, "img");
    }

}