package sk.hudak.pricecomparator.server.database.service;

import org.springframework.transaction.annotation.Transactional;
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
        return null;
    }


    // ----------- PRODUCT_IN_ESHOP ------------
    @Override
    @Transactional
    public Long createProductInEshop(ProductInEshopCreateDto dto) {
        return productInEshopService.createProductInEshop(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopListDto> getAllProductInEshop() {
        return productInEshopService.getAllProductInEshop();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductInEshopDto getProductInEshop(Long productId, Long eshopId) {
        //TODO
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopListDto> getProductsInEshopByProductId(Long productId) {
        return productInEshopService.getProductsInEshopByProductId(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> getEshopsWithProduct(Long productId) {
        return productInEshopService.getEshopsWithProduct(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EshopListDto> getEshopsWithoutProduct(Long productId) {
        return productInEshopService.getEshopsWithoutProduct(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopDto> getProductsInEshopForDownloaderByProductId(Long productId) {
        return productInEshopService.getProductsInEshopForDownloaderByProductId(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductInEshopCustomListDto> getProductsInEshop(Long eshopId) {
        return productInEshopService.getProductsInEshop(eshopId);
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
