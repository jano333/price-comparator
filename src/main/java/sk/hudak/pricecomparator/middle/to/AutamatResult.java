package sk.hudak.pricecomparator.middle.to;

import java.io.Serializable;

/**
 * Created by jan on 6. 2. 2016.
 */
public class AutamatResult implements Serializable {

    private ProductCreateDto productCreateDto;
    private ProductInEshopCreateDto productInEshopCreateDto;

    public AutamatResult(ProductCreateDto productCreateDto, ProductInEshopCreateDto productInEshopCreateDto) {
        this.productCreateDto = productCreateDto;
        this.productInEshopCreateDto = productInEshopCreateDto;
    }

    public ProductCreateDto getProductCreateDto() {
        return productCreateDto;
    }

    public ProductInEshopCreateDto getProductInEshopCreateDto() {
        return productInEshopCreateDto;
    }
}
