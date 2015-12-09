package sk.hudak.pricecomparator.client.swing.panel;

import sk.hudak.pricecomparator.client.swing.components.BasicSelectionListViewPanel;
import sk.hudak.pricecomparator.middle.api.to.TescoProductInfoDto;
import sk.hudak.pricecomparator.server.database.TescoServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by jan on 6. 12. 2015.
 */
public class TescoProductInfoListView extends BasicSelectionListViewPanel<TescoProductInfoDto> {
    @Override
    public List<TescoProductInfoDto> readData() {
        return new TescoServiceImpl().getAllUnprocessedProducts();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends TescoProductInfoDto> list, TescoProductInfoDto value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = new JLabel();
        if (value.getImagePath() != null) {
            label.setIcon(new ImageIcon(value.getImagePath()));
        }
        label.setOpaque(true);
        label.setText(value.getNazov());
        if (isSelected) {
            label.setBackground(Color.CYAN);
        } else {
            label.setBackground(Color.WHITE);
        }
        return label;
    }
}
