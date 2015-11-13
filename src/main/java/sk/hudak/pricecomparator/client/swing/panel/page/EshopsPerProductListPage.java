package sk.hudak.pricecomparator.client.swing.panel.page;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.EshopListDto;
import sk.hudak.pricecomparator.server.downloader.Downloader;
import sk.hudak.pricecomparator.server.downloader.EshopProductPriceDto;
import sk.hudak.pricecomparator.server.downloader.ProductPriceListDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jan on 7. 11. 2015.
 */
public class EshopsPerProductListPage extends JPanel {

    private ProductSelectionListView lvProduct;
    private EshopSelectionListViewPanel lvEshopsWithProduct;
    private JButton btDownloadPrices;

    public EshopsPerProductListPage() {
        setLayout(null);

        int rowNumber = 1;
        add(GuiUtils.label("Produkt: ", rowNumber));
        lvProduct = new ProductSelectionListView() {
            @Override
            protected void onSelectionChanged() {
                onProductChanged();
            }
        };
        lvProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                6 * 17);
        add(lvProduct);

        rowNumber = rowNumber + 4;
        JLabel lbEshopWithProducts = GuiUtils.label("Eshopy s produktom: ", rowNumber);
        lbEshopWithProducts.setForeground(Color.gray);
        add(lbEshopWithProducts);

        lvEshopsWithProduct = new EshopSelectionListViewPanel() {
            @Override
            public java.util.List<EshopListDto> readData() {
                return ServiceLocator.getService().getEshopsWithProduct(lvProduct.getSelectedEntity().getId());
            }

            @Override
            protected Color getForegroundColor() {
                return Color.gray;
            }
        };

        lvEshopsWithProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                6 * 17); // je pocet vyditelnych riadkov
        add(lvEshopsWithProduct);

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
        Downloader downloader = new Downloader();
        ProductPriceListDto result = downloader.downloadProductInfoForProduct(lvProduct.getSelectedEntity().getId());
        java.util.List<EshopProductPriceDto> eshopProductPriceDtos = result.getEshopProductPriceDtos();
        for (EshopProductPriceDto eshopProductPriceDto : eshopProductPriceDtos) {
            StringBuilder sb = new StringBuilder();
            sb.append(eshopProductPriceDto.getEshopName());
            sb.append(" ");
            sb.append(eshopProductPriceDto.getEshopProductInfo().getPriceForUnit());
            sb.append(" ");
            sb.append(eshopProductPriceDto.getProductEshopPage());
            System.out.println(sb.toString());
        }


    }

}
