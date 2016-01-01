package sk.hudak.pricecomparator.server.database.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.server.JefDao;
import sk.hudak.pricecomparator.server.database.model.GroupOfProductEntity;
import sk.hudak.pricecomparator.server.database.model.ProductEntity;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 1. 1. 2016.
 */
@Named
public class GroupOfProductDao extends JefDao<GroupOfProductEntity> {

    @Override
    public GroupOfProductEntity readMandatory(Long id) {
        return readMandatory(id, GroupOfProductEntity.class);
    }

    public List<GroupOfProductEntity> findAllGroupsOfProducts() {
        Criteria crit = createCriteria(GroupOfProductEntity.class);
        addAscOrder(crit, GroupOfProductEntity.AT_NAME);
        return crit.list();
    }

    public List<ProductEntity> getProductsInGroup(Long groupOfProductId) {
        GroupOfProductEntity entity = readMandatory(groupOfProductId);
        //TODO ako urobit sortovanie produktov v DB?
        return entity.getProducts();
    }

    public List<ProductEntity> getProductsNotInGroup(Long groupOfProductId) {
        GroupOfProductEntity groupEntity = readMandatory(groupOfProductId);
        List<ProductEntity> groupProductsList = groupEntity.getProducts();

        List<Long> idList = new ArrayList<>(groupProductsList.size());
        for (ProductEntity productEntity : groupProductsList) {
            idList.add(productEntity.getId());
        }
        Criteria crit = createCriteria(ProductEntity.class);
        //TODO In je limitovana poctom a treba ju rozdelit cez or...
        crit.add(Restrictions.not(Restrictions.in(ProductEntity.AT_ID, idList)));
        addAscOrder(crit, ProductEntity.AT_NAME);

        return crit.list();
    }
}
