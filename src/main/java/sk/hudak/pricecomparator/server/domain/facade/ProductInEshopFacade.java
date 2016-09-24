package sk.hudak.pricecomparator.server.domain.facade;

import sk.hudak.jef.JefFacade;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.ProductInEshopCreateDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceUpdateDto;
import sk.hudak.pricecomparator.server.domain.dao.EshopDao;
import sk.hudak.pricecomparator.server.domain.dao.ProductDao;
import sk.hudak.pricecomparator.server.domain.dao.ProductInEshopDao;
import sk.hudak.pricecomparator.server.domain.model.EshopEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductInEshopEntity;

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
            //FIXME err kluc
            throw new PriceComparatorBusinesException("Produkt s danou URL uz existuje.");
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

    public void updateProductInEshopPrice(ProductInEshopPriceUpdateDto priceUpdateDto) {
        val.notNull(priceUpdateDto, "priceUpdateDto is null");
        val.notNull(priceUpdateDto.getId(), "id is null");

        //TODO validacie

        ProductInEshopEntity entity = productInEshopDao.readMandatory(priceUpdateDto.getId());
        entity.setProductAction(priceUpdateDto.getProductAction());
        entity.setActionValidTo(priceUpdateDto.getActionValidTo());
        entity.setPriceForUnit(priceUpdateDto.getPriceForUnit());
        entity.setPriceForPackage(priceUpdateDto.getPriceForPackage());
        entity.setPriceForOneItemInPackage(priceUpdateDto.getPriceForOneItemInPackage());
        //TODO ako jeden
        entity.setLastUpdatedPrice(new Date());
        // nastavenie best price
        if (notExistBestPrice(entity)) {
            entity.setBestPrice(priceUpdateDto.getPriceForPackage());
        } else {
            if (isBestPriceGreaterThanActualPriceForPackage(priceUpdateDto, entity)) {
                // nastav lepsiu cenu
                entity.setBestPrice(priceUpdateDto.getPriceForPackage());
            }
        }

        productInEshopDao.update(entity);
    }

    private boolean isBestPriceGreaterThanActualPriceForPackage(ProductInEshopPriceUpdateDto priceUpdateDto, ProductInEshopEntity entity) {
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
}
