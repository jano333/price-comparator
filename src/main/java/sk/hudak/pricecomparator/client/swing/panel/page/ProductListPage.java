package sk.hudak.pricecomparator.client.swing.panel.page;

import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;

import javax.swing.*;

/**
 * Created by jan on 24. 10. 2015.
 */
public class ProductListPage extends JPanel {

    private final JLabel lbProducts;
    private final ProductSelectionListView lvProduct;

    public ProductListPage() {
        setLayout(null);

        // 1 riadok
        int rowNumber = 1;

        lbProducts = GuiUtils.label("Zoznam produktov: ", rowNumber);
        add(lbProducts);

        lvProduct = new ProductSelectionListView();
        lvProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                250);
        add(lvProduct);

    }

    public void reloadData() {
        lvProduct.reloadData();
    }
}
