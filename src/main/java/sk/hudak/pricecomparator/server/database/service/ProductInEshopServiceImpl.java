package sk.hudak.pricecomparator.server.database.service;

import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.service.ProductInEshopService;
import sk.hudak.pricecomparator.middle.api.to.*;
import sk.hudak.pricecomparator.server.core.JefValidator;
import sk.hudak.pricecomparator.server.database.assembler.EshopAssembler;
import sk.hudak.pricecomparator.server.database.assembler.ProductInEshopAssembler;
import sk.hudak.pricecomparator.server.database.dao.ProductInEshopDao;
import sk.hudak.pricecomparator.server.database.facade.ProductInEshopFacade;
import sk.hudak.pricecomparator.server.database.model.EshopEntity;
import sk.hudak.pricecomparator.server.database.model.ProductInEshopEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * DB sevice implementacia nad entitou {@link ProductInEshopEntity}.
 * <p/>
 * Created by jan on 30. 12. 2015.
 */
@Named
public class ProductInEshopServiceImpl implements ProductInEshopService {

    @Inject
    private ProductInEshopDao productInEshopDao;

    @Inject
    private ProductInEshopFacade productInEshopFacade;

    @Inject
    private ProductInEshopAssembler productInEshopAssembler;

    @Inject
    private EshopAssembler eshopAssembler;

    @Inject
    private JefValidator val;

    @Override
    public Long createProductInEshop(ProductInEshopCreateDto dto) {
        return productInEshopFacade.createProductInEshop(dto);
    }

    @Override
    public List<ProductInEshopListDto> findAllProductInEshop() {
        List<ProductInEshopEntity> allProductInEshop = productInEshopDao.findAllProductInEshop();
        return productInEshopAssembler.transformToListOfProductInEshopListDto(allProductInEshop);
    }

    @Override
    public ProductInEshopDto getProductInEshop(Long productId, Long eshopId) {
        val.notNull(productId, "productId is null");
        val.notNull(eshopId, "eshopId is null");

        ProductInEshopEntity productInEshopEntity = productInEshopDao.findProductInEshop(productId, eshopId);
        return productInEshopAssembler.transformToProductInEshopDto(productInEshopEntity);
    }

    @Override
    public List<EshopListDto> findEshopsWithProduct(Long productId) {
        val.notNull(productId, "productId is null");

        List<EshopEntity> eshopsWithProduct = productInEshopDao.findEshopsWithProduct(productId);
        return eshopAssembler.transformToListOfEshopListDto(eshopsWithProduct);
    }

    @Override
    public List<EshopListDto> findEshopsWithoutProduct(Long productId) {
        val.notNull(productId, "productId is null");

        List<EshopEntity> eshopsWithoutProduct = productInEshopDao.findEshopsWithoutProduct(productId);
        return eshopAssembler.transformToListOfEshopListDto(eshopsWithoutProduct);
    }

    @Override
    public List<ProductInEshopListDto> findProductsInEshopByProductId(Long productId) {
        val.notNull(productId, "productId is null");

        List<ProductInEshopEntity> productsInEshopByProductId = productInEshopDao.findProductsInEshopByProductId(productId);
        return productInEshopAssembler.transformToListOfProductInEshopListDto(productsInEshopByProductId);
    }

    @Override
    public List<ProductInEshopDto> findProductsInEshopForDownloaderByProductId(Long productId) {
        val.notNull(productId, "productId is null");

        List<ProductInEshopEntity> productsInEshopByProductId = productInEshopDao.findProductsInEshopByProductId(productId);
        return productInEshopAssembler.transformToListOfProductInEshopDto(productsInEshopByProductId);
    }

    @Override
    public List<ProductInEshopCustomListDto> findProductsInEshop(Long eshopId) {
        val.notNull(eshopId, "eshopId is null");

        List<ProductInEshopEntity> productsInEshop = productInEshopDao.findProductsInEshop(eshopId);
        return productInEshopAssembler.transformToListOfProductInEshopCustomListDto(productsInEshop);
    }

    @Override
    public ProductInEshopDto findProductForPriceUpdate(EshopType eshopType) {
        ProductInEshopEntity productInEshop = productInEshopFacade.findProductForPriceUpdate(eshopType);
        return productInEshopAssembler.transformToProductInEshopDto(productInEshop);

    }

    @Override
    public void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto) {
        productInEshopFacade.updateProductInEshopPrice(updateDto);
    }
}
