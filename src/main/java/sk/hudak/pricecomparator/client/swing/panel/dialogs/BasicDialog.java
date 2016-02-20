package sk.hudak.pricecomparator.client.swing.panel.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jan on 22. 11. 2015.
 */
@Deprecated
public abstract class BasicDialog extends JDialog {

    public BasicDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


}
