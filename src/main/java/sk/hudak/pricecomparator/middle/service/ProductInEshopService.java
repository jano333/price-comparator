package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.*;
import sk.hudak.pricecomparator.middle.to.internal.ProductByUrlAnalyzatorResponseDto;
import sk.hudak.pricecomparator.middle.to.internal.ProductByUrlRequestDto;
import sk.hudak.pricecomparator.middle.to.internal.StepTwoRequestDto;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface ProductInEshopService {

    /**
     * Priradenie existujuceho produktu do existujuceho eshopu.
     *
     * @param createDto data pre vytvorenie
     * @return db id novo vytvoreneho produktu v eshope
     * @throws PriceComparatorBusinesException bussines chyba v pripade zlyhania
     */
    Long createProductInEshop(ProductInEshopCreateDto createDto) throws PriceComparatorBusinesException;

    /**
     * Uprava existujucejho produktu v eshope.
     *
     * @param updateDto data pre update
     * @throws PriceComparatorBusinesException bussines chyba v pripade zlyhania
     */
    void updateProductInEshop(ProductInEshopUpdateDto updateDto) throws PriceComparatorBusinesException;

    /**
     * Nacitanie informacii o produkte v eshope.
     *
     * @param productInEshopId db id produktu v eshope
     * @return info o produkte v eshope
     * @throws PriceComparatorBusinesException bussines chyba v pripade zlyhania
     */
    ProductInEshopDto getProductInEshop(Long productInEshopId) throws PriceComparatorBusinesException;

    /**
     * Nacitanie informacii o produkte v eshope.
     *
     * @param productId id produktu, povinne
     * @param eshopId   id eshopu, povinne
     * @return null ak sa nenajde, inak produkt v eshope.
     */
    ProductInEshopDto getProductInEshop(Long productId, Long eshopId);

    /**
     * Odmaze dany produkt z eshopu, pricom samotny produkt ani eshop nerusi.
     *
     * @param productInEshopId db id produtktu v eshope
     */
    void deleteProductInEshop(Long productInEshopId) throws PriceComparatorBusinesException;


    /**
     * Vyhlada vsetky eshop-y, ktore obsahuju produkt <code>productId</code>.
     * <p>
     * TODO prerobit na page list result
     *
     * @param productId id produktu
     * @return zoznam eshopov, ktore obsahuju produkt <code>productId</code>
     */
    List<EshopListDto> findEshopsWithProduct(Long productId);

    /**
     * Vyhlada vsetky eshop-y, ktore NEobsahuju produkt <code>productId</code>.
     * <p>
     * TODO prerobit na page list result
     *
     * @param productId id produktu
     * @return zoznam eshopov, ktore neobsahuju produkt <code>productId</code>
     */
    List<EshopListDto> findEshopsWithoutProduct(Long productId);

    // TODO nizsie prejst co sa pouziva...

    PageList<ResponseDto> findProductInEshopsForProductIds(ProductIdsFindDto productIdsFindDto);

    /**
     * Aktualizacia ceny a akcie produktu v eshope.
     *
     * @param updatePriceDto
     */
    void updateInfoOfProductInEshop(ProductInEshopInfoUpdateDto updatePriceDto);


    /**
     * @param findDto
     * @return
     */
    PageList<ProductInEshopPriceInfoListDto> findProductsInEshopPriceInfo(ProductInEshopFindDto findDto);

    /**
     * @param findDto
     * @return
     */
    PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForProduct(ProductFindDto findDto);

    ProductInEshopPriceResultListDto getProductInEshopPriceResultListDto(Long productInEshopId);

    /**
     * @return
     */
    List<ProductInEshopListDto> findAllProductInEshop();


    /**
     * @param productId
     * @return
     */
    List<ProductInEshopListDto> findProductsInEshopByProductId(Long productId);

    /**
     * @param productId
     * @return
     */
    List<ProductInEshopDto> findProductsInEshopForDownloaderByProductId(Long productId);

    /**
     * @param eshopId id eshop-u
     * @return zoznam produktov v danom eshope
     */
    List<ProductInEshopCustomListDto> findProductsInEshop(Long eshopId);


    /**
     * @param eshopType
     * @return
     */
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

    /**
     * @param productUrl
     * @return
     */
    boolean existProductWithGivenUrl(String productUrl);

    /**
     * //TODO tato metoda by mali ist do ineho servisu
     *
     * @param productByUrlRequestDto
     * @return
     * @throws PriceComparatorBusinesException
     */
    ProductByUrlAnalyzatorResponseDto analyzeProductUrl(ProductByUrlRequestDto productByUrlRequestDto) throws PriceComparatorBusinesException;

    /**
     * @param stepTwoRequestDto
     * @throws PriceComparatorBusinesException
     */
    ProductInEshopDto createNewProductAndAddToEshop(StepTwoRequestDto stepTwoRequestDto) throws PriceComparatorBusinesException;

    PageList<EshopWithoutProductListDto> findEshopWithoutProduct(ProductFindDto filter);

    ProductInEshopPriceInfoListDto getProductInEhopPriceInfoListDto(Long productInEshopId);
}
