package sk.hudak.pricecomparator.middle.to;

import sk.hudak.jef.BasicFilter;
import sk.hudak.jef.paging.OrderBy;
import sk.hudak.jef.paging.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jan on 27. 12. 2015.
 */
//TODO remove extend BasicFilter
public class FindDto extends BasicFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int MAX_RESULTS = 100;

    private Paging paging;
    private List<OrderBy> orders;

    public FindDto() {
    }

    public Paging getPaging() {
        //kontrola, nie je mozne mat viac ako 100 vysledkov
        if (paging != null && paging.getPageSize() > MAX_RESULTS) {
            paging.setPageSize(MAX_RESULTS);
        }
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<OrderBy> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderBy> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "FindDto [paging=" + paging + ", orders=" + orders + "]";
    }
}
