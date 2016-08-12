package sk.hudak.pricecomparator.client.wicket.component.table.column;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import sk.hudak.pricecomparator.middle.canonical.ProductAction;

import java.util.Locale;

/**
 * Created by jan on 16. 6. 2016.
 */
public class ProductActionColumn extends Label {

    public ProductActionColumn(String id, IModel<ProductAction> model) {
        super(id, model);
    }


    @Override
    public <C> IConverter<C> getConverter(Class<C> type) {
        return (IConverter<C>) new IConverter<ProductAction>() {
            @Override
            public ProductAction convertToObject(String value, Locale locale) throws ConversionException {
                return ProductAction.valueOf(value);
            }

            @Override
            public String convertToString(ProductAction value, Locale locale) {
                return value.getLocalizable();
            }
        };
    }
}
