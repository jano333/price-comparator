package sk.hudak.pricecomparator.client.swing.pages.product;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.components.ActionLabelPanel;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.JefStringUtils;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.to.ProductDto;
import sk.hudak.pricecomparator.middle.to.ProductUpdateDto;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;

/**
 * Created by jan on 20. 2. 2016.
 */
public class ProductCreateUpdateDialog extends JDialog {

    private final Long productId;

    public ProductCreateUpdateDialog() {
        this(null);
    }

    public ProductCreateUpdateDialog(Long productId) {
        super(new JFrame(), (productId == null) ? "Vytvorenie produktu" : "Uprava produktu", true);
        this.productId = productId;

        // set the position of the window
        Point p = new Point(300, 300);
        setLocation(p.x, p.y);

        getContentPane().add(new ProductCreateUpdatePanel());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        pack();
        setSize(600, 400);
    }

    private boolean isNewProduct() {
        return productId == null;
    }


    // override the createRootPane inherited by the JDialog, to create the rootPane.
    // create functionality to close the window when "Escape" button is pressed
    public JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action action = new AbstractAction() {

            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", action);
        return rootPane;
    }

    private class ProductCreateUpdatePanel extends JPanel {

        // TODO btSave enablovat len ked su vsetky vyplnene udaje...

        // nazov
        private JTextField tfName;
        // jednotkovy typ
        private JRadioButton rbKus;
        private JRadioButton rbHmotnost;
        private JRadioButton rbObjem;
        private JRadioButton rbDlzka;
        private JRadioButton rbDavka;
        // pocet jednotiek
        private JTextField tfCountOfUnit;
        private JRadioButton rbKg;

        private JSpinner spAmountInPackage;


        private JButton btSave;
        private JButton btReset;

        //        private ProductCreateDto createDto;
//        private ProductUpdateDto updateDto;

        private int rowNumber = 1;
        private JRadioButton rbGram;
        private JTextField tfImage;

        public ProductCreateUpdatePanel() {
            setLayout(null);

            create_Nazov();
            create_JednotkovyTyp();
            create_PocetJednotiek();
            create_MnozstvoVBaleni();
//            create_Category();
//            create_Image();
            create_btSave();
            create_btReset();

            initGuiWithDataIfRequired();
        }

        private void create_Nazov() {
            add(GuiUtils.labelRequired("Názov: ", rowNumber));
            add(tfName = GuiUtils.textField(rowNumber));
        }

        private void create_JednotkovyTyp() {
            rowNumber++;
            add(GuiUtils.labelRequired("Jednotkový typ: ", rowNumber));

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

            rbHmotnost = new JRadioButton("hmotnosť");
            rbHmotnost.setCursor(GuiUtils.HAND_CURSOR);
            rbHmotnost.setBounds(
                    rbKus.getX() + rbKus.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                    GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                    rbHmotnost.getPreferredSize().width,
                    GuiUtils.ROW_HEIGHT);
            rbHmotnost.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showVahaOptions(true);
                }
            });
            add(rbHmotnost);

            rbObjem = new JRadioButton("objem");
            rbObjem.setCursor(GuiUtils.HAND_CURSOR);
            rbObjem.setBounds(
                    rbHmotnost.getX() + rbHmotnost.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                    GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                    rbObjem.getPreferredSize().width,
                    GuiUtils.ROW_HEIGHT);
            add(rbObjem);

            rbDlzka = new JRadioButton("dĺžka");
            rbDlzka.setCursor(GuiUtils.HAND_CURSOR);
            rbDlzka.setBounds(
                    rbObjem.getX() + rbObjem.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                    GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                    rbDlzka.getPreferredSize().width,
                    GuiUtils.ROW_HEIGHT);
            add(rbDlzka);

            rbDavka = new JRadioButton("dávka");
            rbDavka.setCursor(GuiUtils.HAND_CURSOR);
            rbDavka.setBounds(
                    rbDlzka.getX() + rbDlzka.getWidth() + GuiUtils.GAP_AFTER_LABEL,
                    GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                    rbObjem.getPreferredSize().width,
                    GuiUtils.ROW_HEIGHT);
            add(rbDavka);

            ButtonGroup bgJednotkovyTyp = new ButtonGroup();
            bgJednotkovyTyp.add(rbKus);
            bgJednotkovyTyp.add(rbHmotnost);
            bgJednotkovyTyp.add(rbObjem);
            bgJednotkovyTyp.add(rbDlzka);
            bgJednotkovyTyp.add(rbDavka);
        }

        private void create_PocetJednotiek() {
            rowNumber++;
            add(GuiUtils.labelRequired("Počet jednotiek: ", rowNumber));

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

        private void create_MnozstvoVBaleni() {
            rowNumber++;
            add(GuiUtils.labelRequired("Množstvo v balení (ks): ", rowNumber));
            spAmountInPackage = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
            spAmountInPackage.setBounds(
                    GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                    GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                    GuiUtils.SPINNER_WIDTH,
                    GuiUtils.ROW_HEIGHT);
            add(spAmountInPackage);
        }


        private void initGuiWithDataIfRequired() {
            if (isNewProduct()) {
                return;
            }
            ProductDto productDto = ServiceLocator.getService().getProduct(productId);
            // nazov
            tfName.setText(productDto.getName());
            // unit
            switch (productDto.getUnit()) {
                case KUS:
                    rbKus.setSelected(true);
                    break;
                case KILOGRAM:
                    rbHmotnost.setSelected(true);
                    break;
                case LITER:
                    rbObjem.setSelected(true);
                    break;
                case METER:
                    rbDlzka.setSelected(true);
                    break;
                case DAVKA:
                    rbDavka.setSelected(true);
                    break;
                default:
                    throw new PriceComparatorException("Not supported type of unit");
            }
            // pocet jednotiek
            if (Unit.KUS.equals(productDto.getUnit()) || Unit.DAVKA.equals(productDto.getUnit())) {
                tfCountOfUnit.setText(JefStringUtils.convertAndRound(productDto.getCountOfUnit(), 0));
            } else {
                tfCountOfUnit.setText(JefStringUtils.convertAndRound(productDto.getCountOfUnit(), 3));
            }
            // mnozstvo
            spAmountInPackage.getModel().setValue(productDto.getCountOfItemInOnePackage());
        }

        private void create_btReset() {
            btReset = GuiUtils.button("Reset");
            btReset.setBounds(
                    GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL + btSave.getWidth() + GuiUtils.GAP_AFTER_LABEL,
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

        private void create_Image() {
            rowNumber++;
            ActionLabelPanel alImage = new ActionLabelPanel("Obrázok") {
                @Override
                protected void onClick() {
                    final JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(new ImageFilter());
                    int returnVal = fc.showOpenDialog(ProductCreateUpdatePanel.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        tfImage.setText(file.getAbsolutePath());
                    }
                }
            };
            alImage.setBounds(
                    GuiUtils.LEFT_BORDER,
                    GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                    GuiUtils.LABEL_WIDTH,
                    GuiUtils.ROW_HEIGHT);

            tfImage = GuiUtils.textField(rowNumber);
            tfImage.setEditable(false);

            add(alImage);
            add(tfImage);
        }

        private void create_btSave() {
            rowNumber++;
            btSave = GuiUtils.button("Uložiť");
            btSave.setBounds(
                    GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                    GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                    btSave.getPreferredSize().width,
                    GuiUtils.ROW_HEIGHT);
            btSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onSaveAction();
                }
            });
            add(btSave);
        }

        private void showVahaOptions(boolean visible) {
            rbKg.setVisible(visible);
            rbGram.setVisible(visible);
        }


        private void onSaveAction() {
            if (isNewProduct()) {
                onSaveNewProduct();
            } else {
                onSaveExistingProduct();
            }

        }

        private void onSaveNewProduct() {
            ProductCreateDto createDto = new ProductCreateDto();
            createDto.setName(guiGetName());
            createDto.setUnit(guiGetUnit());
            createDto.setCountOfUnit(guiGetCountOfUnit());
            createDto.setCountOfItemInOnePackage(guiGetCountOfItemInPackage());
//            createDto.setImageLocalPath(tfImage.getText());
//            if (StringUtils.isNotBlank(tfImage.getText())) {
//                try {
//                    ByteArrayOutputStream imageContent = new ByteArrayOutputStream();
//                    Files.copy(Paths.get(tfImage.getText()), imageContent);
//                    createDto.setImageContent(imageContent.toByteArray());
//                } catch (IOException e) {
//                    //TODO
//                    e.printStackTrace();
//                }
//            }

            ServiceLocator.getService().createProduct(createDto);

            onResetAction();
        }

        private void onSaveExistingProduct() {
            ProductUpdateDto updateDto = new ProductUpdateDto();
            updateDto.setId(productId);
            updateDto.setName(guiGetName());
            updateDto.setUnit(guiGetUnit());
            updateDto.setCountOfUnit(guiGetCountOfUnit());
            updateDto.setCountOfItemInOnePackage(guiGetCountOfItemInPackage());

            ServiceLocator.getService().updateProduct(updateDto);
        }

        private BigDecimal guiGetCountOfUnit() {
            return new BigDecimal(tfCountOfUnit.getText().replace(",", "."));
        }

        private Integer guiGetCountOfItemInPackage() {
            return (Integer) spAmountInPackage.getValue();
        }

        private String guiGetName() {
            return tfName.getText();
        }

        private Unit guiGetUnit() {
            if (rbKus.isSelected()) {
                return Unit.KUS;
            }
            if (rbHmotnost.isSelected()) {
                return Unit.KILOGRAM;
            }
            if (rbDlzka.isSelected()) {
                return Unit.METER;
            }
            if (rbObjem.isSelected()) {
                return Unit.LITER;
            }
            if (rbDavka.isSelected()) {
                return Unit.DAVKA;
            }
            throw new PriceComparatorException("Not supported unit");

        }

        private void onResetAction() {
            tfName.setText("");
            rbKus.setSelected(true);
            tfCountOfUnit.setText("");
            spAmountInPackage.setValue((Integer.valueOf(1)));
//            tfImage.setText("");
            //focus
            tfName.requestFocus();
        }

        public void init() {
            tfName.requestFocus();
        }

        private class ImageFilter extends FileFilter {

            //Accept all directories and all gif, jpg, tiff, or png files.
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }

                if (f.getName().endsWith(".tiff")) {
                    return true;
                }
                if (f.getName().endsWith(".tif")) {
                    return true;
                }
                if (f.getName().endsWith(".gif")) {
                    return true;
                }
                if (f.getName().endsWith(".jpeg")) {
                    return true;
                }
                if (f.getName().endsWith(".jpg")) {
                    return true;
                }
                if (f.getName().endsWith(".png")) {
                    return true;
                }
                return false;
            }

            public String getDescription() {
                return "Obrázky";
            }
        }
    }


}
