package sk.hudak.pricecomparator.client.swing.components.table;

import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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


                //TODO osetring NPE
                JLabel label = new JLabel(value.toString());
                label.setHorizontalAlignment(alignment.getAligment());
                //FIXME nefunguje
//                label.setCursor(new Cursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        System.out.println("mouseEntered");
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        System.out.println("mouseExited");
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("mouseClicked");
                    }
                });

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
