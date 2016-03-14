package sk.hudak.jef;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jan on 7. 3. 2016.
 */
public class PageList<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String AT_ENTRIES = "entries";
    public static final String AT_CURRENT_PAGE = "currentPage";
    public static final String AT_ALL_PAGE_COUNT = "allPageCount";

    private int currentPage;
    private int allPageCount;
    private List<T> entries;

    public PageList() {
    }

    public PageList(List<T> entries, int currentPage, int allPageCount) {
        this.entries = entries;
        this.currentPage = currentPage;
        this.allPageCount = allPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getAllPageCount() {
        return allPageCount;
    }

    public void setAllPageCount(int allPageCount) {
        this.allPageCount = allPageCount;
    }

    public List<T> getEntries() {
        return entries;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }
}
