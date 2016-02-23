package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.middle.to.EshopListDto;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jan on 23. 2. 2016.
 */
public class EshopComboBoxRenderer extends JLabel implements ListCellRenderer<EshopListDto> {

    public EshopComboBoxRenderer() {
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends EshopListDto> list, EshopListDto value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            setText(value.getName());
        } else {
            setText("Null");
        }

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
