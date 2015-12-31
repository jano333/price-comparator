package sk.hudak.pricecomparator.server.database.service;

import sk.hudak.pricecomparator.middle.api.service.ProductInEshopService;
import sk.hudak.pricecomparator.middle.api.to.*;
import sk.hudak.pricecomparator.server.core.JefValidator;
import sk.hudak.pricecomparator.server.database.assembler.EshopAssembler;
import sk.hudak.pricecomparator.server.database.assembler.ProductInEshopAssembler;
import sk.hudak.pricecomparator.server.database.dao.ProductInEshopDao;
import sk.hudak.pricecomparator.server.database.facade.ProductInEshopFacade;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
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
    public List<ProductInEshopListDto> getAllProductInEshop() {
        return productInEshopAssembler.transformToListOfProductInEshopListDto(productInEshopDao.getAllProductInEshop());
    }

    @Override
    public List<EshopListDto> getEshopsWithProduct(Long productId) {
        val.notNull(productId, "productId is null");
        return eshopAssembler.transformToListOfEshopListDto(productInEshopDao.getEshopsWithProduct(productId));
    }

    @Override
    public List<EshopListDto> getEshopsWithoutProduct(Long productId) {
        val.notNull(productId, "productId is null");
        return eshopAssembler.transformToListOfEshopListDto(productInEshopDao.getEshopsWithoutProduct(productId));
    }

    @Override
    public List<ProductInEshopListDto> getProductsInEshopByProductId(Long productId) {
        val.notNull(productId, "productId is null");
        return productInEshopAssembler.transformToListOfProductInEshopListDto(productInEshopDao.getProductsInEshopByProductId(productId));
    }

    @Override
    public List<ProductInEshopDto> getProductsInEshopForDownloaderByProductId(Long productId) {
        val.notNull(productId, "productId is null");
        return productInEshopAssembler.transformToListOfProductInEshopDto(productInEshopDao.getProductsInEshopByProductId(productId));
    }

    @Override
    public List<ProductInEshopCustomListDto> getProductsInEshop(Long eshopId) {
        val.notNull(eshopId, "eshopId is null");
        return productInEshopAssembler.transformToListOfProductInEshopCustomListDto(productInEshopDao.getProductsInEshop(eshopId));
    }
}
