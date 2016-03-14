package sk.hudak.pricecomparator.client.wicket.component.common;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;

public class NullLabel extends Label {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private IModel<String> nullValue;

    public NullLabel(final String id, IModel<?> model, IModel<String> nullValue) {
        super(id, model);
        this.nullValue = nullValue;
    }

    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        String value = getDefaultModelObjectAsString();
        String nullValueString = nullValue.getObject();
        if (Strings.isEmpty(nullValueString)) {
            nullValueString = "&nbsp;";
        } else {
            nullValueString = Strings.escapeMarkup(nullValueString, false, false).toString();
        }

        if (Strings.isEmpty(value)) {
            value = "<span class=\"nullvalue\">" + nullValueString + "</span>";
        }

        replaceComponentTagBody(markupStream, openTag, value);
    }


}
