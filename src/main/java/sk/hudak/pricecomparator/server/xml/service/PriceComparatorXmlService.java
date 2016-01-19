package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.*;
import sk.hudak.pricecomparator.middle.api.to.*;

import java.util.List;
import java.util.Set;

/**
 * Created by jan on 15. 10. 2015.
 */
@Deprecated
public class PriceComparatorXmlService implements PriceComparatorService {

    private CategoryService categoryService;
    private EshopService eshopService;
    private ProductService productService;
    private ProductInEshopService productInEshopService;
    private GroupOfProductService groupOfProductService;

    @Deprecated
    public PriceComparatorXmlService() {
        this.categoryService = new CategoryServiceXmlImpl();
        this.eshopService = new EshopServiceXmlImpl();
        this.productService = new ProductServiceXmlIml();
        this.productInEshopService = new ProductInEshopServiceXmlImpl();
        this.groupOfProductService = new GroupOfProductServiceXmlImpl();
    }

    @Override
    public Long createCategory(CategoryCreateDto dto) {
        return categoryService.createCategory(dto);
    }

    @Override
    public Long createEshop(EshopCreateDto dto) {
        return eshopService.createEshop(dto);
    }

    @Override
    public EshopDto getEshopById(Long eshopId) {
        return eshopService.getEshopById(eshopId);
    }

    @Override
    public List<EshopListDto> getAllEshops() {
        return eshopService.getAllEshops();
    }

    @Override
    public Long createProductInEshop(ProductInEshopCreateDto dto) {
        return productInEshopService.createProductInEshop(dto);
    }

    @Override
    public List<ProductInEshopListDto> findAllProductInEshop() {
        return productInEshopService.findAllProductInEshop();
    }

    @Override
    public ProductInEshopDto getProductInEshop(Long productId, Long eshopId) {
        return productInEshopService.getProductInEshop(productId, eshopId);
    }

    @Override
    public List<ProductInEshopListDto> findProductsInEshopByProductId(Long productId) {
        return productInEshopService.findProductsInEshopByProductId(productId);
    }

    @Override
    public List<EshopListDto> findEshopsWithoutProduct(Long productId) {
        return productInEshopService.findEshopsWithoutProduct(productId);
    }

    @Override
    public List<EshopListDto> findEshopsWithProduct(Long productId) {
        return productInEshopService.findEshopsWithProduct(productId);
    }

    @Override
    public List<ProductInEshopDto> findProductsInEshopForDownloaderByProductId(Long productId) {
        return productInEshopService.findProductsInEshopForDownloaderByProductId(productId);
    }

    @Override
    public List<ProductInEshopCustomListDto> findProductsInEshop(Long eshopId) {
        return productInEshopService.findProductsInEshop(eshopId);
    }


    @Override
    public ProductInEshopDto findProductForPriceUpdate(EshopType eshopType) {
        return null;
    }

    @Override
    public void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto) {

    }

    @Override
    public List<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForProduct(Long productId) {
        return null;
    }


    @Override
    public Long createProduct(ProductCreateDto dto) {
        return productService.createProduct(dto);
    }

    @Override
    public void updateProduct(ProductEditDto editDto) {
        productService.updateProduct(editDto);
    }

    @Override
    public ProductDto getProduct(Long productId) {
        return productService.getProduct(productId);
    }

    @Override
    public List<ProductListDto> getAllProduct() {
        return productService.getAllProduct();
    }

    @Override
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        return groupOfProductService.createGroupOfProduct(dto);
    }

    @Override
    public GroupOfProductDto getGroupOfProduct(Long groupOfProductId) {
        return groupOfProductService.getGroupOfProduct(groupOfProductId);
    }

    @Override
    public List<GroupOfProductListDto> findAllGroupsOfProducts() {
        return groupOfProductService.findAllGroupsOfProducts();
    }

    @Override
    public List<ProductListDto> getProductsInGroup(Long groupOfProductId) {
        return groupOfProductService.getProductsInGroup(groupOfProductId);
    }

    @Override
    public List<ProductListDto> getProductsNotInGroup(Long groupOfProductId) {
        return groupOfProductService.getProductsNotInGroup(groupOfProductId);
    }

    @Override
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {
        groupOfProductService.addProductsToGroup(productsIdToBeAdded, groupOfProductId);

    }
}
