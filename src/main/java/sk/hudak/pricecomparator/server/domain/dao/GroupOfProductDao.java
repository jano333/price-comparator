package sk.hudak.pricecomparator.server.domain.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.JefDao;
import sk.hudak.jef.PageList;
import sk.hudak.jef.ServerPaging;
import sk.hudak.pricecomparator.middle.to.ProductPriceInGroupFindDto;
import sk.hudak.pricecomparator.middle.to.group.GroupOfProductFindDto;
import sk.hudak.pricecomparator.middle.to.productineshop.ProductInEshopFindDto;
import sk.hudak.pricecomparator.server.domain.model.GroupOfProductEntity;
import sk.hudak.pricecomparator.server.domain.model.GroupOfProductFindEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductEntity;
import sk.hudak.pricecomparator.server.domain.model.ProductInEshopEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 1. 1. 2016.
 */
@Named
public class GroupOfProductDao extends JefDao<GroupOfProductEntity> {

    @Inject
    private ProductDao productDao;

    @Inject
    private ProductInEshopDao productInEshopDao;


    @Override
    public GroupOfProductEntity readMandatory(Long groupId) {
        return readMandatory(groupId, GroupOfProductEntity.class);
    }

    public PageList<ProductInEshopEntity> findPriceInfoInEshopsForGroup(ProductPriceInGroupFindDto filter) {
        // ziskam idecka vsetkych produktov v skupine
        Criteria crit = createCriteria(GroupOfProductFindEntity.class);
        if (filter.getGroupId() != null) {
            crit.add(Restrictions.eq(GroupOfProductFindEntity.AT_GROUP_ID, filter.getGroupId()));
        }
        crit.setProjection(Projections.property(GroupOfProductFindEntity.AT_PRODUCT_ID));
        List<Long> productInGroupIdList = crit.list();


        ProductInEshopFindDto haha = new ProductInEshopFindDto();
        haha.setOffset(filter.getOffset());
        haha.setCount(filter.getCount());

        haha.setProductId(productInGroupIdList);

        PageList<ProductInEshopEntity> result = productInEshopDao.findProductsInEshopByProductsIdsJH(haha);
        return result;
    }

    // -------------

    public List<GroupOfProductEntity> findAllGroupsOfProducts() {
        Criteria crit = createCriteria(GroupOfProductEntity.class);
        addAscOrder(crit, GroupOfProductEntity.AT_NAME);
        return crit.list();
    }

    public List<ProductEntity> findProductsInGroup(Long groupId) {
        GroupOfProductEntity entity = readMandatory(groupId);
        //TODO ako urobit sortovanie produktov v DB?
        //urobit to cez vyhladavaciu entitu
        return entity.getProducts();
    }

    public List<ProductEntity> findProductsNotInGroup(Long groupOfProductId) {
        GroupOfProductEntity groupEntity = readMandatory(groupOfProductId);
        List<ProductEntity> groupProductsList = groupEntity.getProducts();
        //FIXME cez vyhladavaciu entitu GroupOfProductFindEntity
        List<Long> idList = new ArrayList<>(groupProductsList.size());
        for (ProductEntity productEntity : groupProductsList) {
            idList.add(productEntity.getId());
        }
        Criteria crit = createCriteria(ProductEntity.class);
        //FIXME In je limitovana poctom a treba ju rozdelit cez or...
        crit.add(Restrictions.not(Restrictions.in(ProductEntity.AT_ID, idList)));
        addAscOrder(crit, ProductEntity.AT_NAME);

        return crit.list();
    }

    @Deprecated
    public List<ProductInEshopEntity> findPriceInfoInEshopsForGroup(Long groupId) {
        // ziskam idecka vsetkych produktov v skupine
        Criteria crit = createCriteria(GroupOfProductFindEntity.class);
        crit.add(Restrictions.eq(GroupOfProductFindEntity.AT_GROUP_ID, groupId));
        crit.setProjection(Projections.property(GroupOfProductFindEntity.AT_PRODUCT_ID));
        List<Long> productInGroupIdList = crit.list();

        //TODO metro pre lovelu mi nedava do zoznamu cien produktov pre skupinu a neviem preco.l...


        List<ProductInEshopEntity> result = productInEshopDao.findProductsInEshopByProductsIds(productInGroupIdList,
                ProductInEshopEntity.AT_PRICE_FOR_UNIT);


        return result;
    }

    public PageList<GroupOfProductEntity> findGroupOfProduct(GroupOfProductFindDto filter) {
        Criteria crit = createCriteria(GroupOfProductEntity.class);
        if (StringUtils.isNotBlank(filter.getGroupName())) {
            crit.add(Restrictions.ilike(GroupOfProductEntity.AT_NAME, filter.getGroupName() + "%"));
        }
        ServerPaging pagging = createPaging(filter, crit);
        //TODO sortovanie zobrazt z find dto
        //zosrotovane podla nazvu produktu
        addAscOrder(crit, GroupOfProductEntity.AT_NAME);
        return new PageList<>(crit.list(), pagging.getCurrentPage(), pagging.getAllPage());
    }

    public PageList<ProductEntity> findProductsInGroup(GroupOfProductFindDto filter) {
        //TODO pozor, poriesene len pre groupId... z filtra nie pre name ....

        if (filter.getGroupId() != null) {
            // nacitam idecka vsetkych produktov v danej skupine
            Criteria crit = createCriteria(GroupOfProductFindEntity.class);
            crit.add(Restrictions.eq(GroupOfProductFindEntity.AT_GROUP_ID, filter.getGroupId()));
            ServerPaging serverPaging = createPaging(filter, crit);
            crit.setProjection(Projections.property(GroupOfProductFindEntity.AT_PRODUCT_ID));
            List<Long> productIdList = crit.list();

            // nacitam dane produktu na zaklade id-cok
            //TODO doriestit order
            List<ProductEntity> result = productDao.findProductsByIds(productIdList, ProductEntity.AT_NAME);

            return new PageList<>(result, serverPaging.getCurrentPage(), serverPaging.getAllPage());
        }
        //TODO nazov

        return new PageList<>(Collections.<ProductEntity>emptyList(), 0, 0);
    }
}
