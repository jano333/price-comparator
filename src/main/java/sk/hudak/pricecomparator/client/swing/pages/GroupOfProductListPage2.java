package sk.hudak.pricecomparator.client.swing.pages;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jan on 9. 11. 2015.
 */
public class GroupOfProductListPage2 extends BasicPage {


    @Override
    protected void createButtonsForToolBar(JToolBar toolBar) {
        //Create and initialize the button.
        JButton button = new JButton();
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setToolTipText("Toto je tooltip");
//        button.addActionListener(this);
        button.setIcon(new ImageIcon(getClass().getResource("/swing/images/loupe.png")));
        toolBar.add(button);

//        toolBar.addSeparator();
//        toolBar.add(new Button("haha"));
    }

    @Override
    protected void createContent(JScrollPane scrollPane) {
        GroupOfProductListPage comp = new GroupOfProductListPage();
        scrollPane.add(comp);
        System.out.println("ano");
    }
}
