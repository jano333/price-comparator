package sk.hudak.pricecomparator.middle.service;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.to.*;

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

    /**
     * Vyhlada vsetky eshop-y, ktore obsahuju produkt <code>productId</code>.
     *
     * @param productId id produktu
     * @return zoznam eshopov, ktore obsahuju produkt <code>productId</code>
     */
    List<EshopListDto> findEshopsWithProduct(Long productId);

    /**
     * Vyhlada vsetky eshop-y, ktore NEobsahuju produkt <code>productId</code>.
     *
     * @param productId id produktu
     * @return zoznam eshopov, ktore neobsahuju produkt <code>productId</code>
     */
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

    /**
     * Pre produkt <code>productId</code>  vyhlada ceny a dalsie informacie vo vsetkych ehopoch,
     * ktore poskytuju dany produkt. <br/>
     * Vysledok je sortovany od najnizsej ceny za jednotku po najvyssiu.
     *
     * @param productId id produktu
     * @return zoznam informacii o produkte v jednotlivych eshopoch
     */
    List<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForProduct(Long productId);


}
