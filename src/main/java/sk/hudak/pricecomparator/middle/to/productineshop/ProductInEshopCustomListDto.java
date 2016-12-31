package sk.hudak.pricecomparator.middle.to.productineshop;

import sk.hudak.pricecomparator.middle.to.product.ProductListDto;

import java.io.Serializable;

/**
 * Created by jan on 20. 12. 2015.
 */
public class ProductInEshopCustomListDto implements Serializable {
    private Long id;
    private ProductListDto productListDto = new ProductListDto();
    private String eshopProductPage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductListDto getProductListDto() {
        return productListDto;
    }

    public void setProductListDto(ProductListDto productListDto) {
        this.productListDto = productListDto;
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

        ProductInEshopCustomListDto that = (ProductInEshopCustomListDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productListDto != null ? !productListDto.equals(that.productListDto) : that.productListDto != null)
            return false;
        return !(eshopProductPage != null ? !eshopProductPage.equals(that.eshopProductPage) : that.eshopProductPage != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productListDto != null ? productListDto.hashCode() : 0);
        result = 31 * result + (eshopProductPage != null ? eshopProductPage.hashCode() : 0);
        return result;
    }
}
