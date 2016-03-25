package sk.hudak.pricecomparator.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.*;
import sk.hudak.pricecomparator.middle.to.*;

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
    @Transactional(readOnly = true)
    public PageList<EshopListDto> findEshops(EshopFindDto filter) {
        return eshopService.findEshops(filter);
    }

    @Override
    @Transactional
    public Long createEshop(EshopCreateDto createDto) {
        return eshopService.createEshop(createDto);
    }

    @Override
    @Transactional(readOnly = true)
    public EshopDto getEshopById(Long eshopId) {
        return eshopService.getEshopById(eshopId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> findAllEshops() {
        return eshopService.findAllEshops();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopIdNameDto> findAllEshopsForSelection() {
        return eshopService.findAllEshopsForSelection();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductIdNameDto> findAllProductForSelection() {
        return productService.findAllProductForSelection();
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<ProductListDto> findProducts(ProductFindDto filter) {
        return productService.findProducts(filter);
    }

    // -------------- PRODUCT -----------
    @Override
    @Transactional
    public Long createProduct(ProductCreateDto createDto) {
        return productService.createProduct(createDto);
    }

    @Override
    @Transactional
    public void updateProduct(ProductUpdateDto updateDto) {
        productService.updateProduct(updateDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProduct(Long productId) {
        return productService.getProduct(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListDto> getAllProduct() {
        return productService.getAllProduct();
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
    public List<ProductInEshopPriceResultListDto> old_findPriceInfoInEshopsForProduct(Long productId) {
        logger.debug(">> findPriceInfoInEshopsForProduct");
        List<ProductInEshopPriceResultListDto> result = productInEshopService.old_findPriceInfoInEshopsForProduct(productId);
        logger.debug("<< findPriceInfoInEshopsForProduct");
        return result;
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
        logger.debug(">> findPriceInfoInEshopsForProduct");
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
        return groupOfProductService.findAllGroupsOfProducts();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListDto> findProductsInGroup(Long groupOfProductId) {
        return groupOfProductService.findProductsInGroup(groupOfProductId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListDto> findProductsNotInGroup(Long groupOfProductId) {
        return groupOfProductService.findProductsNotInGroup(groupOfProductId);
    }

    @Override
    @Transactional
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {
        groupOfProductService.addProductsToGroup(productsIdToBeAdded, groupOfProductId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(Long groupId) {
        logger.debug(">> findPriceInfoInEshopsForGroup");
        List<ProductInEshopPriceResultListDto> result = groupOfProductService.findPriceInfoInEshopsForGroup(groupId);
        logger.debug("<< findPriceInfoInEshopsForGroup");
        return result;
    }


}
