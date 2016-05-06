package sk.hudak.pricecomparator.server.facade;

import sk.hudak.jef.JefFacade;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
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

    public Long createEshop(EshopCreateDto createDto) {
        val.notNull(createDto, "dto is null");
        val.notNull(createDto.getEshopType(), "eshopTzpe is null");
        val.notNullAndNotEmpty(createDto.getName(), "name is null or empty");
        //TODO validacia na max dlzku 255 ?
        val.notNullAndNotEmpty(createDto.getHomePage(), "homePage is null or empty");
        //TODO validacia na max dlzku 255 ?

        if (eshopDao.existWithName(createDto.getName())){
            throw new PriceComparatorException("Eshop s nazvom " + createDto.getName() + " uz existuje.");
        }

        //TODO kontrola na unikatnost home page
        //TODO kontrola na unikatnost eshop type

        EshopEntity eshop = new EshopEntity();
        eshop.setName(createDto.getName());
        eshop.setHomePage(createDto.getHomePage());
        eshop.setEshopType(createDto.getEshopType());

        return eshopDao.create(eshop);
    }

}
