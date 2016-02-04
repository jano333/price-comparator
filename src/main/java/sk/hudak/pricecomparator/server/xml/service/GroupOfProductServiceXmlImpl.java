package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.service.GroupOfProductService;
import sk.hudak.pricecomparator.middle.api.to.*;
import sk.hudak.pricecomparator.server.xml.model.GroupOfProductXmlEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by jan on 3. 11. 2015.
 */
public class GroupOfProductServiceXmlImpl extends AbstracServiceXmlImpl implements GroupOfProductService {

    @Override
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        return null;
    }

    @Override
    public GroupOfProductDto getGroupOfProduct(Long groupOfProductId) {
        return null;
    }

    @Override
    public List<GroupOfProductListDto> findAllGroupsOfProducts() {
        return null;
    }

    @Override
    public List<ProductListDto> findProductsInGroup(Long groupOfProductId) {
        return null;
    }

    @Override
    public List<ProductListDto> findProductsNotInGroup(Long groupOfProductId) {
        return null;
    }

    @Override
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {

    }

    @Override
    public List<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(Long groupId) {
        return null;
    }


    //TODO dat do bazovej triedy !!! a parametrizovat ak sa da
    private GroupOfProductXmlEntity readMandatory(Long groupOfProductId) {
        return null;
    }

}
