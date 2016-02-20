package sk.hudak.pricecomparator.client.swing.pages.product;

import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.to.ProductListDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        lvProduct = new ProductSelectionListView() {
            @Override
            protected void onMouseDoubleClick(ProductListDto entity) {
                //TODO
                super.onMouseDoubleClick(entity);
            }
        };

        lvProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                250);
        add(lvProduct);

        rowNumber = rowNumber + 9;

        JButton btEdit = GuiUtils.button("edit product");
        btEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductCreateEditDialog dialog = new ProductCreateEditDialog(1L);
                dialog.setSize(600, 400);
                dialog.setVisible(true);
            }
        });
        btEdit.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                btEdit.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        add(btEdit);

    }


    public void init() {
        lvProduct.reloadData();
        lvProduct.setFirstSelected();
        lvProduct.requestFocus();
    }


}
