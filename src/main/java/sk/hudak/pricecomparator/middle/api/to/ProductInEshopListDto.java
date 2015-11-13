package sk.hudak.pricecomparator.middle.api.to;

import java.io.Serializable;

/**
 * Created by jan on 18. 10. 2015.
 */
public class ProductInEshopListDto implements Serializable {

    private Long id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductInEshopListDto listDto = (ProductInEshopListDto) o;

        if (id != null ? !id.equals(listDto.id) : listDto.id != null) return false;
        if (productId != null ? !productId.equals(listDto.productId) : listDto.productId != null) return false;
        if (eshopId != null ? !eshopId.equals(listDto.eshopId) : listDto.eshopId != null) return false;
        return !(eshopProductPage != null ? !eshopProductPage.equals(listDto.eshopProductPage) : listDto.eshopProductPage != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (eshopId != null ? eshopId.hashCode() : 0);
        result = 31 * result + (eshopProductPage != null ? eshopProductPage.hashCode() : 0);
        return result;
    }
}
