package sk.hudak.pricecomparator.server.facade;

import sk.hudak.jef.JefFacade;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.to.CategoryCreateDto;
import sk.hudak.pricecomparator.server.dao.CategoryDao;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 27. 12. 2015.
 */
@Named
public class CategoryFactory extends JefFacade {

    @Inject
    private CategoryDao categoryDao;

    public Long createCategory(CategoryCreateDto dto) {
        val.notNull(dto, "dto is null");
        val.notNullAndNotEmpty(dto.getName(), "name is null or empty");

        //TODO kontorola na unikatnost name a persar class
        if (categoryDao.existWithName(dto.getName())) {
            throw new PriceComparatorException("Categoria s nazvom " + dto.getName() + " uz existuje.");
        }
        return null;
    }
}
