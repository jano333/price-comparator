package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.LabeledWebMarkupContainer;

public class Placeholder extends Behavior {

    private static final long serialVersionUID = 1L;

    @Override
    public void bind(Component component) {
        super.bind(component);
        component.setOutputMarkupId(true);
        if (component instanceof LabeledWebMarkupContainer) {
            component.add(new AttributeModifier("placeholder", ((LabeledWebMarkupContainer) component).getLabel()));
        }

    }
}
