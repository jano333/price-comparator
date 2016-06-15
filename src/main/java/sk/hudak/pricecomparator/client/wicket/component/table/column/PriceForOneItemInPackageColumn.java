package sk.hudak.pricecomparator.client.wicket.component.table.column;

import org.apache.wicket.model.IModel;

import java.math.BigDecimal;

/**
 * Created by jan on 15. 6. 2016.
 */
public class PriceForOneItemInPackageColumn extends PriceColumn {

    public PriceForOneItemInPackageColumn(String id, IModel<BigDecimal> model) {
        super(id, model, 2);
    }
}
