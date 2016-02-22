package sk.hudak.pricecomparator.middle.service;

import sk.hudak.pricecomparator.middle.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.to.ProductDto;
import sk.hudak.pricecomparator.middle.to.ProductListDto;
import sk.hudak.pricecomparator.middle.to.ProductUpdateDto;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface ProductService {

    Long createProduct(ProductCreateDto createDto);

    void updateProduct(ProductUpdateDto updateDto);

    ProductDto getProduct(Long productId);

    List<ProductListDto> getAllProduct();

}
