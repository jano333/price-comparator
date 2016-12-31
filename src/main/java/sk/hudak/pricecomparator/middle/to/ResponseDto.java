package sk.hudak.pricecomparator.middle.to;

import sk.hudak.pricecomparator.middle.to.productineshop.ProductInEshopInfo2;

import java.io.Serializable;
import java.util.List;

/**
 * Predstavuje jeden riadok tabulky
 * <p>
 * Created by jan on 11. 6. 2016.
 */
public class ResponseDto implements Serializable {

    // 1 stlpec
    private Long eshopId;
    private String eshopName;

    // dalsie stlpce produktov v eshope
    private List<ProductInEshopInfo2> productInEshopInfo2;

    public String getEshopName() {
        return eshopName;
    }

    public void setEshopName(String eshopName) {
        this.eshopName = eshopName;
    }

    public Long getEshopId() {
        return eshopId;
    }

    public void setEshopId(Long eshopId) {
        this.eshopId = eshopId;
    }

    public List<ProductInEshopInfo2> getProductInEshopInfo2() {
        return productInEshopInfo2;
    }

    public void setProductInEshopInfo2(List<ProductInEshopInfo2> productInEshopInfo2) {
        this.productInEshopInfo2 = productInEshopInfo2;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "eshopId=" + eshopId +
                ", eshopName='" + eshopName + '\'' +
                ", productInEshopInfo2=" + productInEshopInfo2 +
                '}';
    }
}
