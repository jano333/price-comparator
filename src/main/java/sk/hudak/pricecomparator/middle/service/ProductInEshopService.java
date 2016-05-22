package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.*;
import sk.hudak.pricecomparator.middle.to.internal.StepOneRequestDto;
import sk.hudak.pricecomparator.middle.to.internal.StepOneResponseDto;
import sk.hudak.pricecomparator.middle.to.internal.StepTwoRequestDto;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface ProductInEshopService {

    // OK
    PageList<ProductInEshopPriceInfoListDto> findProductsInEshopPriceInfo(ProductInEshopFindDto findDto);

    PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForProduct(ProductFindDto findDto);

    // TODO vsetko nizsie prejst

    /**
     * Priradenie existujuceho produktu do eshopu.
     *
     * @param dto
     * @return
     */
    Long createProductInEshop(ProductInEshopCreateDto dto);

    void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto);

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


    /**
     * Pre eshop eshopId vyhlada vsetky produkty v danom eshope.
     *
     * @param eshopId id eshop-u
     * @return
     */
    List<ProductInEshopPriceInfoListDto> findProductInEshopPriceInfoForEshop(Long eshopId);


    /**
     * @param feedo
     * @return null, ak ziaden taky neexistuje
     */
    ProductInEshopForPictureDownloadInfoDto findUrlOfProductsInEshopWithoutPicture(EshopType feedo);

    boolean existProductWithGivenUrl(String productUrl);

    StepOneResponseDto analyzeProductUrl(StepOneRequestDto stepOneRequestDto) throws PriceComparatorBusinesException;

    void createNewProdutAndAddToEshop(StepTwoRequestDto stepTwoRequestDto) throws PriceComparatorBusinesException;


    //TODO vsetko nizsie remove

    @Deprecated
    List<ProductInEshopPriceInfoListDto> old_findProductsInEshopPriceInfo(ProductInEshopFindDto findDto);

//    /**
//     * Pre produkt <code>productId</code>  vyhlada ceny a dalsie informacie vo vsetkych ehopoch,
//     * ktore poskytuju dany produkt. <br/>
//     * Vysledok je sortovany od najnizsej ceny za jednotku po najvyssiu.
//     *
//     * @param productId id produktu
//     * @return zoznam informacii o produkte v jednotlivych eshopoch
//     * TODO pouzivat paging
//     */
//    @Deprecated
//    List<ProductInEshopPriceResultListDto> old_findPriceInfoInEshopsForProduct(Long productId);


}
