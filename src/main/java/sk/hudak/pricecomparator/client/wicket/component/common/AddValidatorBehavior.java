package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.validation.IValidator;


public class AddValidatorBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    private transient IValidator<?> validator;

    public AddValidatorBehavior(IValidator<?> validiator) {
        this.validator = validiator;
    }

    @Override
    public void bind(final Component component) {
        super.bind(component);
        if (component instanceof FormComponent<?>) {
            ((FormComponent) component).add(validator);
        }
    }

}