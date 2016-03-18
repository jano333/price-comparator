package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.to.*;

import java.util.List;
import java.util.Set;

/**
 * Created by jan on 3. 11. 2015.
 */
public interface GroupOfProductService {

    PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(ProductPriceInGroupFindDto filter);

    // TODO prejst

    Long createGroupOfProduct(GroupOfProductCreateDto dto);

    GroupOfProductDto getGroupOfProduct(Long groupOfProductId);

    List<GroupOfProductListDto> findAllGroupsOfProducts();

    List<ProductListDto> findProductsInGroup(Long groupOfProductId);

    List<ProductListDto> findProductsNotInGroup(Long groupOfProductId);

    void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId);

    @Deprecated
    List<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(Long groupId);

    List<GroupIdNameDto> findAllProductGroupSelection();
}
