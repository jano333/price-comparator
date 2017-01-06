package sk.hudak.jef.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Toto sa zatial nepoziva, ale bude. Bude to nahrada za PageList
 * <p>
 * Created by jan on 13. 12. 2014.
 */
public class PageData<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String AT_ALL_RECORDS_COUNT = "allRecordsCount";
    public static final String AT_PAGE_COUNT = "pageCount";


    private int allRecordsCount;
    private Paging paging;
    private List<E> result;

    // dopocitavany
    private int pageCount = -1;

    public PageData() {
        this.allRecordsCount = -1;
        this.paging = null;
        this.result = new ArrayList<E>();
        calculatePageCount();
    }

    public PageData(int allRecordsCount, Paging paging, List<E> result) {
        this.allRecordsCount = allRecordsCount;
        this.paging = paging;
        if (result == null) {
            this.result = new ArrayList<E>();
        } else {
            this.result = result;
        }
        calculatePageCount();
    }

    private void calculatePageCount() {
        if (allRecordsCount < 0) {
            pageCount = -1;
            return;
        }
        if (paging == null || paging.getPageSize() < 1) {
            pageCount = -1;
            return;
        }

        int result = allRecordsCount / paging.getPageSize();

        if ((allRecordsCount % paging.getPageSize()) != 0) {
            result++;
        }
        pageCount = result;
    }

    public int getAllRecordsCount() {
        return allRecordsCount;
    }

    public void setAllRecordsCount(int allRecordsCount) {
        this.allRecordsCount = allRecordsCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
        calculatePageCount();
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        if (result != null) {
            this.result = result;
        } else {
            this.result = new ArrayList<>();
        }
    }

    public int size() {
        if (result != null) {
            return result.size();
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return String.format("PageData [allRecordsCount=%s, paging=%s, result=%s]", allRecordsCount, paging, result);
    }

}

