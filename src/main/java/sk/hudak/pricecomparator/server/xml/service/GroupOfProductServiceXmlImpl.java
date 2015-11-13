package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.api.service.GroupOfProductService;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductCreateDto;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductDto;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductListDto;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;
import sk.hudak.pricecomparator.server.xml.model.GroupOfProductXmlEntity;
import sk.hudak.pricecomparator.server.xml.model.ProductXmlEntity;
import sk.hudak.pricecomparator.server.xml.service.comparator.GroupListDtoComparatorByName;
import sk.hudak.pricecomparator.server.xml.service.comparator.ProductListDtoComparatorByName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by jan on 3. 11. 2015.
 */
public class GroupOfProductServiceXmlImpl extends AbstracServiceXmlImpl implements GroupOfProductService {

    @Override
    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        //TODO validacia
        GroupOfProductXmlEntity entity = new GroupOfProductXmlEntity();
        entity.setId(generateNewId());
        entity.setName(dto.getName());
        entity.setProductIds(new ArrayList<>(dto.getProductIds()));

        saveXmlEntity(entity);

        return entity.getId();

    }

    @Override
    public GroupOfProductDto getGroupOfProduct(Long groupOfProductId) {
        for (GroupOfProductXmlEntity group : xmlDataDb.getGroupOfProducts()) {
            if (group.getId().equals(groupOfProductId)) {
                GroupOfProductDto dto = new GroupOfProductDto();
                dto.setId(group.getId());
                dto.setName(group.getName());
                return dto;
            }
        }
        throw new PriceComparatorException("group with id " + groupOfProductId + " not found");
    }

    @Override
    public List<GroupOfProductListDto> findAllGroupsOfProducts() {
        List<GroupOfProductXmlEntity> groupOfProducts = xmlDataDb.getGroupOfProducts();
        List<GroupOfProductListDto> result = new ArrayList<>(groupOfProducts.size());
        for (GroupOfProductXmlEntity groupOfProduct : groupOfProducts) {
            GroupOfProductListDto dto = new GroupOfProductListDto();
            dto.setId(groupOfProduct.getId());
            dto.setName(groupOfProduct.getName());
            result.add(dto);
        }
        Collections.sort(result, new GroupListDtoComparatorByName());

        return result;
    }

    @Override
    public List<ProductListDto> getProductsInGroup(Long groupOfProductId) {
        if (groupOfProductId == null) {
            throw new PriceComparatorException("groupOfProductId is null ");
        }
        List<GroupOfProductXmlEntity> groupOfProducts = xmlDataDb.getGroupOfProducts();
        GroupOfProductXmlEntity group = null;
        for (GroupOfProductXmlEntity groupOfProduct : groupOfProducts) {
            if (groupOfProduct.getId().equals(groupOfProductId)) {
                group = groupOfProduct;
                break;
            }
        }
        if (group == null) {
            throw new PriceComparatorException("group not found with id " + groupOfProductId);
        }
        //TODO prerobit cez DAO nad entitami a tie volat...
        List<Long> productIds = group.getProductIds();
        List<ProductXmlEntity> allProducts = xmlDataDb.getProducts();
        List<ProductListDto> result = new ArrayList<>(productIds.size());
        for (ProductXmlEntity allProduct : allProducts) {
            if (productIds.contains(allProduct.getId())) {
                ProductListDto dto = new ProductListDto();
                dto.setId(allProduct.getId());
                dto.setName(allProduct.getName());
                result.add(dto);
            }
        }
        Collections.sort(result, new ProductListDtoComparatorByName());
        return result;
    }

    @Override
    public List<ProductListDto> getProductsNotInGroup(Long groupOfProductId) {
        GroupOfProductXmlEntity group = readMandatory(groupOfProductId);
        //TODO prerobit cez DAO nad entitami a tie volat...
        List<Long> productIdsInGroup = group.getProductIds();
        List<ProductXmlEntity> products = xmlDataDb.getProducts();

        List<ProductListDto> result = new ArrayList<>();


        for (ProductXmlEntity product : products) {
            if (productIdsInGroup.contains(product.getId())) {
                continue;
            }
            ProductListDto dto = new ProductListDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            result.add(dto);
        }
        return result;
    }

    @Override
    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {
        GroupOfProductXmlEntity group = readMandatory(groupOfProductId);
        group.getProductIds().addAll(productsIdToBeAdded);

        updateXmlEntity();
    }


    //TODO dat do bazovej triedy !!! a parametrizovat ak sa da
    private GroupOfProductXmlEntity readMandatory(Long groupOfProductId) {
        if (groupOfProductId == null) {
            throw new PriceComparatorException("groupOfProductId is null ");
        }
        List<GroupOfProductXmlEntity> groupOfProducts = xmlDataDb.getGroupOfProducts();
        GroupOfProductXmlEntity group = null;
        for (GroupOfProductXmlEntity groupOfProduct : groupOfProducts) {
            if (groupOfProduct.getId().equals(groupOfProductId)) {
                group = groupOfProduct;
                break;
            }
        }
        if (group == null) {
            throw new PriceComparatorException("group not found with id " + groupOfProductId);
        }
        return group;
    }

}
