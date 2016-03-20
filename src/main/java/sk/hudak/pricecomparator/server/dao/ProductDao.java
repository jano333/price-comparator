package sk.hudak.pricecomparator.server.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.JefDao;
import sk.hudak.jef.PageList;
import sk.hudak.jef.ServerPaging;
import sk.hudak.pricecomparator.middle.to.ProductFindDto;
import sk.hudak.pricecomparator.server.model.ProductEntity;

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
}
