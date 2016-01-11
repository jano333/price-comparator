package sk.hudak.pricecomparator.middle.api.service;

import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.to.*;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface ProductInEshopService {

    /**
     * Priradenie existujuceho produktu do eshopu.
     *
     * @param dto
     * @return
     */
    Long createProductInEshop(ProductInEshopCreateDto dto);

    List<ProductInEshopListDto> findAllProductInEshop();

    /**
     * @param productId id produktu, povinne
     * @param eshopId   id eshop-u, povinne
     * @return null ak sa nenajde, inak produkt v eshope.
     */
    ProductInEshopDto getProductInEshop(Long productId, Long eshopId);


    List<EshopListDto> findEshopsWithProduct(Long productId);

    List<EshopListDto> findEshopsWithoutProduct(Long productId);

    List<ProductInEshopListDto> findProductsInEshopByProductId(Long productId);

    List<ProductInEshopDto> findProductsInEshopForDownloaderByProductId(Long productId);


    /**
     * @param eshopId id eshop-u
     * @return zoznam produktov v danom eshope
     */
    List<ProductInEshopCustomListDto> findProductsInEshop(Long eshopId);

    ProductInEshopDto findProductForPriceUpdate(EshopType eshopType);

    void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto);
}
