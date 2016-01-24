package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.GroupOfProductListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by jan on 7. 11. 2015.
 */
public class GroupOfProductAddProductPage extends JPanel {

    private GroupOfProductListViewPanel lvGroup;
    private ProductSelectionListView lvProductsInGroup;
    private ProductSelectionListView lvProductsNotInGroup;

    private List<ProductListDto> addProducts = new ArrayList<>();

    private JButton btAddProduct;

    public GroupOfProductAddProductPage() {
        setLayout(null);

        // 1 riadok
        int rowNumber = 1;
        add(GuiUtils.labelRequired("Skupina: ", rowNumber));

        lvGroup = new GroupOfProductListViewPanel() {
            @Override
            protected void onSelectionChanged() {
                lvProductsInGroup.reloadData();
                lvProductsNotInGroup.reloadData();
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

        lvProductsInGroup = new ProductSelectionListView() {
            @Override
            public List<ProductListDto> readData() {
                if (lvGroup.getSelectedEntity() == null) {
                    return Collections.emptyList();
                }
                List<ProductListDto> fromServer = ServiceLocator.getService().getProductsInGroup(lvGroup.getSelectedEntity().getId());
                List<ProductListDto> result = new ArrayList<>();
                result.addAll(addProducts);
                result.addAll(fromServer);
                return result;
            }
        };
        lvProductsInGroup.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                6 * 17);
        lvProductsInGroup.setReadOnly();
        add(lvProductsInGroup);

        rowNumber = rowNumber + 4;
        add(GuiUtils.labelRequired("Nepridane produkty: ", rowNumber));
        lvProductsNotInGroup = new ProductSelectionListView() {
            @Override
            public List<ProductListDto> readData() {
                if (lvGroup.getSelectedEntity() == null) {
                    return Collections.emptyList();
                }
                List<ProductListDto> fromServer = ServiceLocator.getService().getProductsNotInGroup(lvGroup.getSelectedEntity().getId());
                List<ProductListDto> result = new ArrayList<>();
                result.addAll(fromServer);
                result.removeAll(addProducts);

                return result;
            }

            @Override
            protected void onMouseDoubleClick(ProductListDto entity) {
                addProducts.add(lvProductsNotInGroup.getSelectedEntity());
                lvProductsInGroup.reloadData();
                lvProductsNotInGroup.reloadData();
            }
        };
        lvProductsNotInGroup.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                6 * 17);
        add(lvProductsNotInGroup);


        rowNumber = rowNumber + 4;
        btAddProduct = GuiUtils.button("Pridaj produkt");
        btAddProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                btAddProduct.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        btAddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddAction();
            }
        });
        add(btAddProduct);


    }

    private void onAddAction() {
        Set<Long> productsId = new HashSet<>();
        for (ProductListDto addProduct : addProducts) {
            productsId.add(addProduct.getId());
        }
        ServiceLocator.getService().addProductsToGroup(productsId, lvGroup.getSelectedEntity().getId());
        addProducts.clear();
    }

    public void init() {
        lvGroup.reloadData();
        lvGroup.setFirstSelected();
    }
}
