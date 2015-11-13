package sk.hudak.pricecomparator.client.swing.panel.page;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.GroupOfProductListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;
import sk.hudak.pricecomparator.server.downloader.Downloader;
import sk.hudak.pricecomparator.server.downloader.EshopProductPriceDto;
import sk.hudak.pricecomparator.server.downloader.GroupPriceListDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by jan on 5. 11. 2015.
 */
public class GroupOfProductListPage extends JPanel {

    private final JButton btCreate;
    private GroupOfProductListViewPanel lvGroup;
    private ProductSelectionListView lvProduct;

    public GroupOfProductListPage() {
        setLayout(null);

        // 1 riadok
        int rowNumber = 1;
        add(GuiUtils.label("Skupina: ", rowNumber));

        lvGroup = new GroupOfProductListViewPanel() {
            @Override
            protected void onSelectionChanged() {
                lvProduct.reloadData();
            }
        };
        lvGroup.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                6 * 17);
        add(lvGroup);

        // 2 riadok
        rowNumber = rowNumber + 4;
        add(GuiUtils.label("Produkty v skupine: ", rowNumber));

        lvProduct = new ProductSelectionListView() {
            @Override
            public List<ProductListDto> readData() {
                return ServiceLocator.getService().getProductsInGroup(lvGroup.getSelectedEntity().getId());
            }
        };
        lvProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                6 * 17);
        add(lvProduct);

        rowNumber = rowNumber + 4;
        btCreate = GuiUtils.button("Stihani info");
        btCreate.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                btCreate.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        btCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Downloader downloader = new Downloader();
                GroupPriceListDto groupPriceListDto = downloader.downloadProductInfoForGroup(lvGroup.getSelectedEntity().getId());

                java.util.List<EshopProductPriceDto> eshopProductPriceDtos = groupPriceListDto.getEshopProductPriceDtos();
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
        });
        add(btCreate);


    }

    public void reloadData() {
        lvProduct.reloadData();
    }
}
