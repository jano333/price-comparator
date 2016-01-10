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

    List<ProductInEshopListDto> getAllProductInEshop();

    /**
     * @param productId id produktu, povinne
     * @param eshopId   id eshop-u, povinne
     * @return null ak sa nenajde, inak produkt v eshope.
     */
    ProductInEshopDto getProductInEshop(Long productId, Long eshopId);


    List<EshopListDto> getEshopsWithProduct(Long productId);

    List<EshopListDto> getEshopsWithoutProduct(Long productId);

    List<ProductInEshopListDto> getProductsInEshopByProductId(Long productId);

    List<ProductInEshopDto> getProductsInEshopForDownloaderByProductId(Long productId);


    /**
     * @param eshopId id eshop-u
     * @return zoznam produktov v danom eshope
     */
    List<ProductInEshopCustomListDto> getProductsInEshop(Long eshopId);

    /**
     * ZOznam produktov, kde cena je null alebo je starsia ako 24 hodin
     *
     * @param eshopId
     */
    @Deprecated
    List<ProductInEshopDto> findProductInEshopForPriceUpdate(EshopType eshopId);

    ProductInEshopDto getProductForPriceUpdate(EshopType eshopType);
}
