package sk.hudak.pricecomparator.server.domain.assembler;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.to.product.ProductDto;
import sk.hudak.pricecomparator.middle.to.product.ProductIdNameDto;
import sk.hudak.pricecomparator.middle.to.product.ProductListDto;
import sk.hudak.pricecomparator.server.domain.model.CategoryEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductEntity;
import sk.hudak.pricecomparator.server.utils.ImageUtils;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 29. 12. 2015.
 */
@Named
public class ProductAssembler {

    public ProductDto transformToProductDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        ProductDto dto = new ProductDto();
        dto.setId(productEntity.getId());
        dto.setName(productEntity.getName());
        CategoryEntity category = productEntity.getCategory();
        if (category != null) {
            dto.setCategoryId(category.getId());
        }
        dto.setCountOfItemInOnePackage(productEntity.getCountOfItemInOnePackage());
        dto.setCountOfUnit(productEntity.getCountOfUnit());
        dto.setUnit(productEntity.getUnit());
        return dto;
    }

    public List<ProductListDto> transformToListOfProductListDto(List<ProductEntity> listOfProductEntity) {
        if (listOfProductEntity == null) {
            return null;
        }
        List<ProductListDto> result = new ArrayList<>(listOfProductEntity.size());
        for (ProductEntity product : listOfProductEntity) {
            result.add(transformToProductListDto(product));
        }

        return result;
    }

    public ProductListDto transformToProductListDto(ProductEntity product) {
        if (product == null) {
            return null;
        }
        ProductListDto result = new ProductListDto();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setImagePath(ImageUtils.findProductImage(product.getId()));

        return result;
    }

    public List<ProductIdNameDto> transformToListOfProductIdNameDto(List<ProductEntity> allProducts) {
        if (allProducts == null) {
            return null;
        }
        List<ProductIdNameDto> result = new ArrayList<>(allProducts.size());
        for (ProductEntity productEntity : allProducts) {
            result.add(transformToProductIdNameDto(productEntity));
        }
        return result;
    }

    public ProductIdNameDto transformToProductIdNameDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        ProductIdNameDto result = new ProductIdNameDto();
        result.setId(productEntity.getId());
        result.setName(productEntity.getName());
        return result;
    }

    public PageList<ProductListDto> transformToPageListOfProductListDto(PageList<ProductEntity> products) {

        List<ProductListDto> versions = new ArrayList<>(products.getEntries().size());
        for (ProductEntity entity : products.getEntries()) {
            versions.add(transformToProductListDto(entity));

        }
        return new PageList<>(versions, products.getCurrentPage(), products.getAllPageCount());
    }
}
