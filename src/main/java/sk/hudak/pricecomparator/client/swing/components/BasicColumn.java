package sk.hudak.pricecomparator.client.swing.components;

import javax.swing.table.TableCellRenderer;

/**
 * Created by jan on 24. 1. 2016.
 */
public abstract class BasicColumn {

    private String headerName;
    private TableCellRenderer tcr;
    private int width;

    public BasicColumn(String headerName, int width) {
        this.headerName = headerName;
        this.width = width;
    }

    public TableCellRenderer getTableCellRenderer() {
        if (tcr == null) {
            tcr = createTableCellRenderer();
        }
        return tcr;
    }

    protected abstract TableCellRenderer createTableCellRenderer();

    public abstract <T> Object getValueFor(T rowDto);


    public String getHeaderName() {
        return headerName;
    }

    public int getWidth() {
        return width;
    }
}
