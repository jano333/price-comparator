package sk.hudak.pricecomparator.server.facade;

import sk.hudak.jef.JefFacade;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorErrorCodes;
import sk.hudak.pricecomparator.middle.to.EshopCreateDto;
import sk.hudak.pricecomparator.server.dao.EshopDao;
import sk.hudak.pricecomparator.server.model.EshopEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 27. 12. 2015.
 */
@Named
public class EshopFacade extends JefFacade {

    @Inject
    private EshopDao eshopDao;

    public Long createEshop(EshopCreateDto createDto) throws PriceComparatorBusinesException {
        val.notNull(createDto, "dto is null");
        // type
        val.notNull(createDto.getEshopType(), "eshopType is null");
        if (eshopDao.existWithType(createDto.getEshopType())) {
            throw new PriceComparatorBusinesException(PriceComparatorErrorCodes.ERR_ESHOP_TYPE_ALLREADY_EXIST, createDto.getEshopType().name());
        }
        // nazov
        val.notNullAndNotEmpty(createDto.getName(), "name is null or empty");
        val.maxLength255(createDto.getName(), "name of eshop is longer than 255 chars");
        if (eshopDao.existWithName(createDto.getName())) {
            throw new PriceComparatorBusinesException(PriceComparatorErrorCodes.ERR_ESHOP_NAME_ALLREADY_EXIST, createDto.getName());
        }
        // home page
        val.notNullAndNotEmpty(createDto.getHomePage(), "homePage is null or empty");
        val.maxLength255(createDto.getHomePage(), "home page of eshop is longer than 255 chars");

        // kontrola na unikatnost home page nemoze byt, lebo dohliadac ma viac eshopov s rovnakou home page

        EshopEntity eshop = new EshopEntity();
        eshop.setName(createDto.getName());
        eshop.setHomePage(createDto.getHomePage());
        eshop.setEshopType(createDto.getEshopType());

        return eshopDao.create(eshop);
    }

}
