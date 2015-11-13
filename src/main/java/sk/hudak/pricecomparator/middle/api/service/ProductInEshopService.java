package sk.hudak.pricecomparator.middle.api.service;

import sk.hudak.pricecomparator.middle.api.to.*;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface ProductInEshopService {

    Long createProductInEshop(ProductInEshopCreateDto dto);

    List<ProductInEshopListDto> getAllProductInEshop();

    List<ProductInEshopListDto> getProductsInEshopByProductId(Long productId);

    List<EshopListDto> getEshopsWithoutProduct(Long productId);

    List<EshopListDto> getEshopsWithProduct(Long productId);

    List<ProductInEshopDto> getProductsInEshopForDownloaderByProductId(Long productId);


}