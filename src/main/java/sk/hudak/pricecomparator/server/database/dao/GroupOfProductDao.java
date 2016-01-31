package sk.hudak.pricecomparator.server.database.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.server.JefDao;
import sk.hudak.pricecomparator.server.database.model.GroupOfProductEntity;
import sk.hudak.pricecomparator.server.database.model.GroupOfProductFindEntity;
import sk.hudak.pricecomparator.server.database.model.ProductEntity;
import sk.hudak.pricecomparator.server.database.model.ProductInEshopEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 1. 1. 2016.
 */
@Named
public class GroupOfProductDao extends JefDao<GroupOfProductEntity> {

    @Inject
    private ProductInEshopDao productInEshopDao;


    @Override
    public GroupOfProductEntity readMandatory(Long groupId) {
        return readMandatory(groupId, GroupOfProductEntity.class);
    }

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

    public List<ProductInEshopEntity> findPriceInfoInEshopsForGroup(Long groupId) {
        // ziskam idecka vsetkych produktov v skupine
        Criteria crit = createCriteria(GroupOfProductFindEntity.class);
        crit.add(Restrictions.eq(GroupOfProductFindEntity.AT_GROUP_ID, groupId));
        crit.setProjection(Projections.property(GroupOfProductFindEntity.AT_PRODUCT_ID));
        List<Long> productInGroupIdList = crit.list();


        List<ProductInEshopEntity> result = productInEshopDao.findProductsInEshopByProductsIds(productInGroupIdList,
                ProductInEshopEntity.AT_PRICE_FOR_UNIT);


        return result;
    }
}
