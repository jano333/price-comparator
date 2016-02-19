package sk.hudak.pricecomparator.middle.to;

/**
 * Created by jan on 19. 2. 2016.
 */
public class ProductInEshopFindDto extends FindDto {

    private Long eshopId;
    private String productName;
    private boolean onlyInAction;

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
}
