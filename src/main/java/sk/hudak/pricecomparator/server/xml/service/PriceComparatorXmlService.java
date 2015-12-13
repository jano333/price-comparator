package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.service.*;
import sk.hudak.pricecomparator.middle.api.to.*;

import java.util.List;
import java.util.Set;

/**
 * Created by jan on 15. 10. 2015.
 */
public class PriceComparatorXmlService implements PriceComparatorService {

    private CategoryService categoryService;
    private EshopService eshopService;
    private ProductService productService;
    private ProductInEshopService productInEshopService;
    private GroupOfProductService groupOfProductService;

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
    public List<ProductInEshopListDto> getAllProductInEshop() {
        return productInEshopService.getAllProductInEshop();
    }

    @Override
    public List<ProductInEshopListDto> getProductsInEshopByProductId(Long productId) {
        return productInEshopService.getProductsInEshopByProductId(productId);
    }

    @Override
    public List<EshopListDto> getEshopsWithoutProduct(Long productId) {
        return productInEshopService.getEshopsWithoutProduct(productId);
    }

    @Override
    public List<EshopListDto> getEshopsWithProduct(Long productId) {
        return productInEshopService.getEshopsWithProduct(productId);
    }

    @Override
    public List<ProductInEshopDto> getProductsInEshopForDownloaderByProductId(Long productId) {
        return productInEshopService.getProductsInEshopForDownloaderByProductId(productId);
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
        System.out.println(">> findAllGroupsOfProducts");
        List<GroupOfProductListDto> allGroupsOfProducts = groupOfProductService.findAllGroupsOfProducts();
        System.out.println("<< findAllGroupsOfProducts");
        return allGroupsOfProducts;
    }

    @Override
    public List<ProductListDto> getProductsInGroup(Long groupOfProductId) {
        List<ProductListDto> productsInGroup = groupOfProductService.getProductsInGroup(groupOfProductId);
        return productsInGroup;
    }

    @Override
    public List<ProductListDto> getProductsNotInGroup(Long groupOfProductId) {
        List<ProductListDto> productsNotInGroup = groupOfProductService.getProductsNotInGroup(groupOfProductId);
        return productsNotInGroup;
    }

    @Override
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {
        groupOfProductService.addProductsToGroup(productsIdToBeAdded, groupOfProductId);

    }
}
