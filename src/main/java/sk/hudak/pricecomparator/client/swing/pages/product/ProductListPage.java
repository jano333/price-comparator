package sk.hudak.pricecomparator.client.swing.pages.product;

import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jan on 24. 10. 2015.
 */
@Deprecated
public class ProductListPage extends JPanel {

    private ProductSelectionListView lvProduct;

    public ProductListPage() {
        setLayout(null);

        int rowNumber = 1;
        add(GuiUtils.label("Zoznam produktov: ", rowNumber));
        lvProduct = new ProductSelectionListView();
        lvProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                250);
        add(lvProduct);

        rowNumber = rowNumber + 9;
        JButton btUpdate = GuiUtils.button("Uprava produktu");
        btUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductCreateUpdateDialog dialog = new ProductCreateUpdateDialog(lvProduct.getSelectedEntity().getId());

                dialog.setVisible(true);
            }
        });
        btUpdate.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                btUpdate.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        add(btUpdate);

    }


    public void init() {
        lvProduct.reloadData();
        lvProduct.setFirstSelected();
        lvProduct.requestFocus();
    }


}
