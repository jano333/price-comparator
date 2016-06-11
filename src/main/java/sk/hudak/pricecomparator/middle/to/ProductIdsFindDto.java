package sk.hudak.pricecomparator.middle.to;

import java.util.List;

/**
 * Created by jan on 11. 6. 2016.
 */
public class ProductIdsFindDto extends FindDto {

    private List<Long> productIds;

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
