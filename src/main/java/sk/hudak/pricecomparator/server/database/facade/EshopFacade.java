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

    public Long createEshop(EshopCreateDto createDto) {
        validateDto(createDto);

        EshopEntity eshop = new EshopEntity();
        eshop.setName(createDto.getName());
        eshop.setParserClassName(createDto.getParserClassName());
        eshop.setHomePage(createDto.getHomePage());

        return eshopDao.create(eshop);
    }

    private void validateDto(EshopCreateDto dto) {
        val.notNull(dto, "dto is null");
        val.notNullAndNotEmpty(dto.getName(), "name is null or empty");
        //TODO validacia na max dlzku 255 ?
        val.notNullAndNotEmpty(dto.getParserClassName(), "parser class name is null or empty");
        //TODO validacia na max dlzku 255 ?

        if (eshopDao.existWithName(dto.getName())) {
            throw new PriceComparatorException("Eshop s nazvom " + dto.getName() + " uz existuje.");
        }
        if (eshopDao.existWithParserClassName(dto.getParserClassName())) {
            throw new PriceComparatorException("Eshop s danym parser class name " + dto.getParserClassName() + " uz existuje.");
        }
    }
}
