package sk.hudak.pricecomparator.client.swing.panel.page;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.ActionLabelPanel;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.canonical.Unit;
import sk.hudak.pricecomparator.middle.api.to.ProductCreateDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

/**
 * Created by jan on 15. 10. 2015.
 */
public class ProductCreatePage extends JPanel {


    // TODO btCreate enablovat len ked su vsetky vyplnene udaje...

    private JTextField tfName;

    private JLabel lbJednotkovyTyp;
    private JRadioButton rbKus;
    private JRadioButton rbVaha;
    private JRadioButton rbDlzka;
    private JRadioButton rbObjem;
    private JRadioButton rbDavka;

    private JSpinner spAmountInPackage;

    private JLabel lbCountOfUnit;
    private JTextField tfCountOfUnit;
    private JRadioButton rbKg;

    private JButton btCreate;
    private JButton btReset;

    private ProductCreateDto createDto;

    private int rowNumber = 1;
    private JRadioButton rbGram;

    public ProductCreatePage() {
        setLayout(null);

        createDto = new ProductCreateDto();

        add(GuiUtils.labelRequired("Názov: ", rowNumber));
        add(tfName = GuiUtils.textField(rowNumber));

        rowNumber++;
        add(GuiUtils.labelRequired("Množstvo v balení (ks): ", rowNumber));
        spAmountInPackage = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        spAmountInPackage.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.SPINNER_WIDTH,
                GuiUtils.ROW_HEIGHT);
        add(spAmountInPackage);

        create_JednotkovyTyp();
        create_Mnozstvo();
        create_Category();

        create_btCreate();
        create_btReset();
    }

    private void create_btReset() {
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

    private void create_Category() {
        rowNumber++;
        ActionLabelPanel alCategory = new ActionLabelPanel("Kategoria") {
            @Override
            protected void onClick() {
                System.out.println("clikol si");
                //TODO
            }
        };
        alCategory.setBounds(
                GuiUtils.LEFT_BORDER,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LABEL_WIDTH,
                GuiUtils.ROW_HEIGHT);
        add(alCategory);
    }

    private void create_btCreate() {
        rowNumber++;
        btCreate = GuiUtils.button("Vytvor produkt");
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
    }

    private void create_Mnozstvo() {
        rowNumber++;
        lbCountOfUnit = GuiUtils.label("Množstvo: ", rowNumber);
        add(lbCountOfUnit);

        tfCountOfUnit = GuiUtils.textField(rowNumber);
        tfCountOfUnit.setSize(100, tfCountOfUnit.getHeight());
        add(tfCountOfUnit);

        // vaha
        rbKg = new JRadioButton("kg");
        rbKg.setSelected(true);
        rbKg.setBounds(
                tfCountOfUnit.getX() + tfCountOfUnit.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                rbKg.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        add(rbKg);

        rbGram = new JRadioButton("gram");
        rbGram.setBounds(
                rbKg.getX() + rbKg.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                rbGram.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        add(rbGram);

        ButtonGroup bgVaha = new ButtonGroup();
        bgVaha.add(rbKg);
        bgVaha.add(rbGram);


        showVahaOptions(false);


    }

    private void showVahaOptions(boolean visible) {
        rbKg.setVisible(visible);
        rbGram.setVisible(visible);
    }

    private void create_JednotkovyTyp() {
        rowNumber++;
        lbJednotkovyTyp = GuiUtils.labelRequired("Jednotkový typ: ", rowNumber);
        add(lbJednotkovyTyp);

        rbKus = new JRadioButton("kus");
        rbKus.setSelected(true);
        rbKus.setCursor(GuiUtils.HAND_CURSOR);
        rbKus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showVahaOptions(false);
            }
        });
        rbKus.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                rbKus.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        add(rbKus);

        rbVaha = new JRadioButton("váha");
        rbVaha.setCursor(GuiUtils.HAND_CURSOR);
        rbVaha.setBounds(
                rbKus.getX() + rbKus.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                rbVaha.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        rbVaha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showVahaOptions(true);
            }
        });
        add(rbVaha);

        rbDlzka = new JRadioButton("dĺžka");
        rbDlzka.setCursor(GuiUtils.HAND_CURSOR);
        rbDlzka.setBounds(
                rbVaha.getX() + rbVaha.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                rbDlzka.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        add(rbDlzka);

        rbObjem = new JRadioButton("objem");
        rbObjem.setCursor(GuiUtils.HAND_CURSOR);
        rbObjem.setBounds(
                rbDlzka.getX() + rbDlzka.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                rbObjem.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        add(rbObjem);

        rbDavka = new JRadioButton("davka");
        rbDavka.setCursor(GuiUtils.HAND_CURSOR);
        rbDavka.setBounds(
                rbObjem.getX() + rbObjem.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                rbObjem.getPreferredSize().width,
                GuiUtils.ROW_HEIGHT);
        add(rbDavka);
//

        ButtonGroup bgJednotkovyTyp = new ButtonGroup();
        bgJednotkovyTyp.add(rbKus);
        bgJednotkovyTyp.add(rbVaha);
        bgJednotkovyTyp.add(rbDlzka);
        bgJednotkovyTyp.add(rbObjem);
        bgJednotkovyTyp.add(rbDavka);
    }


    private void onCreateAction() {
        createDto.setName(tfName.getText());
        createDto.setCountOfItemInOnePackage((Integer) spAmountInPackage.getValue());
        if (rbKus.isSelected()) {
            createDto.setUnit(Unit.KUS);
        } else if (rbVaha.isSelected()) {
            createDto.setUnit(Unit.KILOGRAM);
        } else if (rbDlzka.isSelected()) {
            createDto.setUnit(Unit.METER);
        } else if (rbObjem.isSelected()) {
            createDto.setUnit(Unit.LITER);
        } else if (rbDavka.isSelected()) {
            createDto.setUnit(Unit.DAVKA);
        }
        createDto.setCountOfUnit(new BigDecimal(tfCountOfUnit.getText().replace(",", ".")));

        ServiceLocator.getService().createProduct(createDto);

        onResetAction();
    }

    private void onResetAction() {
        createDto = new ProductCreateDto();

        tfName.setText("");
        spAmountInPackage.setValue((Integer.valueOf(1)));
        tfCountOfUnit.setText("");
        rbKus.setSelected(true);

        //focus
        tfName.requestFocus();
    }
}
