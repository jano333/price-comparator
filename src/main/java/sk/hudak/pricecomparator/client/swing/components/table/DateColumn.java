package sk.hudak.pricecomparator.client.swing.components.table;

import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jan on 7. 2. 2016.
 */
public class DateColumn extends TextColumn {

    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    private SimpleDateFormat sdf;

    public DateColumn(String propertyName, String headerName, int width) {
        this(propertyName, DATE_FORMAT, headerName, width);
    }

    public DateColumn(String propertyName, String dateFormat, String headerName, int width) {
        super(propertyName, headerName, width);
        sdf = new SimpleDateFormat(dateFormat);
    }

    @Override
    public TableCellRenderer createTableCellRenderer() {
        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final String strValue;
                if (value == null) {
                    strValue = "-";
                } else if (!(value instanceof Date)) {
                    throw new PriceComparatorException("Value is not type of Date");
                } else {
                    strValue = sdf.format((Date) value);
                }
                JLabel label = new JLabel(strValue);
                label.setHorizontalAlignment(alignment.getAligment());
                label.setFont(table.getFont());
                return label;
            }
        };
    }

}
