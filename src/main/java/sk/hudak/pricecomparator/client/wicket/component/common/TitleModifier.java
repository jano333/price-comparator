package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.model.IModel;

public class TitleModifier extends AttributeModifier {

    private static final long serialVersionUID = 1L;

    public TitleModifier(IModel<?> replaceModel) {
        super("title", replaceModel);
    }

}
