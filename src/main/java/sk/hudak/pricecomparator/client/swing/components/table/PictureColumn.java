package sk.hudak.pricecomparator.client.swing.components.table;

import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by jan on 8. 2. 2016.
 */
public class PictureColumn extends TextColumn {

    public PictureColumn(String propertyName, String headerName, int width) {
        super(propertyName, headerName, width);
    }

    @Override
    public TableCellRenderer createTableCellRenderer() {
        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value == null) {
                    return new JLabel("nemame :-)");
                }
                if (!(value instanceof String)) {
                    throw new PriceComparatorException("Picture path must be string");
                }
                String picturePath = (String) value;

                JLabel label = new JLabel(new ImageIcon(picturePath));
                return label;
            }
        };
    }


}
