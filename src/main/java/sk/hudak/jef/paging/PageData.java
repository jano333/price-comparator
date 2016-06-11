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

    private int allRecordsCount;
    private Paging paging;
    private List<E> result;

    public PageData() {
        this.allRecordsCount = -1;
        this.paging = null;
        this.result = new ArrayList<E>();
    }

    public PageData(int allRecordsCount, Paging paging, List<E> result) {
        this.allRecordsCount = allRecordsCount;
        this.paging = paging;
        if (result == null) {
            this.result = new ArrayList<E>();
        } else {
            this.result = result;
        }
    }

    public int getAllRecordsCount() {
        return allRecordsCount;
    }

    public void setAllRecordsCount(int allRecordsCount) {
        this.allRecordsCount = allRecordsCount;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
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

