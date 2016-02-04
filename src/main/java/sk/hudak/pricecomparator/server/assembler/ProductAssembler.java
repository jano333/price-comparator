package sk.hudak.pricecomparator.server.assembler;

import sk.hudak.pricecomparator.middle.to.ProductDto;
import sk.hudak.pricecomparator.middle.to.ProductListDto;
import sk.hudak.pricecomparator.server.model.CategoryEntity;
import sk.hudak.pricecomparator.server.model.ProductEntity;
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
            result.add(transfomToProductListDto(product));
        }

        return result;
    }

    public ProductListDto transfomToProductListDto(ProductEntity product) {
        if (product == null) {
            return null;
        }
        ProductListDto result = new ProductListDto();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setImagePath(ImageUtils.findProductImage(product.getId()));

        return result;
    }
}
