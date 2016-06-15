package sk.hudak.pricecomparator.client.wicket.component.table.column;


import org.apache.wicket.model.IModel;

import java.math.BigDecimal;

/**
 * Created by jan on 11. 6. 2016.
 */
public class PriceForPackageColumn extends PriceColumn {

    public PriceForPackageColumn(String id, IModel<BigDecimal> model) {
        super(id, model, 2);
    }
}
