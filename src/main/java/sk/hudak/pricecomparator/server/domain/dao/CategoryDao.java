package sk.hudak.pricecomparator.server.domain.dao;

import sk.hudak.jef.JefDao;
import sk.hudak.pricecomparator.server.domain.model.CategoryEntity;

import javax.inject.Named;

/**
 * Created by jan on 27. 12. 2015.
 */
@Named
public class CategoryDao extends JefDao<CategoryEntity> {

    @Override
    public CategoryEntity readMandatory(Long id) {
        return readMandatory(id, CategoryEntity.class);
    }

    public boolean existWithName(String name) {
        //TODO
        return false;
    }
}
