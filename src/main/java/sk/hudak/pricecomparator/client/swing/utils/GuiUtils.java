package sk.hudak.pricecomparator.client.swing.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jan on 16. 10. 2015.
 */
public class GuiUtils {

    public static final int LEFT_BORDER = 10;
    public static final int TOP_BORDER = 15;

    public static final int LABEL_WIDTH = 150;

    public static final int ROW_HEIGHT = 20;

    public static final int GAP_BEETWEN_ROWS = 10;
    public static final int GAP_AFTER_LABEL = 5;

    // zarovnanie labelov
    public static final int LABEL_ALIGMENT = SwingConstants.RIGHT;
    /**
     * Defaultna sirka pre textfield.
     */
    public static final int TEXT_FIELD_WIDTH = 400;
    /**
     * Defaultna sirka pre spinner.
     */
    public static final int SPINNER_WIDTH = 50;
    public static final int LIST_VIEW_SELECTOR_WIDTH = 800;



    public static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);

    public static JLabel label(String text, int rowNumber) {
        return labelWithColor(text, rowNumber, null);
    }

    public static JLabel labelRequired(String text, int rowNumber) {
        return labelWithColor(text, rowNumber, Color.red);
    }

    public static JLabel labelWithColor(String text, int rowNumber, Color color) {
        JLabel lbName = new JLabel(text);
        lbName.setHorizontalAlignment(GuiUtils.LABEL_ALIGMENT);
        if (color != null) {
            lbName.setForeground(color);
        }
        lbName.setBounds(
                GuiUtils.LEFT_BORDER,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                GuiUtils.LABEL_WIDTH,
                GuiUtils.ROW_HEIGHT);
        return lbName;
    }

    public static JTextField textField(int rowNumber) {
        return textField(rowNumber, GuiUtils.TEXT_FIELD_WIDTH);
    }

    public static JTextField textField(int rowNumber, int width) {
        JTextField tfName = new JTextField();
        tfName.setBounds(
                GuiUtils.LEFT_BORDER + GuiUtils.LABEL_WIDTH + GuiUtils.GAP_AFTER_LABEL,
                GuiUtils.TOP_BORDER + ((rowNumber - 1) * GuiUtils.ROW_HEIGHT + ((rowNumber - 1) * GuiUtils.GAP_BEETWEN_ROWS)),
                width,
                GuiUtils.ROW_HEIGHT);

        return tfName;
    }

    public static JButton button(String name) {
        JButton btCreate = new JButton(name);
        btCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btCreate;
    }

}
