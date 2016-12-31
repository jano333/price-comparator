package sk.hudak.pricecomparator.server.domain.facade;

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

    @Inject
    private NewProductDao newProductDao;

    @Inject
    private ProductInEshopDao productInEshopDao;

    public void createIfNotExistYet(NewProductCreateDto newProductCreateDto, EshopType eshopType) {

        //ak existuje ako nespracovany, koncim
        boolean alreadyExist = newProductDao.exist(newProductCreateDto.getProductUrl());
        if (alreadyExist) {
            return;
        }
        //ak existuje ako spracovany, koncim
        alreadyExist = productInEshopDao.existProductWithGivenUrl(newProductCreateDto.getProductUrl());
        if (alreadyExist) {
            return;
        }

        NewProductEntity entity = new NewProductEntity();
        entity.setStatus(NewProductStatus.NEW);
        //TODO tu pokracovat...
//        entity.setProductUrl();

        //TODO

    }
}
