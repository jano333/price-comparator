package sk.hudak.pricecomparator.server.database.facade;

import sk.hudak.jef.server.JefFacade;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopCreateDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopPriceUpdateDto;
import sk.hudak.pricecomparator.server.database.dao.EshopDao;
import sk.hudak.pricecomparator.server.database.dao.ProductDao;
import sk.hudak.pricecomparator.server.database.dao.ProductInEshopDao;
import sk.hudak.pricecomparator.server.database.model.ProductInEshopEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;

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
        val.notNull(createDto, "createDto is null");
        val.notNull(createDto.getEshopId(), "eshopId is null");
        val.notNull(createDto.getProductId(), "productId is null");
        val.notNullAndNotEmpty(createDto.getEshopProductPage(), "eshop product page is null or empty");
        // TODO ostatne a dlzky validovat

        ProductInEshopEntity entity = new ProductInEshopEntity();
        entity.setEshop(eshopDao.readMandatory(createDto.getEshopId()));
        entity.setProduct(productDao.readMandatory(createDto.getProductId()));
        entity.setProductPageInEshop(createDto.getEshopProductPage());

        return productInEshopDao.create(entity);
    }

    public void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto) {
        val.notNull(updateDto, "updateDto is null");
        val.notNull(updateDto.getId(), "id is null");

        ProductInEshopEntity entity = productInEshopDao.readMandatory(updateDto.getId());
        entity.setProductAction(updateDto.getProductAction());
        entity.setActionValidTo(updateDto.getActionValidTo());
        entity.setPriceForUnit(updateDto.getPriceForUnit());
        entity.setPriceForPackage(updateDto.getPriceForPackage());
        entity.setPriceForOneItemInPackage(updateDto.getPriceForOneItemInPackage());
        //TODO ako jeden
        entity.setLastUpdatedPrice(new Date());

        productInEshopDao.update(entity);
    }


    public ProductInEshopEntity findProductForPriceUpdate(EshopType eshopType) {
        val.notNull(eshopType, "eshopType is null");

        //TODO dat do kongigu neako ze co sa ma updatovat !!!
        // moznosti:
        // vsetko co nebolo dnes aktualizovane
        // vsetko starsie ako 24 hodin
        // ??


        // maxmalny pocet hodin, kolko moze byt cena neaktualna
//        Date maxOlderDate = DateUtils.minusHours(new Date(), ServerConfig.getMaxOldProductPriceForUpdateInOurs());
//        System.out.println("finding first product older than " + maxOlderDate);
//        return productInEshopDao.findProductForPriceUpdate(eshopType, maxOlderDate);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        Date maxOlderDate = cal.getTime();

        System.out.println("finding first product older than " + maxOlderDate);
        return productInEshopDao.findProductForPriceUpdate(eshopType, maxOlderDate);


    }
}
