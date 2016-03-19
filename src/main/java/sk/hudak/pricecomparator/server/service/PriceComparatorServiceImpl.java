package sk.hudak.pricecomparator.server.service;

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
        System.out.println(">> createProductInEshop");
        Long result = productInEshopService.createProductInEshop(dto);
        System.out.println("<< createProductInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopListDto> findAllProductInEshop() {
        System.out.println(">> findAllProductInEshop");
        List<ProductInEshopListDto> result = productInEshopService.findAllProductInEshop();
        System.out.println("<< findAllProductInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInEshopDto getProductInEshop(Long productId, Long eshopId) {
        System.out.println(">> findProductInEshop");
        ProductInEshopDto result = productInEshopService.getProductInEshop(productId, eshopId);
        System.out.println("<< findProductInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopListDto> findProductsInEshopByProductId(Long productId) {
        System.out.println(">> findProductsInEshopByProductId");
        List<ProductInEshopListDto> result = productInEshopService.findProductsInEshopByProductId(productId);
        System.out.println("<< findProductsInEshopByProductId");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> findEshopsWithProduct(Long productId) {
        System.out.println(">> findEshopsWithProduct");
        List<EshopListDto> result = productInEshopService.findEshopsWithProduct(productId);
        System.out.println("<< findEshopsWithProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> findEshopsWithoutProduct(Long productId) {
        System.out.println(">> findEshopsWithoutProduct");
        List<EshopListDto> result = productInEshopService.findEshopsWithoutProduct(productId);
        System.out.println("<< findEshopsWithoutProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopDto> findProductsInEshopForDownloaderByProductId(Long productId) {
        System.out.println(">> findProductsInEshopForDownloaderByProductId");
        List<ProductInEshopDto> result = productInEshopService.findProductsInEshopForDownloaderByProductId(productId);
        System.out.println("<< findProductsInEshopForDownloaderByProductId");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopCustomListDto> findProductsInEshop(Long eshopId) {
        System.out.println(">> findProductsInEshop");
        List<ProductInEshopCustomListDto> result = productInEshopService.findProductsInEshop(eshopId);
        System.out.println("<< findProductsInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInEshopDto findProductForPriceUpdate(EshopType eshopType) {
        System.out.println(">> findProductForPriceUpdate");
        ProductInEshopDto result = productInEshopService.findProductForPriceUpdate(eshopType);
        System.out.println("<< findProductForPriceUpdate");
        return result;
    }

    @Override
    @Transactional
    public void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto) {
        System.out.println(">> updateProductInEshopPrice");
        productInEshopService.updateProductInEshopPrice(updateDto);
        System.out.println("<< updateProductInEshopPrice");
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopPriceResultListDto> old_findPriceInfoInEshopsForProduct(Long productId) {
        System.out.println(">> findPriceInfoInEshopsForProduct");
        List<ProductInEshopPriceResultListDto> result = productInEshopService.old_findPriceInfoInEshopsForProduct(productId);
        System.out.println("<< findPriceInfoInEshopsForProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopPriceInfoListDto> findProductInEshopPriceInfoForEshop(Long eshopId) {
        System.out.println(">> findProductInEshopPriceInfoForEshop");
        List<ProductInEshopPriceInfoListDto> result = productInEshopService.findProductInEshopPriceInfoForEshop(eshopId);
        System.out.println("<< findProductInEshopPriceInfoForEshop");
        return result;
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopPriceInfoListDto> old_findProductsInEshopPriceInfo(ProductInEshopFindDto findDto) {
        System.out.println(">> findProductsInEshopPriceInfo");
        List<ProductInEshopPriceInfoListDto> result = productInEshopService.old_findProductsInEshopPriceInfo(findDto);
        System.out.println("<< findProductsInEshopPriceInfo");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<ProductInEshopPriceInfoListDto> findProductsInEshopPriceInfo(ProductInEshopFindDto findDto) {
        return productInEshopService.findProductsInEshopPriceInfo(findDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForProduct(ProductFindDto findDto) {
        return productInEshopService.findPriceInfoInEshopsForProduct(findDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInEshopForPictureDownloadInfoDto findUrlOfProductsInEshopWithoutPicture(EshopType eshopType) {
        return productInEshopService.findUrlOfProductsInEshopWithoutPicture(eshopType);
    }


    // --------- GROUP_OF_PRODUCTS ------------

    @Override
    @Transactional(readOnly = true)
    public PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(ProductPriceInGroupFindDto filter) {
        return groupOfProductService.findPriceInfoInEshopsForGroup(filter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupIdNameDto> findAllProductGroupSelection() {
        return groupOfProductService.findAllProductGroupSelection();
    }

    @Override
    @Transactional
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        System.out.println(">> createGroupOfProduct");
        Long result = groupOfProductService.createGroupOfProduct(dto);
        System.out.println("<< createGroupOfProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public GroupOfProductDto getGroupOfProduct(Long groupOfProductId) {
        return groupOfProductService.getGroupOfProduct(groupOfProductId);
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
        System.out.println(">> findPriceInfoInEshopsForGroup");
        List<ProductInEshopPriceResultListDto> result = groupOfProductService.findPriceInfoInEshopsForGroup(groupId);
        System.out.println("<< findPriceInfoInEshopsForGroup");
        return result;
    }


}
