package sk.hudak.jef;

import java.io.Serializable;

/**
 * Created by jan on 7. 3. 2016.
 */
public class BasicFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String AT_OFFSET = "offset";
    public static final String AT_COUNT = "count";

    public static final int OFFSET_OF_FIRST_ENTRIES = 0;
    public static final int OFFSET_OF_LAST_ENTRIES = -1;
    public static final int DEFAULT_COUNT = 10;

    public static final int MAX_COUNT = 1000;

    private int offset = OFFSET_OF_FIRST_ENTRIES;
    private int count = DEFAULT_COUNT;

    public BasicFilter() {
        this(OFFSET_OF_FIRST_ENTRIES, DEFAULT_COUNT);
    }

    public BasicFilter(int offset, int count) {
        this.offset = offset;
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    /**
     * First entry index of returned list according to order.
     *
     * @param offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    /**
     * Count of entries starting by offset entry index.
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

}