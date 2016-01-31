package sk.hudak.pricecomparator.middle.api.service;

import sk.hudak.pricecomparator.middle.api.to.*;

import java.util.List;
import java.util.Set;

/**
 * Created by jan on 3. 11. 2015.
 */
public interface GroupOfProductService {

    Long createGroupOfProduct(GroupOfProductCreateDto dto);

    GroupOfProductDto getGroupOfProduct(Long groupOfProductId);

    List<GroupOfProductListDto> findAllGroupsOfProducts();

    List<ProductListDto> findProductsInGroup(Long groupOfProductId);

    List<ProductListDto> findProductsNotInGroup(Long groupOfProductId);

    void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId);

    List<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(Long groupId);
}
