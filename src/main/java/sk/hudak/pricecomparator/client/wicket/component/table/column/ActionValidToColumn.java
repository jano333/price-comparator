package sk.hudak.pricecomparator.client.wicket.component.table.column;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.DateConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jan on 17. 6. 2016.
 */
public class ActionValidToColumn extends Label {

    public ActionValidToColumn(String id, IModel<Date> model) {
        super(id, model);
    }

    @Override
    public <C> IConverter<C> getConverter(Class<C> type) {
        return (IConverter<C>) new DateConverter() {
            @Override
            public DateFormat getDateFormat(Locale locale) {
                return new SimpleDateFormat("dd.MM.yyyy");
            }
        };
    }
}
