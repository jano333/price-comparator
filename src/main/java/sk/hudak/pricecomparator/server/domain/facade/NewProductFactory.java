package sk.hudak.pricecomparator.server.domain.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.jef.JefFacade;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.domain.dao.NewProductDao;
import sk.hudak.pricecomparator.server.domain.dao.ProductInEshopDao;
import sk.hudak.pricecomparator.server.domain.model.NewProductEntity;
import sk.hudak.pricecomparator.server.to.NewProductCreateDto;
import sk.hudak.pricecomparator.server.to.NewProductStatus;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 29. 12. 2016.
 */
@Named
public class NewProductFactory extends JefFacade {

    private static Logger logger = LoggerFactory.getLogger(NewProductFactory.class);

    @Inject
    private NewProductDao newProductDao;

    @Inject
    private ProductInEshopDao productInEshopDao;

    public void createIfNotExistYet(NewProductCreateDto createDto, EshopType eshopType) {

        //ak existuje ako nespracovany, koncim
        boolean alreadyExist = newProductDao.exist(createDto.getProductUrl());
        if (alreadyExist) {
            logger.debug("product URL {} allready added", createDto.getProductUrl());
            return;
        }
        //ak existuje ako spracovany, koncim
        alreadyExist = productInEshopDao.existProductWithGivenUrl(createDto.getProductUrl());
        if (alreadyExist) {
            logger.debug("product URL {} exist", createDto.getProductUrl());
            return;
        }

        // este overim bez/s poslednym lomitkom lebo sa stavalo ze ja som s lomitkom a vstupom
        // bolo bez lomitka...
        if (createDto.getProductUrl().endsWith("/")) {
            alreadyExist = productInEshopDao.existProductWithGivenUrl(createDto.getProductUrl().substring(0, createDto.getProductUrl().length() - 1));
            if (alreadyExist) {
                logger.debug("product URL {} exist without last /", createDto.getProductUrl());
                return;
            }
        } else {
            alreadyExist = productInEshopDao.existProductWithGivenUrl(createDto.getProductUrl() + "/");
            if (alreadyExist) {
                logger.debug("product URL {} exist with last /", createDto.getProductUrl());
                return;
            }
        }

        NewProductEntity entity = new NewProductEntity();
        entity.setStatus(NewProductStatus.NEW);
        entity.setEshopType(eshopType);
        entity.setProductName(createDto.getProductName());
        entity.setProductUrl(createDto.getProductUrl());
        entity.setProductPictureUrl(createDto.getProductPictureUrl());
        newProductDao.create(entity);
        logger.info("new product added successfully, URL {}", createDto.getProductUrl());
    }
}
