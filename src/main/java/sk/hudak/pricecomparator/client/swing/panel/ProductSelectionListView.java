package sk.hudak.pricecomparator.client.swing.panel;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.BasicSelectionListViewPanel;
import sk.hudak.pricecomparator.middle.to.ProductListDto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by jan on 17. 10. 2015.
 */
public class ProductSelectionListView extends BasicSelectionListViewPanel<ProductListDto> {
    @Override
    public List<ProductListDto> readData() {
        return ServiceLocator.getService().getAllProduct();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ProductListDto> list, ProductListDto value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = new JLabel();
        if (showImage() && value.getImagePath() != null) {
            label.setIcon(new ImageIcon(value.getImagePath()));
        }
        label.setOpaque(true);
        label.setText(value.getName());
        if (isSelected) {
            label.setBackground(Color.CYAN);
        } else {
            label.setBackground(Color.WHITE);
        }
        return label;
    }

    protected boolean showImage() {
        return true;
    }
}
