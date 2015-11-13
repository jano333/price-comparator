package sk.hudak.pricecomparator.middle.api.service;

import sk.hudak.pricecomparator.middle.api.to.GroupOfProductCreateDto;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductDto;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductListDto;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;

import java.util.List;
import java.util.Set;

/**
 * Created by jan on 3. 11. 2015.
 */
public interface GroupOfProductService {

    Long createGroupOfProduct(GroupOfProductCreateDto dto);

    GroupOfProductDto getGroupOfProduct(Long groupOfProductId);

    List<GroupOfProductListDto> findAllGroupsOfProducts();

    List<ProductListDto> getProductsInGroup(Long groupOfProductId);

    List<ProductListDto> getProductsNotInGroup(Long groupOfProductId);

    void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId);
}
