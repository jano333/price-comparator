package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.to.EshopListDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceResultListDto;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 7. 11. 2015.
 */
public class EshopsPerProductListPage extends JPanel {

    private SimpleDateFormat dateFormater = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat dateTimeFormater = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private ProductSelectionListView lvProduct;
    private EshopSelectionListViewPanel lvEshopsWithProduct;
    private final JTextArea taPriceInfo;
//    private final BasicTable<ProductInEshopPriceResultListDto> table;

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
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
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
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                100);
        add(scrollPane);

//        rowNumber = rowNumber + 4;
//        List<BasicColumn> columns = new ArrayList<>();
//        columns.add(new TextColumn("eshopName", "Eshop", 100));
//        columns.add(new TextColumn("priceForUnit", "Cena za jednotku(€)", 100));
//        columns.add(new TextColumn("priceForPackage", "Cena za balenie(€)", 100));
//        columns.add(new TextColumn("productAction", "Akcia", 100));
//        columns.add(new TextColumn("lastUpdatedPrice", "Aktualizovane o", 120));
//        columns.add(new TextColumn("productEshopPage", "Stranka produktu", 100));
//
//        table = new BasicTable<ProductInEshopPriceResultListDto>(columns) {
//            @Override
//            protected List<ProductInEshopPriceResultListDto> loadData() {
//                if (lvProduct.getSelectedEntity() == null) {
//                    return new ArrayList<>();
//                }
//
//                Long productId = lvProduct.getSelectedEntity().getId();
//                List<ProductInEshopPriceResultListDto> result = ServiceLocator.getService().findPriceInfoInEshopsForProduct(productId);
//                return result;
//            }
//        };
//
//        JScrollPane scrollPane2 = new JScrollPane(table);
////        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane2.setBounds(
//                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
//                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
//                600,
//                100);
//        add(scrollPane2);
    }

    private void onProductChanged() {
        lvEshopsWithProduct.reloadData();
        showProductPriceInfo();

//        table.reload();
    }

    private void showProductPriceInfo() {
        if (lvProduct.getSelectedEntity() == null) {
            taPriceInfo.setText("");
            return;
        }

        Long productId = lvProduct.getSelectedEntity().getId();
        List<ProductInEshopPriceResultListDto> result = ServiceLocator.getService().findPriceInfoInEshopsForProduct(productId);

        //FIXME tabulku so sortovanim a paging :-)
        String text = PriceFormaterUtils.createText(result);

        taPriceInfo.setText(text);
    }

    public void init() {
        lvProduct.reloadData();
        lvProduct.setFirstSelected();
    }


}
