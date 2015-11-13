package sk.hudak.pricecomparator.server.xml.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 3. 11. 2015.
 */
public class GroupOfProductXmlEntity {

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
