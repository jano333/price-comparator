package sk.hudak.pricecomparator.client.swing.panel.page;

import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;

import javax.swing.*;

/**
 * Page pre zoznam existujucich eshopov.
 * <p/>
 * Created by jan on 24. 10. 2015.
 */
public class EshopListPage extends JPanel {

    private JLabel lbEshops;
    private EshopSelectionListViewPanel lvEshops;

    public EshopListPage() {
        setLayout(null);

        // 1 riadok
        int rowNumber = 1;

        lbEshops = GuiUtils.label("Zoznam eshopov: ", rowNumber);
        add(lbEshops);

        lvEshops = new EshopSelectionListViewPanel();
        lvEshops.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                250);

        add(lvEshops);

    }

    public void init() {
        lvEshops.reloadData();
        lvEshops.setFirstSelected();
        lvEshops.requestFocus();
    }
}
