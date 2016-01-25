package sk.hudak.pricecomparator.client.swing.components;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

/**
 * Created by jan on 24. 1. 2016.
 */
public abstract class BasicTable<T> extends JPanel {


    private final MyTableModel myTableModel;
    private final JTable table;

    private List<T> data;
    private List<? extends BasicColumn> columns;

    public BasicTable(List<? extends BasicColumn> columns) {
        super(new GridLayout(1, 0));
        this.columns = columns;

        myTableModel = new MyTableModel();
        table = new JTable(myTableModel);

        //TODO dimenzion ako metodu s moznostou override
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

//        initColumnSizes(table);
        initColumn(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private void initColumn(JTable table) {
        //nastavim vlastny cell render
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);

            BasicColumn basicColumn = columns.get(i);

            column.setCellRenderer(basicColumn.getTableCellRenderer());
            column.setPreferredWidth(basicColumn.getWidth());
        }
    }

    protected abstract List<T> loadData();

    private List<T> internalData() {
        if (data == null) {
            data = loadData();
        }
        return data;
    }

    public void reload() {
        data = null;
        table.invalidate();
        table.repaint();
    }


    class MyTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return internalData().size();
        }

        @Override
        public int getColumnCount() {
            return columns.size();
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columns.get(columnIndex).getHeaderName();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            T rowDto = internalData().get(rowIndex);
            Object obj = columns.get(columnIndex).getValueFor(rowDto);
            return obj;
        }
    }

    class MyTableColumnModel extends DefaultTableColumnModel {
        //TODO

    }


}
