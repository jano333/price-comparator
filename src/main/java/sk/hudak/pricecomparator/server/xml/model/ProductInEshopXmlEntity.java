package sk.hudak.pricecomparator.server.xml.model;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductInEshopXmlEntity {

    // generuje sa pri vytvoreni
    private Long id;
    // tieto sa zadavani pri vytvoreni
    private Long productId;
    private Long eshopId;
    private String eshopProductPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getEshopId() {
        return eshopId;
    }

    public void setEshopId(Long eshopId) {
        this.eshopId = eshopId;
    }

    public String getEshopProductPage() {
        return eshopProductPage;
    }

    public void setEshopProductPage(String eshopProductPage) {
        this.eshopProductPage = eshopProductPage;
    }
}
