package sk.hudak.jef;

/**
 * Created by jan on 10. 3. 2016.
 */
public class ServerPaging {

    private static final int NOT_YET_COMPUTED = -1;

    // count of all pages
    private int allPageCount = NOT_YET_COMPUTED;
    // number of current page(first page has number 1)
    private int currentPage = NOT_YET_COMPUTED;

    private int offset;
    private int count;
    private int allCount;

    public ServerPaging(int offset, int count, int allCount) {
        this.offset = offset;
        this.count = count;
        this.allCount = allCount;
    }

    //TODO odmazat v povodnom nie je !!
    public int getOffset() {
        return offset;
    }

    public int getCurrentPage() {
        if (this.currentPage == NOT_YET_COMPUTED) {
            computeCurrentPage();
        }
        return this.currentPage;
    }

    public int getAllPage() {
        if (this.allPageCount == NOT_YET_COMPUTED) {
            computeAllPageCount();
        }
        return allPageCount;
    }

    private void computeCurrentPage() {
        int tmp = (int) (offset / count);
        this.currentPage = tmp + 1;
    }

    private void computeAllPageCount() {
        int tmp = (int) (allCount / count);
        int part = allCount % count;
        if (part != 0) {
            tmp = tmp + 1;
        }
        this.allPageCount = tmp;
    }
}
