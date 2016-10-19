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
 * Created by jan on 19. 10. 2016.
 */
public class LastUpdatedPriceColumn extends Label {

    public LastUpdatedPriceColumn(String id, IModel<Date> model) {
        super(id, model);
//        if(model.getObject().)
//        add(new AttributeAppender("class", ""))
    }

    @Override
    public <C> IConverter<C> getConverter(Class<C> type) {
        return (IConverter<C>) new DateConverter() {
            @Override
            public DateFormat getDateFormat(Locale locale) {
                return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            }
        };
    }
}
