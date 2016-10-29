package sk.hudak.pricecomparator.server.domain.assembler;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.to.*;
import sk.hudak.pricecomparator.server.domain.model.EshopEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductInEshopEntity;
import sk.hudak.pricecomparator.server.utils.ImageUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
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
        EshopEntity eshop = entity.getEshop();
        result.setEshopId(new EshopIdNameDto(eshop.getId(), eshop.getName()));
        ProductEntity product = entity.getProduct();
        result.setProductId(new ProductIdNameDto(product.getId(), product.getName()));
        result.setEshopProductPage(entity.getProductPageInEshop());

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
        result.setProductListDto(productAssembler.transformToProductListDto(entity.getProduct()));
        result.setEshopProductPage(entity.getProductPageInEshop());
        return result;
    }

    public List<ProductInEshopPriceResultListDto> transformToListOfProductInEshopEntity(List<ProductInEshopEntity> entities) {
        if (entities == null) {
            return null;
        }
        List<ProductInEshopPriceResultListDto> result = new ArrayList<>(entities.size());
        for (ProductInEshopEntity entity : entities) {
            result.add(transformToProductInEshopPriceResultListDto(entity));
        }
        return result;
    }

    public ProductInEshopPriceResultListDto transformToProductInEshopPriceResultListDto(ProductInEshopEntity entity) {
        if (entity == null) {
            return null;
        }
        ProductInEshopPriceResultListDto result = new ProductInEshopPriceResultListDto();
        result.setId(entity.getId());
        result.setProductAction(entity.getProductAction());
        result.setUnit(entity.getProduct().getUnit());
        result.setPriceForUnit(entity.getPriceForUnit());
        result.setActionValidTo(entity.getActionValidTo());
        result.setPriceForOneItemInPackage(entity.getPriceForOneItemInPackage());
        result.setPriceForPackage(entity.getPriceForPackage());
        result.setProductEshopPage(entity.getProductPageInEshop());
        result.setEshopName(entity.getEshop().getName());
        result.setProductName(entity.getProduct().getName());
        result.setLastUpdatedPrice(entity.getLastUpdatedPrice());
        result.setImagePath(ImageUtils.findProductImage(entity.getProduct().getId()));

        return result;
    }

    public List<ProductInEshopPriceInfoListDto> transformToListOfProductInEshopPriceInfoListDto(List<ProductInEshopEntity> entities) {
        if (entities == null) {
            return null;
        }
        List<ProductInEshopPriceInfoListDto> result = new ArrayList<>(entities.size());
        for (ProductInEshopEntity entity : entities) {
            result.add(transformToProductInEshopPriceInfoListDto(entity));
        }
        return result;
    }

    public ProductInEshopPriceInfoListDto transformToProductInEshopPriceInfoListDto(ProductInEshopEntity entity) {
        if (entity == null) {
            return null;
        }
        ProductInEshopPriceInfoListDto result = new ProductInEshopPriceInfoListDto();
        result.setId(entity.getId());
        result.setProductAction(entity.getProductAction());
        result.setUnit(entity.getProduct().getUnit());
        result.setPriceForUnit(entity.getPriceForUnit());
        result.setActionValidTo(entity.getActionValidTo());
        result.setPriceForOneItemInPackage(entity.getPriceForOneItemInPackage());
        result.setPriceForPackage(entity.getPriceForPackage());
        result.setProductEshopPage(entity.getProductPageInEshop());
        result.setProductName(entity.getProduct().getName());
        result.setLastUpdatedPrice(entity.getLastUpdatedPrice());

        //FIXME aspon konstanty na dany adresar... do server config dat...
        File pictureFile = new File("C:\\price-comparator\\images\\" + entity.getProduct().getId() + ".jpg");
        if (pictureFile.exists()) {
            result.setPictureFullPath(pictureFile.getAbsolutePath());
        }
        return result;
    }

    public ProductInEshopForPictureDownloadInfoDto transformToProductInEshopForPictureDownloadInfoDto(ProductInEshopEntity entity) {
        if (entity == null) {
            return null;
        }
        ProductInEshopForPictureDownloadInfoDto result = new ProductInEshopForPictureDownloadInfoDto();
        result.setProductId(entity.getProduct().getId());
        result.setProductInEshopUrl(entity.getProductPageInEshop());

        return result;
    }


    //----- DONE :
    public PageList<ProductInEshopPriceInfoListDto> transformToPageListOfProductInEshopPriceInfoListDto(
            PageList<ProductInEshopEntity> productInEshopEntities) {

        List<ProductInEshopPriceInfoListDto> versions = new ArrayList<>(productInEshopEntities.getEntries().size());
        for (ProductInEshopEntity entity : productInEshopEntities.getEntries()) {
            versions.add(transformToProductInEshopPriceInfoListDto(entity));
        }
        return new PageList<>(versions, productInEshopEntities.getCurrentPage(), productInEshopEntities.getAllPageCount());
    }


    public PageList<ProductInEshopPriceResultListDto> transformToPageListOfProductInEshopPriceResultListDto(
            PageList<ProductInEshopEntity> productInEshopEntities) {

        List<ProductInEshopPriceResultListDto> versions = new ArrayList<>(productInEshopEntities.getEntries().size());
        for (ProductInEshopEntity entity : productInEshopEntities.getEntries()) {
            versions.add(transformToProductInEshopPriceResultListDto(entity));
        }
        return new PageList<>(versions, productInEshopEntities.getCurrentPage(), productInEshopEntities.getAllPageCount());
    }
}
