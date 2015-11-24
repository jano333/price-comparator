package sk.hudak.pricecomparator.client.swing.panel.page;

import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;

import javax.swing.*;

/**
 * Created by jan on 24. 10. 2015.
 * Nepouziva sa
 */
@Deprecated
public class ProductInEshopListPage extends JPanel {

    private final JLabel lbEshop;
    private final EshopSelectionListViewPanel lvEshop;

    private final JLabel lbProduct;
    private final ProductSelectionListView lvProduct;

    public ProductInEshopListPage() {
        setLayout(null);

        // 1 riadok
        int rowNumber = 1;
        lbEshop = GuiUtils.label("Eshop: ", rowNumber);
        add(lbEshop);

        lvEshop = new EshopSelectionListViewPanel();
        lvEshop.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                100);
        add(lvEshop);

        // 2 riadok
        rowNumber = rowNumber + 4;
        lbProduct = GuiUtils.label("Produkt: ", rowNumber);
        add(lbProduct);

        lvProduct = new ProductSelectionListView();
        lvProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                200);
        add(lvProduct);

    }
}
