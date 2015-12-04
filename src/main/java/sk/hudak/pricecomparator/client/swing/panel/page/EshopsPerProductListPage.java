package sk.hudak.pricecomparator.client.swing.panel.page;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.EshopListDto;
import sk.hudak.pricecomparator.server.downloader.EshopProductPriceDto;
import sk.hudak.pricecomparator.server.downloader.PriceDownloader;
import sk.hudak.pricecomparator.server.downloader.ProductPriceListDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

/**
 * Created by jan on 7. 11. 2015.
 */
public class EshopsPerProductListPage extends JPanel {

    private ProductSelectionListView lvProduct;
    private EshopSelectionListViewPanel lvEshopsWithProduct;
    private JButton btDownloadPrices;
    private final JTextArea taPriceInfo;

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
        lbEshopWithProducts.setForeground(Color.gray);
        add(lbEshopWithProducts);

        lvEshopsWithProduct = new EshopSelectionListViewPanel() {
            @Override
            public java.util.List<EshopListDto> readData() {
                if (lvProduct.getSelectedEntity() != null) {
                    return ServiceLocator.getService().getEshopsWithProduct(lvProduct.getSelectedEntity().getId());
                } else {
                    return Collections.emptyList();
                }
            }

            @Override
            protected Color getForegroundColor() {
                return Color.gray;
            }
        };

        lvEshopsWithProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                6 * 17); // je pocet vyditelnych riadkov
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
        btDownloadPrices = GuiUtils.button("Stiahni info o cene");
        btDownloadPrices.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                btDownloadPrices.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        btDownloadPrices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDownloadAction();
            }
        });
        add(btDownloadPrices);


    }

    private void onProductChanged() {
        lvEshopsWithProduct.reloadData();
    }

    private void onDownloadAction() {
        PriceDownloader priceDownloader = new PriceDownloader();
        ProductPriceListDto result = priceDownloader.downloadProductInfoForProduct(lvProduct.getSelectedEntity().getId());
        java.util.List<EshopProductPriceDto> eshopProductPriceDtos = result.getEshopProductPriceDtos();
        StringBuilder sb = new StringBuilder();
        for (EshopProductPriceDto eshopProductPriceDto : eshopProductPriceDtos) {
            sb.append(eshopProductPriceDto.getEshopName()).append("\t").append(" ");
            sb.append(eshopProductPriceDto.getEshopProductInfo().getPriceForUnit()).append(" ");
            sb.append("€ za jednotku, ").append("\t");
            sb.append(eshopProductPriceDto.getEshopProductInfo().getPriceForPackage()).append(" ");
            sb.append("€ za balenie, ");
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
