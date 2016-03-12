package sk.hudak.pricecomparator.client.swing.components.table;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

/**
 * Created by jan on 24. 1. 2016.
 */
public abstract class BasicColumn {

    private String headerName;
    private TableCellRenderer tcr;
    private int width;
    protected TEXT_ALIGNMENT alignment = TEXT_ALIGNMENT.LEFT;

    public enum TEXT_ALIGNMENT {
        LEFT(JLabel.LEFT),
        RIGHT(JLabel.RIGHT),
        CENTER(JLabel.CENTER);

        int aligment;

        TEXT_ALIGNMENT(int aligment) {
            this.aligment = aligment;
        }

        public int getAligment() {
            return aligment;
        }
    }

    public BasicColumn(String headerName, int width) {
        this.headerName = headerName;
        this.width = width;
    }

    public void setAlignment(TEXT_ALIGNMENT alignment) {
        this.alignment = alignment;
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
