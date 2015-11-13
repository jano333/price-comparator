package sk.hudak.pricecomparator.client.swing;

import sk.hudak.pricecomparator.client.swing.components.PriceComparatorJFrame;

import javax.swing.*;

/**
 * Created by jan on 15. 10. 2015.
 */
public class MainSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PriceComparatorJFrame();
            }
        });
    }


}
