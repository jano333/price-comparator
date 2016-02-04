package sk.hudak.pricecomparator.server.database.service;

import sk.hudak.pricecomparator.middle.service.GroupOfProductService;
import sk.hudak.pricecomparator.middle.to.*;
import sk.hudak.pricecomparator.server.assembler.GroupOfProductAssembler;
import sk.hudak.pricecomparator.server.assembler.ProductAssembler;
import sk.hudak.pricecomparator.server.assembler.ProductInEshopAssembler;
import sk.hudak.pricecomparator.server.dao.GroupOfProductDao;
import sk.hudak.pricecomparator.server.facade.GroupOfProductFacade;
import sk.hudak.pricecomparator.server.model.GroupOfProductEntity;
import sk.hudak.pricecomparator.server.model.ProductEntity;
import sk.hudak.pricecomparator.server.model.ProductInEshopEntity;

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
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        return groupOfProductFacade.createGroupOfProduct(dto);
    }

    @Override
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupId) {
        groupOfProductFacade.addProductsToGroup(productsIdToBeAdded, groupId);
    }

    @Override
    public GroupOfProductDto getGroupOfProduct(Long groupId) {
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


}
