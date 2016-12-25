package sk.hudak.pricecomparator.server.domain.facade;

import sk.hudak.jef.JefFacade;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.ProductInEshopCreateDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopInfoUpdateDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopUpdateDto;
import sk.hudak.pricecomparator.middle.to.internal.ProductInEshopUpdateStatus;
import sk.hudak.pricecomparator.server.domain.dao.EshopDao;
import sk.hudak.pricecomparator.server.domain.dao.ProductDao;
import sk.hudak.pricecomparator.server.domain.dao.ProductInEshopDao;
import sk.hudak.pricecomparator.server.domain.model.EshopEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductInEshopEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static sk.hudak.pricecomparator.middle.exeption.PriceComparatorErrorCodes.ERR_PRODUCT_WITH_URL_ALLREADY_EXIST;

/**
 * Fasade for entity {@link ProductInEshopEntity}.
 * <p>
 * Created by jan on 30. 12. 2015.
 */
@Named
public class ProductInEshopFacade extends JefFacade {

    @Inject
    private EshopDao eshopDao;
    @Inject
    private ProductDao productDao;
    @Inject
    private ProductInEshopDao productInEshopDao;

    public Long createProductInEshop(ProductInEshopCreateDto createDto) throws PriceComparatorBusinesException {
        val.notNull(createDto, "createDto is null");
        val.notNull(createDto.getEshopId(), ProductInEshopCreateDto.AT_ESHOP_ID + " is null");
        val.notNull(createDto.getProductId(), ProductInEshopCreateDto.AT_PRODUCT_ID + " is null");
        val.notNullAndNotEmpty(createDto.getEshopProductPage(), ProductInEshopCreateDto.AT_ESHOP_PRODUCT_PAGE + " is null or empty");
        // kontrola, ci produkt v eshope s takou url uz neexistuje
        if (productInEshopDao.existProductWithGivenUrl(createDto.getEshopProductPage())) {
            throw new PriceComparatorBusinesException(ERR_PRODUCT_WITH_URL_ALLREADY_EXIST, createDto.getEshopProductPage());
        }
        // kontola, ci kombinacia produkt <-> eshop uz neexistuje
        if (productInEshopDao.existProductInEshop(createDto.getEshopId(), createDto.getProductId())) {
            //FIXME err kluc
            throw new PriceComparatorBusinesException("Produkt v danom eshope uz je pridany.");
        }
        // kontrola, ci URL je pre dany eshop
        EshopEntity eshop = eshopDao.readMandatory(createDto.getEshopId());
        if (!createDto.getEshopProductPage().startsWith(eshop.getHomePage())) {
            //FIXME err kluc
//            throw new PriceComparatorBusinesException("Dana URL produktu nie je pre dany eshop.");
        }
        // vytvorenie
        ProductInEshopEntity entity = new ProductInEshopEntity();
        entity.setEshop(eshop);
        entity.setProduct(productDao.readMandatory(createDto.getProductId()));
        entity.setProductPageInEshop(createDto.getEshopProductPage());
        // ulozenie do DB
        return productInEshopDao.create(entity);
    }

    public void updateProductInEshop(ProductInEshopUpdateDto updateDto) {
        val.notNull(updateDto, "updateDto is null");
        val.notNull(updateDto.getEshopId(), ProductInEshopUpdateDto.AT_ESHOP_ID + " is null");
        val.notNull(updateDto.getProductId(), ProductInEshopUpdateDto.AT_PRODUCT_ID + " is null");
        val.notNullAndNotEmpty(updateDto.getEshopProductPage(), ProductInEshopUpdateDto.AT_ESHOP_PRODUCT_PAGE + " is null or empty");

        //TODO validacie take co su v create plus este dalsie...

        ProductInEshopEntity entity = productInEshopDao.readMandatory(updateDto.getId());
        entity.setEshop(eshopDao.readMandatory(updateDto.getEshopId()));
        entity.setProduct(productDao.readMandatory(updateDto.getProductId()));
        entity.setProductPageInEshop(updateDto.getEshopProductPage());
    }

    public void deleteProductInEshop(Long productInEshopId) {
        ProductInEshopEntity entity = productInEshopDao.readMandatory(productInEshopId);
        productInEshopDao.delete(entity);
    }

    public void updateInfoOfProductInEshop(ProductInEshopInfoUpdateDto priceUpdateDto) {
        val.notNull(priceUpdateDto, "priceUpdateDto is null");
        val.notNull(priceUpdateDto.getId(), "id is null");

        //TODO validacie

        ProductInEshopEntity entity = productInEshopDao.readMandatory(priceUpdateDto.getId());
        entity.setPriceForUnit(priceUpdateDto.getPriceForUnit());
        entity.setPriceForPackage(priceUpdateDto.getPriceForPackage());
        entity.setPriceForOneItemInPackage(priceUpdateDto.getPriceForOneItemInPackage());
        entity.setLastUpdatedPrice(new Date());
        entity.setUpdateStatus(ProductInEshopUpdateStatus.OK);
        //TODO ako jeden
        // nastavenie best price
        if (notExistBestPrice(entity)) {
            entity.setBestPrice(priceUpdateDto.getPriceForPackage());
        } else {
            if (isBestPriceGreaterThanActualPriceForPackage(priceUpdateDto, entity)) {
                // nastav lepsiu cenu
                entity.setBestPrice(priceUpdateDto.getPriceForPackage());
            }
        }

        entity.setProductAction(priceUpdateDto.getProductAction());
        entity.setActionValidTo(priceUpdateDto.getActionValidTo());
        entity.setProductNameInEhop(priceUpdateDto.getProductName());
        entity.setProductPictureUrl(priceUpdateDto.getPictureUrl());
        productInEshopDao.update(entity);
    }

    private boolean isBestPriceGreaterThanActualPriceForPackage(ProductInEshopInfoUpdateDto priceUpdateDto, ProductInEshopEntity entity) {
        return entity.getBestPrice().compareTo(priceUpdateDto.getPriceForPackage()) > 0;
    }

    private boolean notExistBestPrice(ProductInEshopEntity entity) {
        return entity.getBestPrice() == null;
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

    //TODO zmenit nazov metody lebo sa voal ked sa upstate npodaril
    public void changeUpdateStatus(Long productInEshopId, ProductInEshopUpdateStatus updateStatus) {
        ProductInEshopEntity productInEshopEntity = productInEshopDao.readMandatory(productInEshopId);
        productInEshopEntity.setLastModified(new Date());
        //musi tyu byt lebo inak stale ten isty produkt vytahuje ze ho chce upatovat
        productInEshopEntity.setLastUpdatedPrice(new Date());
        productInEshopEntity.setUpdateStatus(updateStatus);
        productInEshopEntity.setPriceForOneItemInPackage(new BigDecimal(-1));
        productInEshopEntity.setPriceForPackage(new BigDecimal(-1));
        productInEshopEntity.setPriceForUnit(new BigDecimal(-1));
        productInEshopDao.update(productInEshopEntity);
    }

    public void markPictureOfProductInEshopAsDownloaded(Long productInEshopId) {
        ProductInEshopEntity entity = productInEshopDao.readMandatory(productInEshopId);
        entity.setPictureExist(true);
        productInEshopDao.update(entity);
    }
}
