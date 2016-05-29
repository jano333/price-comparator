package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.to.*;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface ProductService {

    List<ProductIdNameDto> findAllProductForSelection();

    PageList<ProductListDto> findProducts(ProductFindDto filter);

    /**
     * for lazy loading in table...
     *
     * @param productId
     * @return
     */
    ProductListDto getProductListDtoById(Long productId);

    //-------

    Long createProduct(ProductCreateDto createDto);

    void updateProduct(ProductUpdateDto updateDto);

    ProductDto getProduct(Long productId);

    List<ProductListDto> getAllProduct();

    ProductIdNameDto getProductIdNameDto(Long productId);
}
