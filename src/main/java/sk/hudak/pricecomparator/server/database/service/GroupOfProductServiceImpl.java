package sk.hudak.pricecomparator.server.database.service;

import sk.hudak.pricecomparator.middle.api.service.GroupOfProductService;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductCreateDto;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductDto;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductListDto;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;
import sk.hudak.pricecomparator.server.database.assembler.GroupOfProductAssembler;
import sk.hudak.pricecomparator.server.database.assembler.ProductAssembler;
import sk.hudak.pricecomparator.server.database.dao.GroupOfProductDao;
import sk.hudak.pricecomparator.server.database.facade.GroupOfProductFacade;

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

    @Override
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        return groupOfProductFacade.createGroupOfProduct(dto);
    }

    @Override
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {
        groupOfProductFacade.addProductsToGroup(productsIdToBeAdded, groupOfProductId);
    }

    @Override
    public GroupOfProductDto getGroupOfProduct(Long groupOfProductId) {
        return groupOfProductAssembler.transformToGroupOfProductDto(groupOfProductDao.readMandatory(groupOfProductId));
    }

    @Override
    public List<GroupOfProductListDto> findAllGroupsOfProducts() {
        return groupOfProductAssembler.transformToGroupOfProductListDto(groupOfProductDao.findAllGroupsOfProducts());
    }

    @Override
    public List<ProductListDto> getProductsInGroup(Long groupOfProductId) {
        return productAssembler.transformToListOfProductListDto(groupOfProductDao.getProductsInGroup(groupOfProductId));
    }

    @Override
    public List<ProductListDto> getProductsNotInGroup(Long groupOfProductId) {
        return productAssembler.transformToListOfProductListDto(groupOfProductDao.getProductsNotInGroup(groupOfProductId));
    }


}
