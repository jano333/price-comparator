package sk.hudak.pricecomparator.server.database.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.server.JefDao;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.server.database.model.EshopEntity;
import sk.hudak.pricecomparator.server.database.model.ProductEntity;
import sk.hudak.pricecomparator.server.database.model.ProductInEshopEntity;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by jan on 30. 12. 2015.
 */
@Named
public class ProductInEshopDao extends JefDao<ProductInEshopEntity> {

    @Override
    public ProductInEshopEntity readMandatory(Long id) {
        return read(id, ProductInEshopEntity.class);
    }

    public List<ProductInEshopEntity> findAllProductInEshop() {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        return crit.list();
    }

    public List<EshopEntity> findEshopsWithProduct(Long productId) {
        // FIXME urobit optimalne tak ako je findEshopsWithoutProduct, lebo robi n selectov,
        // velmi neoptimalne...
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productId));
        crit.setProjection(Projections.property(ProductInEshopEntity.AT_ESHOP));
        addAscOrder(crit.createCriteria(ProductInEshopEntity.AT_ESHOP), EshopEntity.AT_NAME);
        return crit.list();
    }

    public List<EshopEntity> findEshopsWithoutProduct(Long productId) {
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

    public List<ProductInEshopEntity> findProductsInEshopByProductId(Long productId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productId));
        return crit.list();
    }

    public List<ProductInEshopEntity> findProductsInEshop(Long eshopId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_ESHOP + "." + EshopEntity.AT_ID, eshopId));
        return crit.list();
    }

    public ProductInEshopEntity findProductInEshop(Long productId, Long eshopId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        //FIXME pridat do bazovej triedy metodu na spajanie...
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_ESHOP + "." + EshopEntity.AT_ID, eshopId));
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productId));
        return (ProductInEshopEntity) crit.uniqueResult();
    }

    public ProductInEshopEntity findProductInEshopByType(EshopType eshopType) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_ESHOP + "." + EshopEntity.AT_ESHOP_TYPE, eshopType));
        //FIXME pridat unique constrain na eshop type, a povinnost !!!
        return (ProductInEshopEntity) crit.uniqueResult();
    }

    public ProductInEshopEntity findProductForPriceUpdate(EshopType eshopType, Date maxOlderDate) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);

        Criteria critEshop = crit.createCriteria(ProductInEshopEntity.AT_ESHOP);
        critEshop.add(Restrictions.eq(EshopEntity.AT_ESHOP_TYPE, eshopType));

        crit.add(Restrictions.or(
                Restrictions.isNull(ProductInEshopEntity.AT_LAST_UPDATED_PRICE),
                Restrictions.lt(ProductInEshopEntity.AT_LAST_UPDATED_PRICE, maxOlderDate)));

        addDescOrder(crit, ProductInEshopEntity.AT_LAST_UPDATED_PRICE);
        crit.setMaxResults(1);

        return (ProductInEshopEntity) crit.uniqueResult();
    }

    public List<ProductInEshopEntity> findPriceInfoInEshopsForProduct(Long productId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productId));
        // FIXME dat null do DB alebo neako inak ako -1... riesit
        // iba take, ktore mame cenu !!!,
        crit.add(Restrictions.ne(ProductInEshopEntity.AT_PRICE_FOR_UNIT, new BigDecimal(-1)));
        // od najnizsej po najvyssiu
        addAscOrder(crit, ProductInEshopEntity.AT_PRICE_FOR_UNIT);
        return crit.list();
    }
}
