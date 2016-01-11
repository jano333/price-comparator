package sk.hudak.pricecomparator.server.database.assembler;

import sk.hudak.pricecomparator.middle.api.to.ProductInEshopCustomListDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopListDto;
import sk.hudak.pricecomparator.server.database.model.ProductInEshopEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 30. 12. 2015.
 */
@Named
public class ProductInEshopAssembler {

    @Inject
    private ProductAssembler productAssembler;

    public List<ProductInEshopListDto> transformToListOfProductInEshopListDto(List<ProductInEshopEntity> allProductInEshop) {
        if (allProductInEshop == null) {
            return null;
        }
        List<ProductInEshopListDto> result = new ArrayList<>(allProductInEshop.size());
        for (ProductInEshopEntity entity : allProductInEshop) {
            result.add(transformToProductInEshopListDto(entity));
        }
        return result;
    }

    public ProductInEshopListDto transformToProductInEshopListDto(ProductInEshopEntity entity) {
        if (entity == null) {
            return null;
        }
        ProductInEshopListDto listDto = new ProductInEshopListDto();
        listDto.setId(entity.getId());
        listDto.setEshopId(entity.getEshop().getId());
        listDto.setProductId(entity.getProduct().getId());
        //FIXME nesedia nazvy... urcit ako to zjednotit
        listDto.setEshopProductPage(entity.getProductPageInEshop());
        return listDto;
    }

    public List<ProductInEshopDto> transformToListOfProductInEshopDto(List<ProductInEshopEntity> productsInEshopByProductId) {
        if (productsInEshopByProductId == null) {
            return null;
        }
        List<ProductInEshopDto> result = new ArrayList<>(productsInEshopByProductId.size());
        for (ProductInEshopEntity entity : productsInEshopByProductId) {
            result.add(transformToProductInEshopDto(entity));
        }
        return result;
    }

    public ProductInEshopDto transformToProductInEshopDto(ProductInEshopEntity entity) {
        if (entity == null) {
            return null;
        }
        ProductInEshopDto result = new ProductInEshopDto();
        result.setId(entity.getId());
        result.setEshopId(entity.getEshop().getId());
        result.setProductId(entity.getProduct().getId());
        result.setEshopProductPage(entity.getProductPageInEshop());
        //TODO dalsie

        return result;
    }

    public List<ProductInEshopCustomListDto> transformToListOfProductInEshopCustomListDto(List<ProductInEshopEntity> productsInEshop) {
        if (productsInEshop == null) {
            return null;
        }
        List<ProductInEshopCustomListDto> result = new ArrayList<>(productsInEshop.size());
        for (ProductInEshopEntity entity : productsInEshop) {
            result.add(transformToProductInEshopCustomListDto(entity));
        }
        return result;
    }

    private ProductInEshopCustomListDto transformToProductInEshopCustomListDto(ProductInEshopEntity entity) {
        if (entity == null) {
            return null;
        }
        ProductInEshopCustomListDto result = new ProductInEshopCustomListDto();
        result.setId(entity.getId());
        result.setProductListDto(productAssembler.transfomToProductListDto(entity.getProduct()));
        result.setEshopProductPage(entity.getProductPageInEshop());
        return result;
    }


}
