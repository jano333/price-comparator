package sk.hudak.pricecomparator.middle.to.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 3. 11. 2015.
 */
public class GroupOfProductCreateDto implements Serializable {

    public static final String AT_NAME = "name";

    //nazov
    private String name;
    // zoznam produktov v danej grupe
    private List<Long> productIds = new ArrayList<>();

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
