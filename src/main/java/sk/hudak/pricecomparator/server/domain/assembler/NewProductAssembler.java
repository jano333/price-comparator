package sk.hudak.pricecomparator.server.domain.assembler;

import sk.hudak.jef.paging.PageData;
import sk.hudak.pricecomparator.middle.to.NewProductListDto;
import sk.hudak.pricecomparator.server.domain.model.NewProductEntity;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hudak on 31.12.2016.
 */
@Named
public class NewProductAssembler {


    public PageData<NewProductListDto> transformToPageDataOfNewProductListDto(PageData<NewProductEntity> newProducts) {

        List<NewProductListDto> records = new ArrayList<>(newProducts.getResult().size());
        for (NewProductEntity entity : newProducts.getResult()) {
            records.add(transformToNewProductListDto(entity));

        }
        return new PageData<>(newProducts.getAllRecordsCount(), newProducts.getPaging(), records);
    }

    public NewProductListDto transformToNewProductListDto(NewProductEntity entity) {
        if (entity == null) {
            return null;
        }
        NewProductListDto result = new NewProductListDto();
        result.setId(entity.getId());
        result.setEshopType(entity.getEshopType());
        result.setProductName(entity.getProductName());
        result.setProductPictureUrl(entity.getProductPictureUrl());
        result.setStatus(entity.getStatus());
        result.setProductUrl(entity.getProductUrl());
        return result;
    }
}
