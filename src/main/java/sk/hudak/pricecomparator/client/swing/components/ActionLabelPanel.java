package sk.hudak.pricecomparator.client.swing.components;

import sk.hudak.pricecomparator.client.swing.utils.GuiUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by jan on 16. 10. 2015.
 */
public abstract class ActionLabelPanel extends JPanel {

    public ActionLabelPanel(String text) {
        this(text, FlowLayout.RIGHT);
    }

    public ActionLabelPanel(String text, int flowlayoutAlign) {
        setLayout(new FlowLayout(flowlayoutAlign));
        //TODO doriet to ze dam prec flowlayout...
//        setLayout(null);

        ImageIcon image = new ImageIcon(getClass().getResource("/swing/images/loupe.png"));

        JLabel comp = new JLabel(text);
        comp.setSize(GuiUtils.LABEL_WIDTH - image.getIconWidth(), GuiUtils.ROW_HEIGHT);

        add(comp);

        JLabel loupe = new JLabel(image);
        loupe.setSize(image.getIconWidth(), GuiUtils.ROW_HEIGHT);
        loupe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loupe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick();
            }
        });
        add(loupe);
    }

    protected abstract void onClick();


}
