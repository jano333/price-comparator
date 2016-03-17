package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.table.*;
import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.to.EshopListDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceResultListDto;
import sk.hudak.pricecomparator.middle.to.ProductListDto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 7. 11. 2015.
 */
public class EshopsPerProductListPage extends JPanel {

    private ProductSelectionListView lvProduct;
    private EshopSelectionListViewPanel lvEshopsWithProduct;
    private BasicTable<ProductInEshopPriceResultListDto> table;

    public EshopsPerProductListPage() {
        setLayout(null);

        int rowNumber = 1;
        add(GuiUtils.label("Produkty: ", rowNumber));
        lvProduct = new ProductSelectionListView() {
            @Override
            protected void onSelectionChanged() {
                onProductChanged();
            }
        };
        lvProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                12 * 17);
        add(lvProduct);

        rowNumber = rowNumber + 7;
        JLabel lbEshopWithProducts = GuiUtils.label("Eshopy s produktom: ", rowNumber);
        add(lbEshopWithProducts);

        lvEshopsWithProduct = new EshopSelectionListViewPanel() {
            @Override
            public java.util.List<EshopListDto> readData() {
                if (lvProduct.getSelectedEntity() != null) {
                    return ServiceLocator.getService().findEshopsWithProduct(lvProduct.getSelectedEntity().getId());
                } else {
                    return Collections.emptyList();
                }
            }

            @Override
            protected void onMouseDoubleClick(EshopListDto entity) {
                if (lvProduct.getSelectedEntity() != null) {
                    ProductInEshopDto productInEshop = ServiceLocator.getProductInEshopService().getProductInEshop(
                            lvProduct.getSelectedEntity().getId(),
                            entity.getId());
                    openURLInExternalBrowser(productInEshop.getEshopProductPage());

                }
            }
        };

        lvEshopsWithProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                6 * 17); // je pocet viditelnych riadkov
        add(lvEshopsWithProduct);

        rowNumber = rowNumber + 4;
        add(GuiUtils.label("Vysledne ceny: ", rowNumber));

        List<BasicColumn> columns = new ArrayList<>();
        columns.add(new TextColumn("eshopName", "Eshop", 100));
        columns.add(new TextColumn("priceForUnit", "Jednotková cena(€)", 120));
        columns.add(new EuroColumn("priceForPackage", 2, "Balenie(€)", 100));
        columns.add(new ProductActionColumn("productAction", "Akcia", 50));
        columns.add(new DateTimeColumn("lastUpdatedPrice", "Aktualizovane o", 120));
        columns.add(new TextColumn("productEshopPage", "Stranka produktu", 400));

        table = new BasicTable<ProductInEshopPriceResultListDto>(columns) {
            @Override
            protected List<ProductInEshopPriceResultListDto> loadData() {
                ProductListDto selectedEntity = lvProduct.getSelectedEntity();
                if (selectedEntity == null) {
                    return new ArrayList<>();
                }
                return ServiceLocator.getService().old_findPriceInfoInEshopsForProduct(selectedEntity.getId());
            }
        };

        table.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                8 * 17);
        add(table);
    }

    private void onProductChanged() {
        lvEshopsWithProduct.reloadData();
        table.reload();
    }

    public void init() {
        lvProduct.reloadData();
        lvProduct.setFirstSelected();
    }


}
