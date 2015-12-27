package sk.hudak.jef.middle.paging;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType
public class Paging implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Index of first record of the bean.
     */
    private int offset = -1;

    /**
     * Count of records per bean.
     */
    private int pageSize = 1;

    /**
     * Flag which indicates if client wants information of all records count.
     */
    private boolean doCount = true;

    /**
     * This constructor could not be deprecated because we need to pass serialize and deserialize paging
     * (e.g. when using webservices). This could not be done without this. constructor.
     */
    public Paging() {
    }

    /**
     * Constructor.
     *
     * @param offset   index of first record at the bean
     * @param pageSize count of records per bean
     */
    public Paging(int offset, int pageSize) {
        this.offset = offset;
        this.pageSize = pageSize;
    }

    /**
     * Constructor.
     *
     * @param offset   index of first record at the bean
     * @param pageSize count of records per bean
     * @param doCount  information of all records count
     */
    public Paging(int offset, int pageSize, boolean doCount) {
        this.offset = offset;
        this.pageSize = pageSize;
        this.doCount = doCount;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isDoCount() {
        return doCount;
    }

    public void setDoCount(boolean doCount) {
        this.doCount = doCount;
    }

    @Override
    public String toString() {
        return String.format("Paging [offset=%s, pageSize=%s, doCount=%s]", offset, pageSize, doCount);
    }

}

