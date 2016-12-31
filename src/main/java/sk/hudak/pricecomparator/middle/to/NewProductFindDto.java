package sk.hudak.pricecomparator.middle.to;

/**
 * Created by jan on 31. 12. 2016.
 */
public class NewProductFindDto extends FindDtoNg {

    public static final String AT_PRODUCT_NAME = "productName";

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
