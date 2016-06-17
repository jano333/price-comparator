package sk.hudak.pricecomparator.client.wicket.component.table.column;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import sk.hudak.pricecomparator.middle.canonical.Unit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/**
 * Created by jan on 16. 6. 2016.
 */
public class PriceForUnitColumn extends PriceColumn {

    private IModel<Unit> unit;

    public PriceForUnitColumn(String id, IModel<BigDecimal> priceForUnit, IModel<Unit> unit) {
        super(id, priceForUnit, 3);
        this.unit = unit;
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        unit.detach();
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
                suma = suma.replace(".", ",");
                return suma + " €/" + unit.getObject().getJednotka();
            }
        };
    }
}
