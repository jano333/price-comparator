package sk.hudak.pricecomparator.server.database.service;

import sk.hudak.pricecomparator.middle.service.CategoryService;
import sk.hudak.pricecomparator.middle.to.CategoryCreateDto;
import sk.hudak.pricecomparator.server.facade.CategoryFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 27. 12. 2015.
 */
@Named
public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryFactory categoryFactory;

    @Override
    public Long createCategory(CategoryCreateDto dto) {
        return categoryFactory.createCategory(dto);
    }
}
