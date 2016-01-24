package sk.hudak.pricecomparator.client.swing.pages;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jan on 9. 11. 2015.
 */
public abstract class BasicPage extends JPanel {

    public BasicPage() {
        super(new BorderLayout());

        JToolBar toolBar = new JToolBar("Still draggable");
        createButtonsForToolBar(toolBar);
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        add(toolBar, BorderLayout.PAGE_START);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(400,400);
        add(scrollPane, BorderLayout.CENTER);
        createContent(scrollPane);

    }


    protected abstract void createButtonsForToolBar(JToolBar toolBar);

    protected abstract void createContent(JScrollPane scrollPane);
}
