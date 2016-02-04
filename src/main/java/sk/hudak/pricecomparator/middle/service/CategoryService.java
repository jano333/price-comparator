package sk.hudak.pricecomparator.middle.service;

import sk.hudak.pricecomparator.middle.to.CategoryCreateDto;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface CategoryService {

    Long createCategory(CategoryCreateDto dto);

}
