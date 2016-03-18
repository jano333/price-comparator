package sk.hudak.pricecomparator.middle.to;

import java.util.List;

/**
 * Created by jan on 19. 2. 2016.
 */
public class ProductInEshopFindDto extends FindDto {

    public static final String AT_PRODUCT_NAME = "productName";
    public static final String AT_ONLY_IN_ACTION = "onlyInAction";

    private Long eshopId;
    private String productName;
    private boolean onlyInAction;

    private List<Long> productId;

    public Long getEshopId() {
        return eshopId;
    }

    public void setEshopId(Long eshopId) {
        this.eshopId = eshopId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isOnlyInAction() {
        return onlyInAction;
    }

    public void setOnlyInAction(boolean onlyInAction) {
        this.onlyInAction = onlyInAction;
    }

    public List<Long> getProductId() {
        return productId;
    }

    public void setProductId(List<Long> productId) {
        this.productId = productId;
    }
}
