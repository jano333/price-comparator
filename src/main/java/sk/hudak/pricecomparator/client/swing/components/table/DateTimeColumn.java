package sk.hudak.pricecomparator.client.swing.components.table;

/**
 * Created by jan on 7. 2. 2016.
 */
public class DateTimeColumn extends DateColumn {

    public DateTimeColumn(String propertyName, String headerName, int width) {
        super(propertyName, DATE_TIME_FORMAT, headerName, width);
    }
}
