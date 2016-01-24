package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;
import sk.hudak.pricecomparator.middle.api.to.EshopCreateDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jan on 24. 10. 2015.
 */
public class EshopCreatePage extends JPanel {

    private JTextField tfName;

    private JTextField tfHomePage;

    private JLabel lbParserClassName;
    private JTextField tfParserClassName;

    private final JButton btCreate;
    private final JButton btReset;

    private EshopCreateDto createDto;

    public EshopCreatePage() {
        setLayout(null);

        createDto = new EshopCreateDto();

        int rowNumber = 1;
        add(GuiUtils.labelRequired("Názov: ", rowNumber));
        add(tfName = GuiUtils.textField(rowNumber));

        rowNumber++;
        add(lbParserClassName = GuiUtils.labelRequired("Parser implementacia: ", rowNumber));
        add(tfParserClassName = GuiUtils.textField(rowNumber));

        rowNumber++;
        add(GuiUtils.label("www stránka: ", rowNumber));
        add(tfHomePage = GuiUtils.textField(rowNumber));


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


    }

    private void onCreateAction() {
        createDto.setName(tfName.getText());
        createDto.setHomePage(tfHomePage.getText());
        createDto.setParserClassName(tfParserClassName.getText());

        ServiceLocator.getService().createEshop(createDto);

        onResetAction();
    }

    private void onResetAction() {
        createDto = new EshopCreateDto();

        tfName.setText("");
        tfHomePage.setText("");
        tfParserClassName.setText("");

        //focus
        tfName.requestFocus();
    }

    public void init() {
        tfName.requestFocus();
    }

}
