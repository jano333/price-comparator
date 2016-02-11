package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.table.*;
import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductInEshopSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.to.EshopListDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopCustomListDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceInfoListDto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 20. 12. 2015.
 */
public class ProductsInEshopListPage extends JPanel {

    private EshopSelectionListViewPanel lvEshops;
    private ProductInEshopSelectionListView lvProductsInEshop;
    private BasicTable<ProductInEshopPriceInfoListDto> table;

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
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                6 * 17);
        add(lvEshops);

        rowNumber = rowNumber + 4;
        add(GuiUtils.label("Produkty v eshope: ", rowNumber));
        lvProductsInEshop = new ProductInEshopSelectionListView() {
            @Override
            public List<ProductInEshopCustomListDto> readData() {
                EshopListDto selectedEshop = lvEshops.getSelectedEntity();
                if (selectedEshop == null) {
                    return Collections.emptyList();
                }
                return ServiceLocator.getService().findProductsInEshop(selectedEshop.getId());
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
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                12 * 17); // je pocet vyditelnych riadkov
        add(lvProductsInEshop);

        rowNumber = rowNumber + 8;

        List<BasicColumn> columns = new ArrayList<>();
        columns.add(new PictureColumn("pictureFullPath", "Obrázok", 100));
        columns.add(new TextColumn("productName", "Názov", 400));
        columns.add(new EuroColumn("priceForPackage", 2, "Cena(€)", 80));
        columns.add(new TextColumn("priceForUnit", "Jednotková cena(€)", 120));
        columns.add(new ProductActionColumn("productAction", "Akcia", 50));
        columns.add(new DateTimeColumn("lastUpdatedPrice", "Aktualizovane o", 120));
        columns.add(new TextColumn("productEshopPage", "Stránka produktu", 400));

        table = new BasicTable<ProductInEshopPriceInfoListDto>(columns) {
            @Override
            protected List<ProductInEshopPriceInfoListDto> loadData() {
                EshopListDto selectedEntity = lvEshops.getSelectedEntity();
                if (selectedEntity == null) {
                    return new ArrayList<>();
                }
                return ServiceLocator.getService().findProductInEshopPriceInfoForEshop(
                        selectedEntity.getId());
            }
        };

        table.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                8 * 17);
        add(table);
    }

    private void onEshopChanged() {
        lvProductsInEshop.reloadData();
        table.reload();
    }

    public void init() {
        lvEshops.reloadData();
        lvEshops.setFirstSelected();


    }
}
