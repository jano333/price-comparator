package sk.hudak.pricecomparator.middle.to;

import java.io.Serializable;

/**
 * Created by jan on 17. 6. 2016.
 */
//TODO premenovat, nemoze sa to volat EshopWithou, ked je to ja pre eshopy, ktore ho maju..
public class EshopWithoutProductListDto implements Serializable {

    // vyplnene vzdy
    private EshopIdNameDto eshopIdNameDto;

    // attr vyplnene iba, ak dany produkt sa nachadaza v danom eshope:
    private Long productInEshopId;
    private String productInEshopPage;

    public EshopIdNameDto getEshopIdNameDto() {
        return eshopIdNameDto;
    }

    public void setEshopIdNameDto(EshopIdNameDto eshopIdNameDto) {
        this.eshopIdNameDto = eshopIdNameDto;
    }

    public Long getProductInEshopId() {
        return productInEshopId;
    }

    public void setProductInEshopId(Long productInEshopId) {
        this.productInEshopId = productInEshopId;
    }

    public String getProductInEshopPage() {
        return productInEshopPage;
    }

    public void setProductInEshopPage(String productInEshopPage) {
        this.productInEshopPage = productInEshopPage;
    }
}
