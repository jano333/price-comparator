package sk.hudak.pricecomparator.server.domain.assembler;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.to.GroupIdNameDto;
import sk.hudak.pricecomparator.middle.to.GroupOfProductDto;
import sk.hudak.pricecomparator.middle.to.GroupOfProductListDto;
import sk.hudak.pricecomparator.server.domain.model.GroupOfProductEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductEntity;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 1. 1. 2016.
 */
@Named
public class GroupOfProductAssembler {
    public GroupOfProductDto transformToGroupOfProductDto(GroupOfProductEntity groupOfProductEntity) {
        if (groupOfProductEntity == null) {
            return null;
        }
        GroupOfProductDto dto = new GroupOfProductDto();
        dto.setId(groupOfProductEntity.getId());
        dto.setName(groupOfProductEntity.getName());
        for (ProductEntity productEntity : groupOfProductEntity.getProducts()) {
            dto.getProductIds().add(productEntity.getId());
        }
        return dto;
    }

    public List<GroupOfProductListDto> transformToGroupOfProductListDto(List<GroupOfProductEntity> allGroupsOfProducts) {
        if (allGroupsOfProducts == null) {
            return null;
        }
        List<GroupOfProductListDto> result = new ArrayList<>(allGroupsOfProducts.size());
        for (GroupOfProductEntity entity : allGroupsOfProducts) {
            result.add(transformToGroupOfProductListDto(entity));
        }
        return result;
    }

    public GroupOfProductListDto transformToGroupOfProductListDto(GroupOfProductEntity entity) {
        if (entity == null) {
            return null;
        }
        GroupOfProductListDto result = new GroupOfProductListDto();
        result.setId(entity.getId());
        result.setName(entity.getName());

        return result;
    }

    public List<GroupIdNameDto> transformToListOfGroupIdNameDto(List<GroupOfProductEntity> allGroupsOfProducts) {
        if (allGroupsOfProducts == null) {
            return null;
        }
        List<GroupIdNameDto> result = new ArrayList<>(allGroupsOfProducts.size());
        for (GroupOfProductEntity groupOfProductEntity : allGroupsOfProducts) {
            result.add(transformToGroupIdNameDto(groupOfProductEntity));
        }
        return result;
    }



    private GroupIdNameDto transformToGroupIdNameDto(GroupOfProductEntity groupOfProductEntity) {
        if (groupOfProductEntity == null) {
            return null;
        }
        GroupIdNameDto result = new GroupIdNameDto();
        result.setId(groupOfProductEntity.getId());
        result.setName(groupOfProductEntity.getName());
        return result;
    }

    public PageList<GroupOfProductListDto> transformToPageListOfGroupOfProductListDto(PageList<GroupOfProductEntity> allGroupsOfProducts) {
        List<GroupOfProductListDto> versions = new ArrayList<>(allGroupsOfProducts.getEntries().size());
        for (GroupOfProductEntity entity : allGroupsOfProducts.getEntries()) {
            versions.add(transformToGroupOfProductListDto(entity));
        }
        return new PageList<>(versions, allGroupsOfProducts.getCurrentPage(), allGroupsOfProducts.getAllPageCount());
    }
}
