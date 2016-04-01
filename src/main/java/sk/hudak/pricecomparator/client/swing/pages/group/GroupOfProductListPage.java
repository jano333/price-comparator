package sk.hudak.pricecomparator.client.swing.pages.group;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.GroupOfProductListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.client.utils.PriceFormaterUtils;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceResultListDto;
import sk.hudak.pricecomparator.middle.to.ProductListDto;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 5. 11. 2015.
 */
@Deprecated
public class GroupOfProductListPage extends JPanel {

    private GroupOfProductListViewPanel groupListView;
    private ProductSelectionListView productListView;
    private final JTextArea taPriceInfo;

    public GroupOfProductListPage() {
        setLayout(null);

        // 1 riadok
        int rowNumber = 1;
        add(GuiUtils.label("Skupina: ", rowNumber));

        groupListView = new GroupOfProductListViewPanel() {
            @Override
            protected void onSelectionChanged() {
                onGroupChanged();
            }
        };
        groupListView.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
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
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
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
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                100);
        add(scrollPane);
    }

    public void init() {
        groupListView.reloadData();
        groupListView.setFirstSelected();
    }

    private void onGroupChanged() {
        productListView.reloadData();
        showProductPriceInfo();
    }

    private void showProductPriceInfo() {
        if (groupListView.getSelectedEntity() == null) {
            taPriceInfo.setText("");
            return;
        }

        Long groupId = groupListView.getSelectedEntity().getId();
        List<ProductInEshopPriceResultListDto> result = ServiceLocator.getService().findPriceInfoInEshopsForGroup(groupId);
        taPriceInfo.setText(PriceFormaterUtils.createText(result));
    }
}
