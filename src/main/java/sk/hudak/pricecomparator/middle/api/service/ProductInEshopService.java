package sk.hudak.pricecomparator.middle.api.service;

import sk.hudak.pricecomparator.middle.api.to.*;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface ProductInEshopService {

    Long createProductInEshop(ProductInEshopCreateDto dto);

    List<ProductInEshopListDto> getAllProductInEshop();

    List<EshopListDto> getEshopsWithProduct(Long productId);

    List<EshopListDto> getEshopsWithoutProduct(Long productId);


    List<ProductInEshopListDto> getProductsInEshopByProductId(Long productId);


    List<ProductInEshopDto> getProductsInEshopForDownloaderByProductId(Long productId);


    /**
     * @param eshopId id eshop-u
     * @return zoznam produktov v danom eshope
     */
    List<ProductInEshopCustomListDto> getProductsInEshop(Long eshopId);
}
