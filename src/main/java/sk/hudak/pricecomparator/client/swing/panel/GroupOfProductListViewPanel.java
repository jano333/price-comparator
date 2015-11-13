package sk.hudak.pricecomparator.client.swing.panel;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.BasicSelectionListViewPanel;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductListDto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by jan on 5. 11. 2015.
 */
public class GroupOfProductListViewPanel extends BasicSelectionListViewPanel<GroupOfProductListDto> {
    @Override
    public List<GroupOfProductListDto> readData() {
        return ServiceLocator.getService().findAllGroupsOfProducts();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends GroupOfProductListDto> list, GroupOfProductListDto value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setText(value.getName());
        if (isSelected) {
            label.setBackground(Color.CYAN);
        } else {
            label.setBackground(Color.WHITE);

        }
        return label;
    }
}
