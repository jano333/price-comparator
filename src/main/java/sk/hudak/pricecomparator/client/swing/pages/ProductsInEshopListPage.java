package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.table.*;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.to.EshopListDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopFindDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceInfoListDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 20. 12. 2015.
 */
public class ProductsInEshopListPage extends JPanel {

    private JComboBox<EshopListDto> cbEshops;
    private JTextField tfProductName;
    private JCheckBox chbOnlyInAction;
    private BasicTable<ProductInEshopPriceInfoListDto> table;

    public ProductsInEshopListPage() {
        setLayout(null);

        int rowNumber = 1;
        add(GuiUtils.label("Produkty v eshope: ", rowNumber + 1));

        JPanel compozite = new JPanel(new FlowLayout(FlowLayout.LEFT));

        compozite.add(new JLabel("Eshop:"));
        compozite.add(cbEshops = new JComboBox<>(ServiceLocator.getService().findAllEshops().toArray(new EshopListDto[0])));

        compozite.add(new JLabel("Názov produktu:"));
        compozite.add(tfProductName = new JTextField("", 20));

        compozite.add(chbOnlyInAction = new JCheckBox("Len v akcii"));
        compozite.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                800,
                2 * 17);
        add(compozite);

        cbEshops.setRenderer(new EshopComboBoxRenderer());
        cbEshops.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFilterEshopChanged();
            }
        });

        tfProductName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                onFilterProductNameChanged();
            }
        });

        chbOnlyInAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFilterOnlyInAction();
            }
        });

        rowNumber = rowNumber + 2;


        List<BasicColumn> columns = new ArrayList<>();
        columns.add(new PictureColumn("pictureFullPath", "Obrázok", 100));
        columns.add(new TextColumn("productName", "Názov", 400));

        EuroColumn clPriceForPackage = new EuroColumn("priceForPackage", 2, "Cena(€)", 80);
        clPriceForPackage.setAlignment(EuroColumn.TEXT_ALIGNMENT.CENTER);
        columns.add(clPriceForPackage);

        EuroColumn clPriceForUnit = new EuroColumn("priceForUnit", 5, "Jednotková cena(€)", 120);
        clPriceForUnit.setAlignment(EuroColumn.TEXT_ALIGNMENT.CENTER);
        columns.add(clPriceForUnit);

        ProductActionColumn clProductActionColumn = new ProductActionColumn("productAction", "Akcia", 50);
        clProductActionColumn.setAlignment(ProductActionColumn.TEXT_ALIGNMENT.CENTER);
        columns.add(clProductActionColumn);

        DateColumn clActionValidTo = new DateColumn("actionValidTo", "Platnosť akcie", 100);
        clActionValidTo.setAlignment(EuroColumn.TEXT_ALIGNMENT.CENTER);
        columns.add(clActionValidTo);

        DateTimeColumn clLastUpdatedPrice = new DateTimeColumn("lastUpdatedPrice", "Aktualizovane o", 120);
        clActionValidTo.setAlignment(EuroColumn.TEXT_ALIGNMENT.CENTER);
        columns.add(clLastUpdatedPrice);

        columns.add(new TextColumn("productEshopPage", "Stránka produktu", 400));

        table = new BasicTable<ProductInEshopPriceInfoListDto>(columns) {
            @Override
            protected List<ProductInEshopPriceInfoListDto> loadData() {
                //TODO eshop musi byt povinny teda vyber eshopu
                return ServiceLocator.getService().old_findProductsInEshopPriceInfo(createFindDto());
            }
        };

        table.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LIST_VIEW_SELECTOR_WIDTH,
                17 * 17);
        add(table);
    }

    private ProductInEshopFindDto createFindDto() {
        ProductInEshopFindDto findDto = new ProductInEshopFindDto();
        findDto.setEshopId(getFilterEshopId());
        findDto.setProductName(getFilterProductName());
        findDto.setOnlyInAction(isOnlyInActionChecked());
        return findDto;
    }

    private void onFilterEshopChanged() {
        table.reload();
    }

    private void onFilterProductNameChanged() {
        table.reload();
    }

    private void onFilterOnlyInAction() {
        table.reload();
    }

    private Long getFilterEshopId() {
        return cbEshops.getSelectedItem() != null ? ((EshopListDto) cbEshops.getSelectedItem()).getId() : null;
    }

    private String getFilterProductName() {
        return tfProductName.getText();
    }

    private boolean isOnlyInActionChecked() {
        return chbOnlyInAction.isSelected();
    }

    public void init() {
        cbEshops.setSelectedIndex(0);
    }
}
