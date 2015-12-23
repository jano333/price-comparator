package sk.hudak.pricecomparator.server.database.service;

import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.*;

import java.util.List;
import java.util.Set;

/**
 * Created by jan on 29. 11. 2015.
 */
public class PriceComparatorDbServiceImp implements PriceComparatorService {

    @Override
    public Long createEshop(EshopCreateDto dto) {
        return null;
    }

    @Override
    public EshopDto getEshopById(Long eshopId) {
        return null;
    }

    @Override
    public List<EshopListDto> getAllEshops() {
        return null;
    }

    @Override
    public Long createCategory(CategoryCreateDto dto) {
        return null;
    }

    @Override
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        return null;
    }

    @Override
    public GroupOfProductDto getGroupOfProduct(Long groupOfProductId) {
        return null;
    }

    @Override
    public List<GroupOfProductListDto> findAllGroupsOfProducts() {
        return null;
    }

    @Override
    public List<ProductListDto> getProductsInGroup(Long groupOfProductId) {
        return null;
    }

    @Override
    public List<ProductListDto> getProductsNotInGroup(Long groupOfProductId) {
        return null;
    }

    @Override
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {

    }

    @Override
    public Long createProductInEshop(ProductInEshopCreateDto dto) {
        return null;
    }

    @Override
    public List<ProductInEshopListDto> getAllProductInEshop() {
        return null;
    }

    @Override
    public List<ProductInEshopListDto> getProductsInEshopByProductId(Long productId) {
        return null;
    }

    @Override
    public List<EshopListDto> getEshopsWithoutProduct(Long productId) {
        return null;
    }

    @Override
    public List<EshopListDto> getEshopsWithProduct(Long productId) {
        return null;
    }

    @Override
    public List<ProductInEshopDto> getProductsInEshopForDownloaderByProductId(Long productId) {
        return null;
    }

    @Override
    public List<ProductInEshopCustomListDto> getProductsInEshop(Long eshopId) {
        return null;
    }

    @Override
    public Long createProduct(ProductCreateDto dto) {
        return null;
    }

    @Override
    public void updateProduct(ProductEditDto editDto) {

    }

    @Override
    public ProductDto getProduct(Long productId) {
        return null;
    }

    @Override
    public List<ProductListDto> getAllProduct() {
        return null;
    }
}
