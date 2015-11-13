package sk.hudak.pricecomparator.client.swing.components;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by jan on 17. 10. 2015.
 */
public abstract class BasicSelectionListViewPanel<T> extends JPanel implements ListCellRenderer<T> {

    private final JList jList;

    public BasicSelectionListViewPanel() {
        this(ListSelectionModel.SINGLE_SELECTION);
    }


    public void setFirstSelected() {
        jList.setSelectedIndex(0);
    }

    public BasicSelectionListViewPanel(int selectionMode) {
        setLayout(new BorderLayout());

        jList = new JList(readData().toArray());
        jList.setSelectionMode(selectionMode);
        //nastvi prvu moznost ako vybratu
        setFirstSelected();
        jList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jList.setCellRenderer(this);

        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    onSelectionChanged();
                }
            }
        });

        jList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    int index = list.locationToIndex(evt.getPoint());
                    Object elementAt = list.getModel().getElementAt(index);
                    onMouseDoubleClick((T) elementAt);
                }
            }
        });

        jList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                    onDeleteKeyPressed((T) list.getSelectedValue());
                }
            }
        });


        JScrollPane pane = new JScrollPane(jList);
        // roztiahne sa na celu plochu
        add(pane, BorderLayout.CENTER);
    }

    protected Color getForegroundColor() {
        return null;
    }

    protected void onSelectionChanged() {
        // nothing
    }

    protected void onMouseDoubleClick(T entity) {
        // nothing
    }

    protected void onDeleteKeyPressed(T entity) {
        // nothing
    }

    public void reloadData() {
        jList.setListData(readData().toArray());
    }

    public abstract List<T> readData();

    public T getSelectedEntity() {
        return (T) jList.getSelectedValue();
    }

    public void setReadOnly() {
        // zrusim ak je nieco vybrane:
        jList.setSelectedIndex(Integer.MAX_VALUE);
        // disablujem jlist
        jList.setEnabled(false);
    }

}
