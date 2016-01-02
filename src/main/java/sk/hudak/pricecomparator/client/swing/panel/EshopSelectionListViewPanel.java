package sk.hudak.pricecomparator.client.swing.panel;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.BasicSelectionListViewPanel;
import sk.hudak.pricecomparator.middle.api.to.EshopListDto;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.util.List;


/**
 * Created by jan on 17. 10. 2015.
 */
public class EshopSelectionListViewPanel extends BasicSelectionListViewPanel<EshopListDto> {

    @Override
    public List<EshopListDto> readData() {
        return ServiceLocator.getService().getAllEshops();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends EshopListDto> list, EshopListDto value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setText(value.getName() + " - " + value.getHomePage());
        if (isSelected) {
            label.setBackground(Color.CYAN);
        } else {
            label.setBackground(Color.WHITE);
        }
        if (getForegroundColor() != null) {
            label.setForeground(getForegroundColor());
        }
        return label;
    }

    @Override
    protected void onMouseDoubleClick(EshopListDto entity) {
        openURLInExternalBrowser(entity.getHomePage());
    }

    protected void openURLInExternalBrowser(String uri) {
        if (!Desktop.isDesktopSupported()) {
            return;
        }
        try {
            Desktop.getDesktop().browse(new URI(uri));
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }


}
