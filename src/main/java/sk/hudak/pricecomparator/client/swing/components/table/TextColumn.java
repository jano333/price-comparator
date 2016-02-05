package sk.hudak.pricecomparator.client.swing.components.table;

import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by jan on 24. 1. 2016.
 */
public class TextColumn extends BasicColumn {

    private String propertyName;

    public TextColumn(String propertyName, String headerName, int width) {
        super(headerName, width);
        this.propertyName = propertyName;
    }

    @Override
    public TableCellRenderer createTableCellRenderer() {
        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setFont(table.getFont());
                return label;
            }
        };
    }

    @Override
    public <T> Object getValueFor(T rowDto) {
        try {
            return PropertyUtils.getProperty(rowDto, propertyName);

        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            return null;
        }
    }


}
