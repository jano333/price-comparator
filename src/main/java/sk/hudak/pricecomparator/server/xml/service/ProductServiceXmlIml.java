package sk.hudak.pricecomparator.server.xml.service;

import org.apache.commons.lang3.StringUtils;
import sk.hudak.pricecomparator.middle.api.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.api.service.ProductService;
import sk.hudak.pricecomparator.middle.api.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.api.to.ProductDto;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;
import sk.hudak.pricecomparator.server.xml.model.ProductXmlEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductServiceXmlIml extends AbstracServiceXmlImpl implements ProductService {

    @Override
    public Long createProduct(ProductCreateDto dto) {
        if (dto == null) {
            throw new PriceComparatorException("dto is null");
        }
        if (StringUtils.isBlank(dto.getName())) {
            throw new PriceComparatorException("name is null or empty");
        }
        if (dto.getUnit() == null) {
            throw new PriceComparatorException("unit is null");
        }
        if (dto.getCountOfUnit() == null) {
            throw new PriceComparatorException("countOfUnit is null");
        }

        ProductXmlEntity entity = new ProductXmlEntity();
        entity.setId(generateNewId());
        entity.setName(dto.getName());
        entity.setCountOfItemInOnePackage(dto.getCountOfItemInOnePackage());
        entity.setUnit(dto.getUnit());
        entity.setCountOfUnit(dto.getCountOfUnit());
        entity.setCategoryId(dto.getCategoryId());

        saveXmlEntity(entity);

        return entity.getId();
    }

    @Override
    public ProductDto getProduct(Long productId) {
        if (productId == null) {
            throw new PriceComparatorException("product id is null");
        }
        for (ProductXmlEntity product : xmlDataDb.getProducts()) {
            if (product.getId().equals(productId)) {
                return transformToProductDto(product);
            }
        }
        throw new PriceComparatorException("produkt with id " + productId + " not found.");
    }


    @Override
    public List<ProductListDto> getAllProduct() {
        List<ProductXmlEntity> products = xmlDataDb.getProducts();
        List<ProductListDto> result = new ArrayList<>(products.size());
        for (ProductXmlEntity product : products) {
            result.add(transformToProductListDto(product));
        }
        Collections.sort(result, new ProductListDtoComparatorByName());
        return result;
    }

    private ProductListDto transformToProductListDto(ProductXmlEntity product) {
        if (product == null) {
            return null;
        }
        ProductListDto dto = new ProductListDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        return dto;
    }

    private ProductDto transformToProductDto(ProductXmlEntity product) {
        if (product == null) {
            return null;
        }
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategoryId(product.getCategoryId());
        dto.setCountOfItemInOnePackage(product.getCountOfItemInOnePackage());
        dto.setCountOfUnit(product.getCountOfUnit());
        dto.setUnit(product.getUnit());
        return dto;
    }

    private class ProductListDtoComparatorByName implements Comparator<ProductListDto> {
        @Override
        public int compare(ProductListDto o1, ProductListDto o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    }


}
