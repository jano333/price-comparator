package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.BasicColumn;
import sk.hudak.pricecomparator.client.swing.components.BasicTable;
import sk.hudak.pricecomparator.client.swing.components.TextColumn;
import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.EshopListDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopPriceResultListDto;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 7. 11. 2015.
 */
public class EshopsPerProductListPage extends JPanel {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private ProductSelectionListView lvProduct;
    private EshopSelectionListViewPanel lvEshopsWithProduct;
    private final JTextArea taPriceInfo;
    private final BasicTable<ProductInEshopPriceResultListDto> table;

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
                600,
                12 * 17);
        add(lvProduct);

        rowNumber = rowNumber + 8;
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
                600,
                6 * 17); // je pocet viditelnych riadkov
        add(lvEshopsWithProduct);

        rowNumber = rowNumber + 4;
        add(GuiUtils.label("Vysledne ceny: ", rowNumber));
        taPriceInfo = new JTextArea();
        taPriceInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taPriceInfo);
        scrollPane.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                100);
        add(scrollPane);

        rowNumber = rowNumber + 4;
        List<BasicColumn> columns = new ArrayList<>();
        columns.add(new TextColumn("eshopName", "Eshop", 100));
        columns.add(new TextColumn("priceForUnit", "Cena za jednotku(€)", 100));
        columns.add(new TextColumn("priceForUnit", "Cena za balenie(€)", 100));
        columns.add(new TextColumn("lastUpdatedPrice", "Aktualizovane o", 120));
        columns.add(new TextColumn("productEshopPage", "Stranka produktu", 100));

        table = new BasicTable<ProductInEshopPriceResultListDto>(columns) {
            @Override
            protected List<ProductInEshopPriceResultListDto> loadData() {
                if (lvProduct.getSelectedEntity() == null) {
                    return new ArrayList<>();
                }

                Long productId = lvProduct.getSelectedEntity().getId();
                List<ProductInEshopPriceResultListDto> result = ServiceLocator.getService().findPriceInfoInEshopsForProduct(productId);
                return result;
            }
        };

        JScrollPane scrollPane2 = new JScrollPane(table);
        scrollPane2.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                100);
//        add(scrollPane2);



    }

    private void onProductChanged() {
        lvEshopsWithProduct.reloadData();
        showProductPriceInfo();
    }

    private void showProductPriceInfo() {
        if (lvProduct.getSelectedEntity() == null) {
            taPriceInfo.setText("");
            return;
        }

        Long productId = lvProduct.getSelectedEntity().getId();
        List<ProductInEshopPriceResultListDto> result = ServiceLocator.getService().findPriceInfoInEshopsForProduct(productId);

        //FIXME tabulku so sortovanim a paging :-)
        StringBuilder sb = new StringBuilder();
        for (ProductInEshopPriceResultListDto eshopProductPriceDto : result) {
            sb.append(eshopProductPriceDto.getEshopName()).append("\t").append(" ");
            sb.append(eshopProductPriceDto.getPriceForUnit()).append(" ");
            sb.append("€ za jednotku, ").append("\t");
            sb.append(eshopProductPriceDto.getPriceForPackage()).append(" ");
            sb.append("€ za balenie, ").append("\t");
            sb.append("akt.: ");
            if (eshopProductPriceDto.getLastUpdatedPrice() == null) {
                sb.append("---").append("\t");

            } else {
                sb.append(sdf.format(eshopProductPriceDto.getLastUpdatedPrice())).append("\t");
            }
            sb.append(eshopProductPriceDto.getProductEshopPage()).append(" ");

//            sb.append("image path: ");
//            sb.append(eshopProductPriceDto.getEshopProductInfo().getProductImageUrl());
            sb.append(System.lineSeparator());
        }
        taPriceInfo.setText(sb.toString());
    }

    public void init() {
        lvProduct.reloadData();
        lvProduct.setFirstSelected();
    }


}
