package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

public class ClassAppender extends AttributeAppender {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ClassAppender(String value) {
        this(new Model<String>(value));
    }

    public ClassAppender(IModel<?> value) {
        super("class", value, " ");
    }

    public ClassAppender(final IModel<Boolean> model, final String trueValue, final String falseValue) {
        this(new LoadableDetachableModel<String>() {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected String load() {
                Boolean o = model.getObject();
                if (o == null) {
                    return falseValue;
                }
                return o ? trueValue : falseValue;
            }

            @Override
            public void detach() {
                super.detach();
                model.detach();
            }
        });

    }

}
