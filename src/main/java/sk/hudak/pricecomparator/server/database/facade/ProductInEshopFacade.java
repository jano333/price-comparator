package sk.hudak.pricecomparator.server.database.facade;

import sk.hudak.jef.server.JefFacade;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopCreateDto;
import sk.hudak.pricecomparator.server.database.dao.EshopDao;
import sk.hudak.pricecomparator.server.database.dao.ProductDao;
import sk.hudak.pricecomparator.server.database.dao.ProductInEshopDao;
import sk.hudak.pricecomparator.server.database.model.ProductInEshopEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 30. 12. 2015.
 */
@Named
public class ProductInEshopFacade extends JefFacade {

    @Inject
    private ProductInEshopDao productInEshopDao;

    @Inject
    private EshopDao eshopDao;

    @Inject
    private ProductDao productDao;

    public Long createProductInEshop(ProductInEshopCreateDto createDto) {
        validateDto(createDto);

        ProductInEshopEntity entity = new ProductInEshopEntity();
        entity.setEshop(eshopDao.readMandatory(createDto.getEshopId()));
        entity.setProduct(productDao.readMandatory(createDto.getProductId()));
        entity.setProductPageInEshop(createDto.getEshopProductPage());

        return productInEshopDao.create(entity);
    }

    private void validateDto(ProductInEshopCreateDto createDto) {
        val.notNull(createDto, "createDto is null");
        val.notNull(createDto.getEshopId(), "eshopId is null");
        val.notNull(createDto.getProductId(), "productId is null");
        val.notNullAndNotEmpty(createDto.getEshopProductPage(), "eshop product page is null or empty");
        // TODO

    }
}
