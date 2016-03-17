package sk.hudak.pricecomparator.server.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.JefDao;
import sk.hudak.jef.PageList;
import sk.hudak.jef.ServerPaging;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.model.ProductAction;
import sk.hudak.pricecomparator.middle.to.ProductFindDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopFindDto;
import sk.hudak.pricecomparator.server.model.EshopEntity;
import sk.hudak.pricecomparator.server.model.ProductEntity;
import sk.hudak.pricecomparator.server.model.ProductInEshopEntity;

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

    public PageList<ProductInEshopEntity> findProductsInEshop(ProductInEshopFindDto findDto) {
        if (findDto == null) {
            throw new PriceComparatorException("Find dto is null");
        }
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        // budem podla toho sortovat
        Criteria critProd = crit.createCriteria(ProductInEshopEntity.AT_PRODUCT);

        //eshopId
        if (findDto.getEshopId() != null) {
            crit.add(Restrictions.eq(ProductInEshopEntity.AT_ESHOP + "." + EshopEntity.AT_ID, findDto.getEshopId()));
        }
        //productName
        if (StringUtils.isNotBlank(findDto.getProductName())) {
            critProd.add(Restrictions.ilike(ProductEntity.AT_NAME, "%" + findDto.getProductName() + "%"));
        }
        // onlyInAction
        if (findDto.isOnlyInAction()) {
            crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT_ACTION, ProductAction.IN_ACTION));
        }

        ServerPaging pagging = createPaging(findDto, crit);
        //TODO sortovanie zobrazt z find dto
        //zosrotovane podla nazvu produktu
        addAscOrder(critProd, ProductEntity.AT_NAME);
        return new PageList<>(crit.list(), pagging.getCurrentPage(), pagging.getAllPage());
    }


    public PageList<ProductInEshopEntity> findPriceInfoInEshopsForProduct(ProductFindDto findDto) {
        if (findDto == null) {
            throw new PriceComparatorException("Find dto is null");
        }
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        if (findDto.getProductId() != null) {
            crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, findDto.getProductId()));
        }
        // FIXME dat null do DB alebo neako inak ako -1... riesit
        // iba take, ktore mame cenu !!!,
        crit.add(Restrictions.ne(ProductInEshopEntity.AT_PRICE_FOR_UNIT, new BigDecimal(-1)));


        ServerPaging pagging = createPaging(findDto, crit);
        //TODO sortovanie zobrazt z find dto
        // od najnizsej po najvyssiu
        addAscOrder(crit, ProductInEshopEntity.AT_PRICE_FOR_UNIT);

        return new PageList<>(crit.list(), pagging.getCurrentPage(), pagging.getAllPage());
    }

    //TODO vsetko nizie prejst ------------------

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

    /**
     * Vsetky produkty v danom eshop-e. Ordering podla nazvu produktu.
     *
     * @param eshopId
     * @return
     */
    public List<ProductInEshopEntity> old_findProductsInEshop(Long eshopId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_ESHOP + "." + EshopEntity.AT_ID, eshopId));

        //zosrotovane podla nazvu produktu
        addAscOrder(crit.createCriteria(ProductInEshopEntity.AT_PRODUCT), ProductEntity.AT_NAME);

        return crit.list();
    }


    public ProductInEshopEntity findProductInEshop(Long productId, Long eshopId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        //FIXME pridat do bazovej triedy metodu na spajanie...
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_ESHOP + "." + EshopEntity.AT_ID, eshopId));
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productId));
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

    public List<ProductInEshopEntity> findProductsInEshopByProductsIds(List<Long> productsId, String orderBy) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.in(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productsId));
        // iba take, ktore mame cenu !!!,
        crit.add(Restrictions.ne(ProductInEshopEntity.AT_PRICE_FOR_UNIT, new BigDecimal(-1)));
        // od najnizsej po najvyssiu
        addAscOrder(crit, orderBy);
        return crit.list();
    }


    public List<ProductInEshopEntity> findProductsInEshopByType(EshopType eshopType) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        Criteria critEshop = crit.createCriteria(ProductInEshopEntity.AT_ESHOP);
        critEshop.add(Restrictions.eq(EshopEntity.AT_ESHOP_TYPE, eshopType));

        return crit.list();
    }

    @Deprecated
    public List<ProductInEshopEntity> old_findPriceInfoInEshopsForProduct(Long productId) {
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT + "." + ProductEntity.AT_ID, productId));
        // FIXME dat null do DB alebo neako inak ako -1... riesit
        // iba take, ktore mame cenu !!!,
        crit.add(Restrictions.ne(ProductInEshopEntity.AT_PRICE_FOR_UNIT, new BigDecimal(-1)));
        // od najnizsej po najvyssiu
        addAscOrder(crit, ProductInEshopEntity.AT_PRICE_FOR_UNIT);
        return crit.list();
    }

    //-----------


    @Deprecated
    public List<ProductInEshopEntity> old_findProductsInEshop(ProductInEshopFindDto findDto) {
        if (findDto == null) {
            throw new PriceComparatorException("Find dto is null");
        }
        Criteria crit = createCriteria(ProductInEshopEntity.class);
        // budem podla toho sortovat
        Criteria critProd = crit.createCriteria(ProductInEshopEntity.AT_PRODUCT);

        //eshopId
        if (findDto.getEshopId() != null) {
            crit.add(Restrictions.eq(ProductInEshopEntity.AT_ESHOP + "." + EshopEntity.AT_ID, findDto.getEshopId()));
        }
        //productName
        if (StringUtils.isNotBlank(findDto.getProductName())) {
            critProd.add(Restrictions.ilike(ProductEntity.AT_NAME, "%" + findDto.getProductName() + "%"));
        }
        // onlyInAction
        if (findDto.isOnlyInAction()) {
            crit.add(Restrictions.eq(ProductInEshopEntity.AT_PRODUCT_ACTION, ProductAction.IN_ACTION));
        }
        //TODO sortovanie zobrazt z find dto
        //zosrotovane podla nazvu produktu
        addAscOrder(critProd, ProductEntity.AT_NAME);

        return crit.list();
    }

}
