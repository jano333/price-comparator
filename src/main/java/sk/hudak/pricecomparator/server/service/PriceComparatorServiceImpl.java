package sk.hudak.pricecomparator.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.service.*;
import sk.hudak.pricecomparator.middle.to.*;
import sk.hudak.pricecomparator.middle.to.internal.StepOneRequestDto;
import sk.hudak.pricecomparator.middle.to.internal.StepOneResponseDto;
import sk.hudak.pricecomparator.middle.to.internal.StepTwoRequestDto;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

/**
 * Created by jan on 29. 11. 2015.
 */
@Named(value = PriceComparatorService.SERVICE_NAME)
public class PriceComparatorServiceImpl implements PriceComparatorService {

    private Logger logger = LoggerFactory.getLogger(PriceComparatorServiceImpl.class);

    @Inject
    private EshopService eshopService;

    @Inject
    private ProductService productService;

    @Inject
    private ProductInEshopService productInEshopService;

    @Inject
    private GroupOfProductService groupOfProductService;

    // --------- ESHOP ----------

    @Override
    @Transactional
    public Long createEshop(EshopCreateDto createDto) {
        logger.debug(">> createEshop");
        Long result = eshopService.createEshop(createDto);
        logger.debug("<< createEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<EshopListDto> findEshops(EshopFindDto filter) {
        logger.debug(">> findEshops");
        PageList<EshopListDto> result = eshopService.findEshops(filter);
        logger.debug("<< findEshops");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopHomePageInfoDto> findAllHomePages() {
        logger.debug(">> findAllHomePages");
        List<EshopHomePageInfoDto> result = eshopService.findAllHomePages();
        logger.debug("<< findAllHomePages");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public EshopDto getEshopById(Long eshopId) {
        logger.debug(">> getEshopById");
        EshopDto result = eshopService.getEshopById(eshopId);
        logger.debug("<< getEshopById");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> findAllEshops() {
        logger.debug(">> findAllEshops");
        List<EshopListDto> result = eshopService.findAllEshops();
        logger.debug(">> findAllEshops");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public EshopListDto getEshopListDtoById(Long eshopId) {
        logger.debug(">> getEshopListDtoById");
        EshopListDto result = eshopService.getEshopListDtoById(eshopId);
        logger.debug("<< getEshopListDtoById");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public EshopIdNameDto getEshopIdNameDto(Long eshopId) {
        return eshopService.getEshopIdNameDto(eshopId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopIdNameDto> findAllEshopsForSelection() {
        logger.debug(">> findAllEshopsForSelection");
        List<EshopIdNameDto> result = eshopService.findAllEshopsForSelection();
        logger.debug("<< findAllEshopsForSelection");
        return result;
    }

    // -------------- PRODUCT -----------

    @Override
    @Transactional
    public Long createProduct(ProductCreateDto createDto) {
        logger.debug(">> createProduct");
        Long result = productService.createProduct(createDto);
        logger.debug("<< createProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductIdNameDto> findAllProductForSelection() {
        logger.debug(">> findAllProductForSelection");
        List<ProductIdNameDto> result = productService.findAllProductForSelection();
        logger.debug("<< findAllProductForSelection");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<ProductListDto> findProducts(ProductFindDto filter) {
        logger.debug(">> findProducts");
        PageList<ProductListDto> result = productService.findProducts(filter);
        logger.debug("<< findProducts");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductListDto getProductListDtoById(Long productId) {
        logger.debug(">> getProductListDtoById");
        ProductListDto result = productService.getProductListDtoById(productId);
        logger.debug("<< getProductListDtoById");
        return result;
    }

    @Override
    @Transactional
    public void updateProduct(ProductUpdateDto updateDto) {
        logger.debug(">> updateProduct");
        productService.updateProduct(updateDto);
        logger.debug("<< updateProduct");
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProduct(Long productId) {
        logger.debug(">> getProduct");
        ProductDto result = productService.getProduct(productId);
        logger.debug("<< getProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListDto> getAllProduct() {
        logger.debug(">> getAllProduct");
        List<ProductListDto> result = productService.getAllProduct();
        logger.debug("<< getAllProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductIdNameDto getProductIdNameDto(Long productId) {
        return productService.getProductIdNameDto(productId);
    }


    // --------- CATEGORY ----------

    @Override
    @Transactional
    public Long createCategory(CategoryCreateDto dto) {
        //TODO impl
        return null;
    }


    // ----------- PRODUCT_IN_ESHOP ------------
    @Override
    @Transactional
    public Long createProductInEshop(ProductInEshopCreateDto dto) {
        logger.debug(">> createProductInEshop");
        Long result = productInEshopService.createProductInEshop(dto);
        logger.debug("<< createProductInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopListDto> findAllProductInEshop() {
        logger.debug(">> findAllProductInEshop");
        List<ProductInEshopListDto> result = productInEshopService.findAllProductInEshop();
        logger.debug("<< findAllProductInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInEshopDto getProductInEshop(Long productId, Long eshopId) {
        logger.debug(">> findProductInEshop");
        ProductInEshopDto result = productInEshopService.getProductInEshop(productId, eshopId);
        logger.debug("<< findProductInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopListDto> findProductsInEshopByProductId(Long productId) {
        logger.debug(">> findProductsInEshopByProductId");
        List<ProductInEshopListDto> result = productInEshopService.findProductsInEshopByProductId(productId);
        logger.debug("<< findProductsInEshopByProductId");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> findEshopsWithProduct(Long productId) {
        logger.debug(">> findEshopsWithProduct");
        List<EshopListDto> result = productInEshopService.findEshopsWithProduct(productId);
        logger.debug("<< findEshopsWithProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> findEshopsWithoutProduct(Long productId) {
        logger.debug(">> findEshopsWithoutProduct");
        List<EshopListDto> result = productInEshopService.findEshopsWithoutProduct(productId);
        logger.debug("<< findEshopsWithoutProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopDto> findProductsInEshopForDownloaderByProductId(Long productId) {
        logger.debug(">> findProductsInEshopForDownloaderByProductId");
        List<ProductInEshopDto> result = productInEshopService.findProductsInEshopForDownloaderByProductId(productId);
        logger.debug("<< findProductsInEshopForDownloaderByProductId");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopCustomListDto> findProductsInEshop(Long eshopId) {
        logger.debug(">> findProductsInEshop");
        List<ProductInEshopCustomListDto> result = productInEshopService.findProductsInEshop(eshopId);
        logger.debug("<< findProductsInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInEshopDto findProductForPriceUpdate(EshopType eshopType) {
        logger.debug(">> findProductForPriceUpdate");
        ProductInEshopDto result = productInEshopService.findProductForPriceUpdate(eshopType);
        logger.debug("<< findProductForPriceUpdate");
        return result;
    }

    @Override
    @Transactional
    public void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto) {
        logger.debug(">> updateProductInEshopPrice");
        productInEshopService.updateProductInEshopPrice(updateDto);
        logger.debug("<< updateProductInEshopPrice");
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopPriceInfoListDto> findProductInEshopPriceInfoForEshop(Long eshopId) {
        logger.debug(">> findProductInEshopPriceInfoForEshop");
        List<ProductInEshopPriceInfoListDto> result = productInEshopService.findProductInEshopPriceInfoForEshop(eshopId);
        logger.debug("<< findProductInEshopPriceInfoForEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopPriceInfoListDto> old_findProductsInEshopPriceInfo(ProductInEshopFindDto findDto) {
        logger.debug(">> findProductsInEshopPriceInfo");
        List<ProductInEshopPriceInfoListDto> result = productInEshopService.old_findProductsInEshopPriceInfo(findDto);
        logger.debug("<< findProductsInEshopPriceInfo");
        return result;
    }


    @Override
    @Transactional(readOnly = true)
    public PageList<ProductInEshopPriceInfoListDto> findProductsInEshopPriceInfo(ProductInEshopFindDto findDto) {
        logger.debug(">> findProductsInEshopPriceInfo");
        PageList<ProductInEshopPriceInfoListDto> result = productInEshopService.findProductsInEshopPriceInfo(findDto);
        logger.debug("<< findProductsInEshopPriceInfo");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForProduct(ProductFindDto findDto) {
        logger.debug(">> findPriceInfoInEshopsForProduct findDto {}", findDto);
        PageList<ProductInEshopPriceResultListDto> result = productInEshopService.findPriceInfoInEshopsForProduct(findDto);
        logger.debug("<< findPriceInfoInEshopsForProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInEshopForPictureDownloadInfoDto findUrlOfProductsInEshopWithoutPicture(EshopType eshopType) {
        logger.debug(">> findUrlOfProductsInEshopWithoutPicture");
        ProductInEshopForPictureDownloadInfoDto result = productInEshopService.findUrlOfProductsInEshopWithoutPicture(eshopType);
        logger.debug("<< findUrlOfProductsInEshopWithoutPicture");
        return result;
    }

    // --------- GROUP_OF_PRODUCTS ------------


    @Override
    @Transactional
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        logger.debug(">> createGroupOfProduct");
        Long result = groupOfProductService.createGroupOfProduct(dto);
        logger.debug("<< createGroupOfProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public GroupOfProductDto findGroupOfProductById(Long groupOfProductId) {
        logger.debug(">> findGroupOfProductById");
        GroupOfProductDto result = groupOfProductService.findGroupOfProductById(groupOfProductId);
        logger.debug("<< findGroupOfProductById");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<GroupOfProductListDto> findGroupOfProductByFilter(GroupOfProductFindDto filter) {
        logger.debug(">> findGroupOfProductByFilter");
        PageList<GroupOfProductListDto> result = groupOfProductService.findGroupOfProductByFilter(filter);
        logger.debug("<< findGroupOfProductByFilter");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(ProductPriceInGroupFindDto filter) {
        logger.debug(">> findPriceInfoInEshopsForGroup");
        PageList<ProductInEshopPriceResultListDto> result = groupOfProductService.findPriceInfoInEshopsForGroup(filter);
        logger.debug("<< findPriceInfoInEshopsForGroup");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupIdNameDto> findAllProductGroupSelection() {
        logger.debug(">> findAllProductGroupSelection");
        List<GroupIdNameDto> result = groupOfProductService.findAllProductGroupSelection();
        logger.debug("<< findAllProductGroupSelection");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<ProductListDto> findProductsInGroup(GroupOfProductFindDto filter) {
        logger.debug(">> findProductsInGroup");
        PageList<ProductListDto> result = groupOfProductService.findProductsInGroup(filter);
        logger.debug("<< findProductsInGroup");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupOfProductListDto> findAllGroupsOfProducts() {
        logger.debug(">> findAllGroupsOfProducts");
        List<GroupOfProductListDto> result = groupOfProductService.findAllGroupsOfProducts();
        logger.debug("<< findAllGroupsOfProducts");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListDto> findProductsInGroup(Long groupOfProductId) {
        logger.debug(">> findProductsInGroup");
        List<ProductListDto> result = groupOfProductService.findProductsInGroup(groupOfProductId);
        logger.debug("<< findProductsInGroup");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListDto> findProductsNotInGroup(Long groupOfProductId) {
        logger.debug(">> findProductsNotInGroup");
        List<ProductListDto> result = groupOfProductService.findProductsNotInGroup(groupOfProductId);
        logger.debug("<< findProductsNotInGroup");
        return result;
    }

    @Override
    @Transactional
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {
        logger.debug(">> addProductsToGroup");
        groupOfProductService.addProductsToGroup(productsIdToBeAdded, groupOfProductId);
        logger.debug("<< addProductsToGroup");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(Long groupId) {
        logger.debug(">> findPriceInfoInEshopsForGroup");
        List<ProductInEshopPriceResultListDto> result = groupOfProductService.findPriceInfoInEshopsForGroup(groupId);
        logger.debug("<< findPriceInfoInEshopsForGroup");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existProductWithGivenUrl(String productUrl) {
        logger.debug(">> existProductWithGivenUrl");
        boolean result = productInEshopService.existProductWithGivenUrl(productUrl);
        logger.debug("<< existProductWithGivenUrl");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public StepOneResponseDto analyzeProductUrl(StepOneRequestDto stepOneRequestDto) throws PriceComparatorBusinesException {
        logger.debug(">> analyzeProductUrl");
        StepOneResponseDto stepOneResponseDto = productInEshopService.analyzeProductUrl(stepOneRequestDto);
        logger.debug("<< analyzeProductUrl");
        return stepOneResponseDto;
    }

    @Override
    public void createNewProdutAndAddToEshop(StepTwoRequestDto stepTwoRequestDto) throws PriceComparatorBusinesException {
        //TODO impl
    }


    //    @Override
//    @Transactional(readOnly = true)
//    public List<ProductInEshopPriceResultListDto> old_findPriceInfoInEshopsForProduct(Long productId) {
//        logger.debug(">> findPriceInfoInEshopsForProduct");
//        List<ProductInEshopPriceResultListDto> result = productInEshopService.old_findPriceInfoInEshopsForProduct(productId);
//        logger.debug("<< findPriceInfoInEshopsForProduct");
//        return result;
//    }


}
