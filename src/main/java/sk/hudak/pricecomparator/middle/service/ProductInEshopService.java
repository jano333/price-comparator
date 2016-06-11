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

    PageList<ResponseDto> findProductInEshopsForProductIds(ProductIdsFindDto productIdsFindDto);

    /**
     * Priradenie produktu do eshopu.
     *
     * @param dto
     * @return
     */
    Long createProductInEshop(ProductInEshopCreateDto dto);

    /**
     * @param updateDto
     */
    void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto);

    /**
     * @param productId id produktu, povinne
     * @param eshopId   id eshopu, povinne
     * @return null ak sa nenajde, inak produkt v eshope.
     */
    ProductInEshopDto getProductInEshop(Long productId, Long eshopId);

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

    // TODO vsetko nizsie prejst

    /**
     * @return
     */
    List<ProductInEshopListDto> findAllProductInEshop();

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
     * @param stepOneRequestDto
     * @return
     * @throws PriceComparatorBusinesException
     */
    StepOneResponseDto analyzeProductUrl(StepOneRequestDto stepOneRequestDto) throws PriceComparatorBusinesException;

    /**
     * @param stepTwoRequestDto
     * @throws PriceComparatorBusinesException
     */
    void createNewProdutAndAddToEshop(StepTwoRequestDto stepTwoRequestDto) throws PriceComparatorBusinesException;
}
