package sk.hudak.pricecomparator.client.wicket.component.table.column;

import org.apache.wicket.model.IModel;

import java.math.BigDecimal;

/**
 * Created by jan on 15. 6. 2016.
 */
@Deprecated
public class PriceForUnitColumn extends PriceColumn {

    @Deprecated
    public PriceForUnitColumn(String id, IModel<BigDecimal> priceForUnit) {
        super(id, priceForUnit, 3);
    }
}
