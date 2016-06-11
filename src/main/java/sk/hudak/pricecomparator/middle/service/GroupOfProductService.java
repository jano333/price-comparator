package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.to.*;

import java.util.List;
import java.util.Set;

/**
 * Created by jan on 3. 11. 2015.
 */
public interface GroupOfProductService {

    PageList<GroupOfProductListDto> findGroupOfProductByFilter(GroupOfProductFindDto filter);

    PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(ProductPriceInGroupFindDto filter);

    PageList<ProductListDto> findProductsInGroup(GroupOfProductFindDto filter);

    List<GroupIdNameDto> findAllProductGroupSelection();

    /**
     * Zoznam vsetkych produktov v skupine podla id skupiny <code>groupOfProductId</code>
     *
     * @param groupOfProductId
     * @return
     */
    List<ProductListDto> findProductsInGroup(Long groupOfProductId);

    // TODO prejst

    Long createGroupOfProduct(GroupOfProductCreateDto dto);

    GroupOfProductDto findGroupOfProductById(Long groupOfProductId);

    List<GroupOfProductListDto> findAllGroupsOfProducts();

    List<ProductListDto> findProductsNotInGroup(Long groupOfProductId);

    void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId);

    @Deprecated
    List<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(Long groupId);
}
