package sk.hudak.pricecomparator.server.database.service;

import org.springframework.transaction.annotation.Transactional;
import sk.hudak.pricecomparator.middle.api.service.EshopService;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

/**
 * Created by jan on 29. 11. 2015.
 */
@Named
public class PriceComparatorDbServiceImp implements PriceComparatorService {

    @Inject
    private EshopService eshopService;

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


    // --------- CATEGORY ----------

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
