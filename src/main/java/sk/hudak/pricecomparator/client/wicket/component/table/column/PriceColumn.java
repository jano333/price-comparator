package sk.hudak.pricecomparator.client.wicket.component.table.column;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/**
 * Created by jan on 14. 6. 2016.
 */
public abstract class PriceColumn extends Label {
    private int pocetDestatinychMiest;

    public PriceColumn(String id, IModel<BigDecimal> model, int pocetDestatinychMiest) {
        super(id, model);
        this.pocetDestatinychMiest = pocetDestatinychMiest;
    }

    @Override
    public <C> IConverter<C> getConverter(Class<C> type) {
        return (IConverter<C>) new IConverter<BigDecimal>() {
            @Override
            public BigDecimal convertToObject(String value, Locale locale) throws ConversionException {
                return new BigDecimal(value);
            }

            @Override
            public String convertToString(BigDecimal value, Locale locale) {
                String suma = String.valueOf(value.setScale(pocetDestatinychMiest, RoundingMode.HALF_UP));
                return suma + " €";
            }
        };
    }

}
