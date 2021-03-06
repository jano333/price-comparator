package sk.hudak.pricecomparator.middle.to.product;

import sk.hudak.pricecomparator.middle.to.FindDto;

/**
 * Created by jan on 17. 3. 2016.
 */
public class ProductFindDto extends FindDto {

    public static final String AT_PRODUCT_ID = "productId";
    public static final String AT_NAME = "name";

    private Long productId;
    private String name;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductFindDto{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                '}'+super.toString();
    }
}
