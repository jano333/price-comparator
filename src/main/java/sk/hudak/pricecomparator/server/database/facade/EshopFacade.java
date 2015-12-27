package sk.hudak.pricecomparator.server.database.facade;

import sk.hudak.jef.server.JefFacade;
import sk.hudak.pricecomparator.middle.api.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.api.to.EshopCreateDto;
import sk.hudak.pricecomparator.server.database.dao.EshopDao;
import sk.hudak.pricecomparator.server.database.model.EshopEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 27. 12. 2015.
 */
@Named
public class EshopFacade extends JefFacade {

    @Inject
    private EshopDao eshopDao;

    public Long createEshop(EshopCreateDto dto) {
        val.notNull(dto, "dto is null");
        val.notNullAndNotEmpty(dto.getName(), "name is null or empty");
        val.notNullAndNotEmpty(dto.getParserClassName(), "parser class name is null or empty");

        //TODO kontorola na unikatnost name a persar class
        if (eshopDao.existWithName(dto.getName())) {
            throw new PriceComparatorException("Eshop s nazvom " + dto.getName() + " uz existuje.");
        }

        if (eshopDao.existWithParserClassName(dto.getParserClassName())) {
            throw new PriceComparatorException("Eshop s danym parser class name " + dto.getParserClassName() + " uz existuje.");
        }


        EshopEntity eshop = new EshopEntity();
        eshop.setName(dto.getName());
        eshop.setParserClassName(dto.getParserClassName());
        eshop.setHomePage(dto.getHomePage());

        return eshopDao.create(eshop);
    }
}
