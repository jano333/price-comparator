package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.*;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface ProductService {

    /**
     * @param createDto
     * @return
     */
    Long createProduct(ProductCreateDto createDto) throws PriceComparatorBusinesException;

    /**
     * @param updateDto
     */
    void updateProduct(ProductUpdateDto updateDto)throws PriceComparatorBusinesException;

    /**
     * @param productId
     * @return
     */
    ProductDto getProductById(Long productId);

    /**
     * @param productId
     * @return
     */
    ProductIdNameDto getProductIdNameById(Long productId);

    /**
     * for lazy loading in table...
     *
     * @param productId
     * @return
     */
    ProductListDto getProductListById(Long productId);

    /**
     * @param filter
     * @return
     */
    PageList<ProductListDto> findProducts(ProductFindDto filter);

    /**
     * @return
     */
    List<ProductIdNameDto> findAllProductForSelection();

    /**
     * @return
     */
    @Deprecated
    List<ProductListDto> findAllProduct();
}
