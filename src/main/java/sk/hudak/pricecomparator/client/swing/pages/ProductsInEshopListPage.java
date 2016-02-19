package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.table.*;
import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.to.EshopListDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopFindDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceInfoListDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 20. 12. 2015.
 */
public class ProductsInEshopListPage extends JPanel {

    private EshopSelectionListViewPanel lvEshops;
    private JTextField tfProductName;
    private JCheckBox chbOnlyInAction;
    private BasicTable<ProductInEshopPriceInfoListDto> table;
//    private ProductInEshopSelectionListView lvProductsInEshop;

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

        add(GuiUtils.label("Produkty v eshope: ", rowNumber + 1));


        JPanel compozite = new JPanel(new FlowLayout(FlowLayout.LEFT));
        compozite.add(new JLabel("Názov produktu:"));
        compozite.add(tfProductName = new JTextField("", 20));
        compozite.add(chbOnlyInAction = new JCheckBox("Len v akcii"));
        compozite.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                800,
                2 * 17);
        add(compozite);

        tfProductName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onFilterProductNameChanged();
            }
        });

        chbOnlyInAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFilterOnlyInAction();
            }
        });

        rowNumber = rowNumber + 2;


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
                return ServiceLocator.getService().findProductsInEshopPriceInfo(createFindDto());
            }
        };

        table.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                17 * 17);
        add(table);

//        rowNumber = rowNumber + 8;
//        lvProductsInEshop = new ProductInEshopSelectionListView() {
//            @Override
//            public List<ProductInEshopCustomListDto> readData() {
//                EshopListDto selectedEshop = lvEshops.getSelectedEntity();
//                if (selectedEshop == null) {
//                    return Collections.emptyList();
//                }
//                return ServiceLocator.getService().findProductsInEshop(selectedEshop.getId());
//            }
//
//            @Override
//            protected void onMouseDoubleClick(ProductInEshopCustomListDto entity) {
//                if (lvEshops.getSelectedEntity() == null) {
//                    return;
//                }
//                openURLInExternalBrowser(entity.getEshopProductPage());
//            }
//        };
//        lvProductsInEshop.setBounds(
//                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
//                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
//                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
//                8 * 17); // je pocet vyditelnych riadkov
//        add(lvProductsInEshop);
    }

    private ProductInEshopFindDto createFindDto() {
        ProductInEshopFindDto findDto = new ProductInEshopFindDto();
        findDto.setEshopId(lvEshops.getSelectedEntity() != null ? lvEshops.getSelectedEntity().getId() : null);
        findDto.setProductName(getFilterProductName());
        findDto.setOnlyInAction(isOnlyInActionChecked());
        return findDto;
    }

    private void onFilterProductNameChanged() {
        table.reload();
    }

    private String getFilterProductName() {
        return tfProductName.getText();
    }

    private void onFilterOnlyInAction() {
        table.reload();
    }

    private boolean isOnlyInActionChecked() {
        return chbOnlyInAction.isSelected();
    }

    private void onEshopChanged() {
        table.reload();
//        lvProductsInEshop.reloadData();
    }

    public void init() {
        lvEshops.reloadData();
        lvEshops.setFirstSelected();


    }
}
