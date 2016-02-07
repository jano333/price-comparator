package sk.hudak.pricecomparator.client.swing.components.table;

import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.model.ProductAction;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by jan on 7. 2. 2016.
 */
public class ProductActionColumn extends TextColumn {

    private static final Color MY_GREEN = new Color(0, 128, 0);


    public ProductActionColumn(String propertyName, String headerName, int width) {
        super(propertyName, headerName, width);
    }

    @Override
    public TableCellRenderer createTableCellRenderer() {
        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (!(value instanceof ProductAction)) {
                    throw new PriceComparatorException("Value is not type of " + ProductAction.class.getSimpleName());
                }
                ProductAction productAction = (ProductAction) value;
                String result;
                Color color;
                switch (productAction) {
                    case IN_ACTION:
                        result = "Áno";
                        color = MY_GREEN;
                        break;
                    case NON_ACTION:
                        result = "Nie";
                        color = Color.RED;
                        break;
                    case UNKNOWN:
                        result = "Nezistené";
                        color = Color.DARK_GRAY;
                        break;
                    default:
                        throw new PriceComparatorException("Not supported type");
                }

                JLabel label = new JLabel(result);
                label.setFont(table.getFont());
                label.setForeground(color);
                return label;
            }
        };
    }
}
