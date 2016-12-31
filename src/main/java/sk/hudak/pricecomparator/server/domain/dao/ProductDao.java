package sk.hudak.pricecomparator.server.domain.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.JefDao;
import sk.hudak.jef.PageList;
import sk.hudak.jef.ServerPaging;
import sk.hudak.pricecomparator.middle.to.product.ProductFindDto;
import sk.hudak.pricecomparator.server.domain.model.ProductEntity;

import javax.inject.Named;
import java.util.List;

/**
 * Created by jan on 29. 12. 2015.
 */
@Named
public class ProductDao extends JefDao<ProductEntity> {
    @Override
    public ProductEntity readMandatory(Long id) {
        return readMandatory(id, ProductEntity.class);
    }


    @Deprecated
    public List<ProductEntity> findAllProducts() {
        Criteria crit = createCriteria(ProductEntity.class);
        addAscOrder(crit, ProductEntity.AT_NAME);
        return crit.list();
    }

    public PageList<ProductEntity> findProducts(ProductFindDto filter) {
        Criteria crit = createCriteria(ProductEntity.class);

        if (StringUtils.isNotBlank(filter.getName())) {
            crit.add(Restrictions.ilike(ProductEntity.AT_NAME, filter.getName() + "%"));
        }
        ServerPaging pagging = createPaging(filter, crit);
        //TODO sortovanie zobrazt z find dto
        //zosrotovane podla nazvu produktu
        addAscOrder(crit, ProductEntity.AT_NAME);
        return new PageList<>(crit.list(), pagging.getCurrentPage(), pagging.getAllPage());
    }

    public List<ProductEntity> findProductsByIds(List<Long> productIdList, String order) {
        Criteria crit = createCriteria(ProductEntity.class);
        crit.add(Restrictions.in(ProductEntity.AT_ID, productIdList));

        if (order != null) {
            addAscOrder(crit, order);
        } else {
            addAscOrder(crit, ProductEntity.AT_NAME);
        }
        return crit.list();
    }

    public boolean existWithName(String name) {
        Criteria crit = createCriteria(ProductEntity.class);
        crit.add(Restrictions.eq(ProductEntity.AT_NAME, name));
        //FIXME optimalizaovat
        return !crit.list().isEmpty();
    }

    public boolean existWithName(String productName, Long productIdSkip) {
        Criteria crit = createCriteria(ProductEntity.class);
        crit.add(Restrictions.eq(ProductEntity.AT_NAME, productName));
        crit.add(Restrictions.ne(ProductEntity.AT_ID, productIdSkip));
        //FIXME optimalizaovat
        return !crit.list().isEmpty();
    }
}
