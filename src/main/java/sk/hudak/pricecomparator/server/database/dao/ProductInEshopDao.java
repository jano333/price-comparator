package sk.hudak.pricecomparator.server.database.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.server.JefDao;
import sk.hudak.pricecomparator.server.database.model.EshopEntity;
import sk.hudak.pricecomparator.server.database.model.ProductEntity;
import sk.hudak.pricecomparator.server.database.model.ProductInEshopEntity;

import javax.inject.Named;
import java.util.List;

/**
 * Created by jan on 30. 12. 2015.
 */
@Named
public class ProductInEshopDao extends JefDao<ProductInEshopEntity> {

    @Override
    protected ProductInEshopEntity readMandatory(Long id) {
        return read(id, ProductInEshopEntity.class);
    }

    public List<ProductInEshopEntity> getAllProductInEshop() {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        return crit.list();
    }

    public List<EshopEntity> getEshopsWithProduct(Long productId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productId));
        crit.setProjection(Projections.property(ProductInEshopEntity.AT_ESHOP));
        addAscOrder(crit.createCriteria(ProductInEshopEntity.AT_ESHOP), EshopEntity.AT_NAME);
        return crit.list();
    }

    public List<EshopEntity> getEshopsWithoutProduct(Long productId) {
        Criteria cr = createCriteria(ProductInEshopEntity.class);
        cr.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productId));
        cr.setProjection(Projections.distinct(Projections.property(ProductInEshopEntity.AT_ESHOP + "." + EshopEntity.AT_ID)));
        // zoznam eshopov, ktore maju dany produkt
        List<Long> eshopsIds = cr.list();

        Criteria crEshop = createCriteria(EshopEntity.class);
        crEshop.add(Restrictions.not(Restrictions.in(EshopEntity.AT_ID, eshopsIds)));
        addAscOrder(crEshop, EshopEntity.AT_NAME);
        List<EshopEntity> eshops = crEshop.list();

        return eshops;
    }

    public List<ProductInEshopEntity> getProductsInEshopByProductId(Long productId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productId));
        return crit.list();
    }

    public List<ProductInEshopEntity> getProductsInEshop(Long eshopId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_ESHOP + "." + EshopEntity.AT_ID, eshopId));
        return crit.list();

    }
}
