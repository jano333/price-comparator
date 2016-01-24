package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.EshopSelectionListViewPanel;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.EshopListDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopCreateDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 17. 10. 2015.
 */
public class ProductInEshopCreatePage extends JPanel {

    private ProductSelectionListView lvProduct;
    private EshopSelectionListViewPanel lvEshopsWithoutProduct;
    private JTextField tfStrankaProduktu;
    private JButton btCreate;
    private JButton btReset;
    private EshopSelectionListViewPanel lvEshopsWithProduct;
    private ProductInEshopCreateDto createDto;

    public ProductInEshopCreatePage() {
        setLayout(null);

        createDto = new ProductInEshopCreateDto();

        int rowNumber = 1;
        add(GuiUtils.labelRequired("Produkt: ", rowNumber));
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
                6 * 17);
        add(lvProduct);

        rowNumber = rowNumber + 4;
        add(GuiUtils.labelRequired("Eshop bez produktu: ", rowNumber));
        lvEshopsWithoutProduct = new EshopSelectionListViewPanel() {
            @Override
            public List<EshopListDto> readData() {
                if (lvProduct.getSelectedEntity() != null) {
                    return ServiceLocator.getService().findEshopsWithoutProduct(lvProduct.getSelectedEntity().getId());
                } else {
                    return Collections.emptyList();
                }
            }
        };
        lvEshopsWithoutProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                6 * 17);
        add(lvEshopsWithoutProduct);

        rowNumber = rowNumber + 4;
        add(GuiUtils.labelRequired("Stránka produktu: ", rowNumber));
        add(tfStrankaProduktu = GuiUtils.textField(rowNumber, 600));

        rowNumber++;
        btCreate = GuiUtils.button("Vytvor");
        btCreate.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                btCreate.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        btCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCreateAction();
            }
        });
        add(btCreate);

        btReset = GuiUtils.button("Reset");
        btReset.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL + btCreate.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                btReset.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        btReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onResetAction();
            }
        });
        add(btReset);


        rowNumber++;// = rowNumber + 4;
        JLabel lbEshopWithProducts = GuiUtils.label("Eshopy s produktom: ", rowNumber);
        lbEshopWithProducts.setForeground(Color.gray);
        add(lbEshopWithProducts);

        lvEshopsWithProduct = new EshopSelectionListViewPanel() {
            @Override
            public List<EshopListDto> readData() {
                if (lvProduct.getSelectedEntity() != null) {
                    return ServiceLocator.getService().findEshopsWithProduct(lvProduct.getSelectedEntity().getId());
                } else {
                    return Collections.emptyList();
                }
            }

            @Override
            protected Color getForegroundColor() {
                return Color.gray;
            }
        };

        lvEshopsWithProduct.setReadOnly();


        lvEshopsWithProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                600,
                6 * 17); // je pocet vyditelnych riadkov
        add(lvEshopsWithProduct);


    }

    private void onCreateAction() {
        createDto.setProductId(lvProduct.getSelectedEntity().getId());
        createDto.setEshopId(lvEshopsWithoutProduct.getSelectedEntity().getId());
        createDto.setEshopProductPage(tfStrankaProduktu.getText());

        ServiceLocator.getService().createProductInEshop(createDto);

        onResetAction();
    }

    private void onResetAction() {
        createDto = new ProductInEshopCreateDto();

        tfStrankaProduktu.setText("");

        //focus
        lvProduct.requestFocus();

        // znovunacitanie eshopv
        lvEshopsWithProduct.reloadData();
        lvEshopsWithoutProduct.reloadData();
    }

    private void onProductChanged() {
        lvEshopsWithProduct.reloadData();
        lvEshopsWithoutProduct.reloadData();
    }

    public void init() {
        lvProduct.reloadData();
        lvProduct.setFirstSelected();

    }


}

