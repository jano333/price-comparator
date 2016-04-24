package sk.hudak.pricecomparator.server.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.JefDao;
import sk.hudak.jef.PageList;
import sk.hudak.jef.ServerPaging;
import sk.hudak.jef.paging.PageData;
import sk.hudak.pricecomparator.middle.to.EshopFindDto;
import sk.hudak.pricecomparator.server.model.EshopEntity;

import javax.inject.Named;
import java.util.List;

/**
 * Created by jan on 29. 11. 2015.
 */
@Named
public class EshopDao extends JefDao<EshopEntity> {

    @Override
    public EshopEntity readMandatory(Long id) {
        return readMandatory(id, EshopEntity.class);
    }

    public PageList<EshopEntity> findEshops(EshopFindDto filter) {
        Criteria crit = createCriteria(EshopEntity.class);
        if (StringUtils.isNotBlank(filter.getName())) {
            crit.add(Restrictions.ilike(EshopEntity.AT_NAME, filter.getName() + "%"));
        }
        ServerPaging pagging = createPaging(filter, crit);
        //TODO sortovanie zobrazt z find dto
        //zosrotovane podla nazvu produktu
        addAscOrder(crit, EshopEntity.AT_NAME);
        return new PageList<>(crit.list(), pagging.getCurrentPage(), pagging.getAllPage());
    }

    //TODO ------------

    public PageData<EshopEntity> findByCriteria(EshopFindDto findDto) {
        val.notNull(findDto, "findDto dto is null");

        Criteria crit = createCriteria(EshopEntity.class);
        crit.add(Restrictions.eq(EshopEntity.AT_NAME, findDto.getName()));

        if (findDto.getOrders().isEmpty()) {
            addAscOrder(crit, EshopEntity.AT_NAME);
        } else {
            // TODO order

        }
        return createPageData(crit, findDto);
    }

    /**
     * @param eshopName nazov eshopu
     * @return true, ak eshop s danym nazvom uz existuje
     */
    public boolean existWithName(String eshopName) {
        Criteria cr = createCriteria(EshopEntity.class);
        cr.add(Restrictions.eq(EshopEntity.AT_NAME, eshopName));
        return !cr.list().isEmpty();
    }

    public List<EshopEntity> findAllEshops() {
        Criteria crit = createCriteria(EshopEntity.class);
        addAscOrder(crit, EshopEntity.AT_NAME);
        return crit.list();
    }

    /**
     * @return home pages csetkych eshopov
     */
    public List<String> findAllHomePages() {
        Criteria crit = createCriteria(EshopEntity.class);
        crit.setProjection(Projections.property(EshopEntity.AT_HOME_PAGE));
        return crit.list();
    }
}
