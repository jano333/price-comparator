package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.ProductInEshopService;
import sk.hudak.pricecomparator.middle.api.to.*;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductInEshopServiceXmlImpl extends AbstracServiceXmlImpl implements ProductInEshopService {

    @Override
    public Long createProductInEshop(ProductInEshopCreateDto dto) {
        return null;
    }

    @Override
    public List<ProductInEshopListDto> findAllProductInEshop() {
        return null;
    }

    @Override
    public ProductInEshopDto getProductInEshop(Long productId, Long eshopId) {
        return null;
    }


    @Override
    public List<ProductInEshopListDto> findProductsInEshopByProductId(Long productId) {
        return null;
    }

    @Override
    public List<EshopListDto> findEshopsWithoutProduct(Long productId) {
        return null;
    }

    @Override
    public List<EshopListDto> findEshopsWithProduct(Long productId) {
        return null;
    }

    @Override
    public List<ProductInEshopDto> findProductsInEshopForDownloaderByProductId(Long productId) {
        return null;
    }

    @Override
    public List<ProductInEshopCustomListDto> findProductsInEshop(Long eshopId) {
        return null;
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


}
