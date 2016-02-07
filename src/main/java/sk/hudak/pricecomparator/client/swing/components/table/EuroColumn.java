package sk.hudak.pricecomparator.client.swing.components.table;

import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by jan on 7. 2. 2016.
 */
public class EuroColumn extends TextColumn {

    private int countOfDecimal;

    public EuroColumn(String propertyName, int countOfDecimal, String headerName, int width) {
        super(propertyName, headerName, width);
        this.countOfDecimal = countOfDecimal;
    }


    @Override
    public TableCellRenderer createTableCellRenderer() {
        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                String result;
                if (value instanceof BigDecimal) {
                    result = convertTo((BigDecimal) value);

                } else if (value instanceof Double) {
                    result = convertTo((Double) value);

                } else {
                    result = value.toString();
                }
                JLabel label = new JLabel(result + " €");
                label.setFont(table.getFont());
                return label;
            }
        };
    }


    private String convertTo(BigDecimal value) {
        return String.valueOf(formatDecimal(value, countOfDecimal));
    }

    private String convertTo(Double value) {
        //TODO
        throw new PriceComparatorException("Neimplementovane");
    }


    private static BigDecimal formatDecimal(BigDecimal value, int countOfDecimal) {
        if (value == null) {
            return null;
        }
        return value.setScale(countOfDecimal, RoundingMode.HALF_UP);
    }


}
