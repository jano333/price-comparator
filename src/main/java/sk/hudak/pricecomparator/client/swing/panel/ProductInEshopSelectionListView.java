package sk.hudak.pricecomparator.client.swing.panel;

import sk.hudak.pricecomparator.client.swing.components.BasicSelectionListViewPanel;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopCustomListDto;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 23. 12. 2015.
 */
public class ProductInEshopSelectionListView extends BasicSelectionListViewPanel<ProductInEshopCustomListDto> {
    @Override
    public List<ProductInEshopCustomListDto> readData() {
        return Collections.emptyList();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ProductInEshopCustomListDto> list, ProductInEshopCustomListDto value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = new JLabel();
        if (showImage() && value.getProductListDto().getImagePath() != null) {
            label.setIcon(new ImageIcon(value.getProductListDto().getImagePath()));
        }
        label.setOpaque(true);
        label.setText(value.getProductListDto().getName());
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
