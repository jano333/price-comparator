package sk.hudak.pricecomparator.server.database.service;

import org.springframework.transaction.annotation.Transactional;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.*;
import sk.hudak.pricecomparator.middle.api.to.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

/**
 * Created by jan on 29. 11. 2015.
 */
@Named
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
    @Transactional
    public Long createEshop(EshopCreateDto dto) {
        return eshopService.createEshop(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public EshopDto getEshopById(Long eshopId) {
        return eshopService.getEshopById(eshopId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> getAllEshops() {
        return eshopService.getAllEshops();
    }

    // -------------- PRODUCT -----------
    @Override
    @Transactional
    public Long createProduct(ProductCreateDto dto) {
        return productService.createProduct(dto);
    }

    @Override
    @Transactional
    public void updateProduct(ProductEditDto editDto) {
        productService.updateProduct(editDto);
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
    public List<ProductInEshopListDto> getAllProductInEshop() {
        System.out.println(">> getAllProductInEshop");
        List<ProductInEshopListDto> result = productInEshopService.getAllProductInEshop();
        System.out.println("<< getAllProductInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInEshopDto getProductInEshop(Long productId, Long eshopId) {
        System.out.println(">> getProductInEshop");
        ProductInEshopDto result = productInEshopService.getProductInEshop(productId, eshopId);
        System.out.println("<< getProductInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopListDto> getProductsInEshopByProductId(Long productId) {
        System.out.println(">> getProductsInEshopByProductId");
        List<ProductInEshopListDto> result = productInEshopService.getProductsInEshopByProductId(productId);
        System.out.println("<< getProductsInEshopByProductId");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> getEshopsWithProduct(Long productId) {
        System.out.println(">> getEshopsWithProduct");
        List<EshopListDto> result = productInEshopService.getEshopsWithProduct(productId);
        System.out.println("<< getEshopsWithProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> getEshopsWithoutProduct(Long productId) {
        System.out.println(">> getEshopsWithoutProduct");
        List<EshopListDto> result = productInEshopService.getEshopsWithoutProduct(productId);
        System.out.println("<< getEshopsWithoutProduct");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopDto> getProductsInEshopForDownloaderByProductId(Long productId) {
        System.out.println(">> getProductsInEshopForDownloaderByProductId");
        List<ProductInEshopDto> result = productInEshopService.getProductsInEshopForDownloaderByProductId(productId);
        System.out.println("<< getProductsInEshopForDownloaderByProductId");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopCustomListDto> getProductsInEshop(Long eshopId) {
        System.out.println(">> getProductsInEshop");
        List<ProductInEshopCustomListDto> result = productInEshopService.getProductsInEshop(eshopId);
        System.out.println("<< getProductsInEshop");
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInEshopDto getProductForPriceUpdate(EshopType eshopType) {
        System.out.println(">> getProductForPriceUpdate");
        ProductInEshopDto result = productInEshopService.getProductForPriceUpdate(eshopType);
        System.out.println("<< getProductForPriceUpdate");
        return result;
    }

    @Override
    @Transactional
    public void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto) {
        System.out.println(">> updateProductInEshopPrice");
        productInEshopService.updateProductInEshopPrice(updateDto);
        System.out.println("<< updateProductInEshopPrice");
    }


    // --------- GROUP_OF_PRODUCTS ------------

    @Override
    @Transactional
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        return groupOfProductService.createGroupOfProduct(dto);
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
    public List<ProductListDto> getProductsInGroup(Long groupOfProductId) {
        return groupOfProductService.getProductsInGroup(groupOfProductId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductListDto> getProductsNotInGroup(Long groupOfProductId) {
        return groupOfProductService.getProductsNotInGroup(groupOfProductId);
    }

    @Override
    @Transactional
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {
        groupOfProductService.addProductsToGroup(productsIdToBeAdded, groupOfProductId);
    }


}
