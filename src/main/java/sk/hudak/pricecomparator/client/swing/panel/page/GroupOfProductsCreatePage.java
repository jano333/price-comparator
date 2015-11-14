package sk.hudak.pricecomparator.client.swing.panel.page;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.panel.ProductSelectionListView;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductCreateDto;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 3. 11. 2015.
 */
public class GroupOfProductsCreatePage extends JPanel {

    private GroupOfProductCreateDto createDto;
    private List<ProductListDto> selectedProducts = new ArrayList<>();

    private JTextField tfName;
    private ProductSelectionListView lvSelectedProduct;


    private final ProductSelectionListView lvProduct;

    private final JButton btCreate;
    private final JButton btReset;

    public GroupOfProductsCreatePage() {
        setLayout(null);

        createDto = new GroupOfProductCreateDto();

        int rowNumber = 1;
        add(GuiUtils.labelRequired("Názov: ", rowNumber));
        add(tfName = GuiUtils.textField(rowNumber));

        rowNumber++;
        add(GuiUtils.labelRequired("Vybrate produkty: ", rowNumber));

        lvSelectedProduct = new ProductSelectionListView() {

            @Override
            public List<ProductListDto> readData() {
                return selectedProducts;
            }

            @Override
            protected void onDeleteKeyPressed(ProductListDto entity) {
                selectedProducts.remove(entity);
                lvSelectedProduct.reloadData();
            }
        };
        lvSelectedProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                6 * 17);
        add(lvSelectedProduct);

        rowNumber = rowNumber + 4;
        add(GuiUtils.label("Produkt: ", rowNumber));

        lvProduct = new ProductSelectionListView() {

            @Override
            protected void onMouseDoubleClick(ProductListDto entity) {
                selectedProducts.add(entity);
                lvSelectedProduct.reloadData();
            }
        };
        lvProduct.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                400,
                6 * 17);
        add(lvProduct);

        rowNumber = rowNumber + 4;
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

    }

    private void onCreateAction() {
        createDto.setName(tfName.getText());

        List<ProductListDto> productListDtoList = lvSelectedProduct.readData();
        List<Long> ids = new ArrayList<>(productListDtoList.size());
        for (ProductListDto dto : productListDtoList) {
            ids.add(dto.getId());
        }
        createDto.setProductIds(ids);

        ServiceLocator.getService().createGroupOfProduct(createDto);

        onResetAction();
    }

    private void onResetAction() {
        createDto = new GroupOfProductCreateDto();

        tfName.setText("");
        selectedProducts.clear();
        lvSelectedProduct.reloadData();

        tfName.requestFocus();
    }


    public void init() {
        lvProduct.reloadData();
        tfName.requestFocus();
    }
}
