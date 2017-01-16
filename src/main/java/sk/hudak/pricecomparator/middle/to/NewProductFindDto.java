package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.server.to.NewProductStatus;

/**
 * Created by jan on 31. 12. 2016.
 */
public class NewProductFindDto extends FindDtoNg {

    public static final String AT_PRODUCT_NAME = "productName";
    public static final String AT_STATUS = "status";

    private String productName;
    private NewProductStatus status;

    public NewProductStatus getStatus() {
        return status;
    }

    public void setStatus(NewProductStatus status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
