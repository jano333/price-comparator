package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.GroupOfProductListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopPriceResultListDto;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 5. 11. 2015.
 */
public class GroupOfProductListPage extends JPanel {

    private SimpleDateFormat dateFormater = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat dateTimeFormater = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");


    private GroupOfProductListViewPanel groupListView;
    private ProductSelectionListView productListView;

    private final JTextArea taPriceInfo;
    private final JButton btbtDownloadPrices;

    public GroupOfProductListPage() {
        setLayout(null);

        // 1 riadok
        int rowNumber = 1;
        add(GuiUtils.label("Skupina: ", rowNumber));

        groupListView = new GroupOfProductListViewPanel() {
            @Override
            protected void onSelectionChanged() {
                productListView.reloadData();
            }
        };
        groupListView.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                6 * 17);
        add(groupListView);

        // 2 riadok
        rowNumber = rowNumber + 4;
        add(GuiUtils.label("Produkty v skupine: ", rowNumber));

        productListView = new ProductSelectionListView() {
            @Override
            public List<ProductListDto> readData() {
                if (groupListView.getSelectedEntity() != null) {
                    return ServiceLocator.getService().findProductsInGroup(groupListView.getSelectedEntity().getId());
                } else {
                    return Collections.emptyList();
                }
            }
        };
        productListView.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                12 * 17);
        add(productListView);

        rowNumber = rowNumber + 8;
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
        btbtDownloadPrices = GuiUtils.button("Stiahni info o cene");
        btbtDownloadPrices.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                btbtDownloadPrices.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        btbtDownloadPrices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (groupListView.getSelectedEntity() == null) {
                    taPriceInfo.setText("");
                    return;
                }

                //TODO
                Long groupId = groupListView.getSelectedEntity().getId();

                List<ProductInEshopPriceResultListDto> result = ServiceLocator.getService().findPriceInfoInEshopsForGroup(groupId);

                String text = PriceFormaterUtils.createText(result);
//                System.out.println(text);

                taPriceInfo.setText(text);

                // ---------------------------
//                PriceDownloader priceDownloader = new PriceDownloader();
//                GroupPriceListDto groupPriceListDto = priceDownloader.downloadProductInfoForGroup(groupListView.getSelectedEntity().getId());
//
//                StringBuilder sb = new StringBuilder();
//                java.util.List<EshopProductPriceDto> eshopProductPriceDtos = groupPriceListDto.getEshopProductPriceDtos();
//                for (EshopProductPriceDto eshopProductPriceDto : eshopProductPriceDtos) {
//                    sb.append(eshopProductPriceDto.getEshopName()).append("\t").append(" ");
//                    sb.append(eshopProductPriceDto.getEshopProductInfo().getPriceForUnit()).append(" ");
//                    sb.append("€ za jednotku, ").append("\t");
//                    sb.append(eshopProductPriceDto.getEshopProductInfo().getPriceForPackage()).append(" ");
//                    sb.append("€ za balenie, ");
//                    sb.append(eshopProductPriceDto.getProductEshopPage()).append(" ");
//                    sb.append(System.lineSeparator());
//                }
//                taPriceInfo.setText(sb.toString());
            }
        });
        add(btbtDownloadPrices);


    }

    public void init() {
        groupListView.reloadData();
        groupListView.setFirstSelected();

    }
}
