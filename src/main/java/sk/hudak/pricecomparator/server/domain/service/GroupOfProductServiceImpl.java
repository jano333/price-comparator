package sk.hudak.pricecomparator.server.domain.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.service.GroupOfProductService;
import sk.hudak.pricecomparator.middle.to.ProductPriceInGroupFindDto;
import sk.hudak.pricecomparator.middle.to.group.*;
import sk.hudak.pricecomparator.middle.to.product.ProductListDto;
import sk.hudak.pricecomparator.middle.to.productineshop.ProductInEshopPriceResultListDto;
import sk.hudak.pricecomparator.server.domain.assembler.GroupOfProductAssembler;
import sk.hudak.pricecomparator.server.domain.assembler.ProductAssembler;
import sk.hudak.pricecomparator.server.domain.assembler.ProductInEshopAssembler;
import sk.hudak.pricecomparator.server.domain.dao.GroupOfProductDao;
import sk.hudak.pricecomparator.server.domain.facade.GroupOfProductFacade;
import sk.hudak.pricecomparator.server.domain.model.GroupOfProductEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductInEshopEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

/**
 * Created by jan on 1. 1. 2016.
 */
@Named
public class GroupOfProductServiceImpl implements GroupOfProductService {

    @Inject
    private GroupOfProductFacade groupOfProductFacade;

    @Inject
    private GroupOfProductDao groupOfProductDao;

    @Inject
    private GroupOfProductAssembler groupOfProductAssembler;

    @Inject
    private ProductAssembler productAssembler;

    @Inject
    private ProductInEshopAssembler productInEshopAssembler;


    @Override
    public PageList<GroupOfProductListDto> findGroupOfProductByFilter(GroupOfProductFindDto filter) {
        PageList<GroupOfProductEntity> allGroupsOfProducts = groupOfProductDao.findGroupOfProduct(filter);
        return groupOfProductAssembler.transformToPageListOfGroupOfProductListDto(allGroupsOfProducts);
    }

    @Override
    public PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(ProductPriceInGroupFindDto filter) {
        PageList<ProductInEshopEntity> productInEshopEntities = groupOfProductDao.findPriceInfoInEshopsForGroup(filter);
        return productInEshopAssembler.transformToPageListOfProductInEshopPriceResultListDto(productInEshopEntities);
    }

    @Override
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        return groupOfProductFacade.createGroupOfProduct(dto);
    }

    @Override
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupId) {
        groupOfProductFacade.addProductsToGroup(productsIdToBeAdded, groupId);
    }

    @Override
    public GroupOfProductDto findGroupOfProductById(Long groupId) {
        GroupOfProductEntity groupOfProductEntity = groupOfProductDao.readMandatory(groupId);
        return groupOfProductAssembler.transformToGroupOfProductDto(groupOfProductEntity);
    }

    @Override
    public List<GroupOfProductListDto> findAllGroupsOfProducts() {
        List<GroupOfProductEntity> allGroupsOfProducts = groupOfProductDao.findAllGroupsOfProducts();
        return groupOfProductAssembler.transformToGroupOfProductListDto(allGroupsOfProducts);
    }

    @Override
    public List<ProductListDto> findProductsInGroup(Long groupId) {
        List<ProductEntity> productsInGroup = groupOfProductDao.findProductsInGroup(groupId);
        return productAssembler.transformToListOfProductListDto(productsInGroup);
    }

    @Override
    public List<ProductListDto> findProductsNotInGroup(Long groupId) {
        List<ProductEntity> productsNotInGroup = groupOfProductDao.findProductsNotInGroup(groupId);
        return productAssembler.transformToListOfProductListDto(productsNotInGroup);
    }

    @Override
    public List<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForGroup(Long groupId) {
        List<ProductInEshopEntity> productInEshopEntities = groupOfProductDao.findPriceInfoInEshopsForGroup(groupId);
        return productInEshopAssembler.transformToListOfProductInEshopEntity(productInEshopEntities);
    }

    @Override
    public List<GroupIdNameDto> findAllProductGroupSelection() {
        return groupOfProductAssembler.transformToListOfGroupIdNameDto(groupOfProductDao.findAllGroupsOfProducts());
    }

    @Override
    public PageList<ProductListDto> findProductsInGroup(GroupOfProductFindDto filter) {
        PageList<ProductEntity> productsInGroup = groupOfProductDao.findProductsInGroup(filter);
        return productAssembler.transformToPageListOfProductListDto(productsInGroup);
    }


}
