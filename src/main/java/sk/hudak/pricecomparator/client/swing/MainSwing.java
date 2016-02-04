package sk.hudak.pricecomparator.client.swing;

import sk.hudak.pricecomparator.client.swing.components.PriceComparatorJFrame;
import sk.hudak.pricecomparator.client.swing.components.TaskManager;
import sk.hudak.pricecomparator.server.eshops.tesco.TescoPictureLocalFinder;

import javax.swing.*;

/**
 * Created by jan on 15. 10. 2015.
 */
public class MainSwing {

    public static void main(String[] args) {
        final TaskManager taskManager = new TaskManager();
        //tasky pre stahovanie
        taskManager.initTaks();
        //TODO odkomentovat
        taskManager.startDownloading();

        // nakopiruj obrazky ak su neake nove...
        TescoPictureLocalFinder.main(args);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PriceComparatorJFrame(taskManager);
            }
        });
    }


}
