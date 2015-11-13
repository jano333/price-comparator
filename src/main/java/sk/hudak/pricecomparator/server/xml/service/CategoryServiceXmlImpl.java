package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.service.CategoryService;
import sk.hudak.pricecomparator.middle.api.to.CategoryCreateDto;
import sk.hudak.pricecomparator.server.xml.model.CategoryXmlEntity;

/**
 * Created by jan on 14. 10. 2015.
 */
public class CategoryServiceXmlImpl extends AbstracServiceXmlImpl implements CategoryService {

    @Override
    public Long createCategory(CategoryCreateDto dto) {
        // TODO validacia

        CategoryXmlEntity categoryXmlEntity = new CategoryXmlEntity();
        categoryXmlEntity.setId(generateNewId());
        categoryXmlEntity.setName(dto.getName());
        categoryXmlEntity.setParentId(dto.getCategoryParentId());

        saveXmlEntity(categoryXmlEntity);

        return categoryXmlEntity.getId();
    }

}
