package sk.hudak.pricecomparator.server.dao;

import org.hibernate.Criteria;
import sk.hudak.jef.JefDao;
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


    public List<ProductEntity> findAllProducts() {
        Criteria crit = createCriteria(ProductEntity.class);
        addAscOrder(crit, ProductEntity.AT_NAME);
        return crit.list();
    }
}
