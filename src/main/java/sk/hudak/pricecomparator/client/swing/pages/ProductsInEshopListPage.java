package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductInEshopSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopCustomListDto;

import javax.swing.*;
import java.util.Collections;

/**
 * Created by jan on 20. 12. 2015.
 */
public class ProductsInEshopListPage extends JPanel {

    private EshopSelectionListViewPanel lvEshops;
    private ProductInEshopSelectionListView lvProductsInEshop;

    public ProductsInEshopListPage() {
        setLayout(null);

        int rowNumber = 1;
        add(GuiUtils.label("Eshopy: ", rowNumber));
        lvEshops = new EshopSelectionListViewPanel() {
            @Override
            protected void onSelectionChanged() {
                onEshopChanged();
            }
        };
        lvEshops.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                6 * 17);
        add(lvEshops);

        rowNumber = rowNumber + 4;
        JLabel lbEshopWithProducts = GuiUtils.label("Produkty v eshope: ", rowNumber);
        add(lbEshopWithProducts);

        lvProductsInEshop = new ProductInEshopSelectionListView() {
            @Override
            public java.util.List<ProductInEshopCustomListDto> readData() {
                if (lvEshops.getSelectedEntity() != null) {
                    return ServiceLocator.getService().findProductsInEshop(lvEshops.getSelectedEntity().getId());
                } else {
                    return Collections.emptyList();
                }
            }

            @Override
            protected void onMouseDoubleClick(ProductInEshopCustomListDto entity) {
                if (lvEshops.getSelectedEntity() == null) {
                    return;
                }
                openURLInExternalBrowser(entity.getEshopProductPage());
            }
        };
        lvProductsInEshop.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                12 * 17); // je pocet vyditelnych riadkov
        add(lvProductsInEshop);
    }

    private void onEshopChanged() {
        lvProductsInEshop.reloadData();
    }

    public void init() {
        lvEshops.reloadData();
        lvEshops.setFirstSelected();
    }
}
