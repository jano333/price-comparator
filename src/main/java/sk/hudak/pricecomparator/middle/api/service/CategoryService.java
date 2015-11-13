package sk.hudak.pricecomparator.middle.api.service;

import sk.hudak.pricecomparator.middle.api.to.CategoryCreateDto;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface CategoryService {

    Long createCategory(CategoryCreateDto dto);

}
