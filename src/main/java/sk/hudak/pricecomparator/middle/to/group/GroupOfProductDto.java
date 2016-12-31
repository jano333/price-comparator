package sk.hudak.pricecomparator.middle.to.group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 7. 11. 2015.
 */
public class GroupOfProductDto {

    private Long id;
    //nazov
    private String name;
    // zoznam produktov v danej grupe
    private List<Long> productIds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
