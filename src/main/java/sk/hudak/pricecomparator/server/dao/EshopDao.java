package sk.hudak.pricecomparator.server.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.JefDao;
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

    public List<EshopEntity> getAllEshops() {
        Criteria crit = createCriteria(EshopEntity.class);
        addAscOrder(crit, EshopEntity.AT_NAME);
        return crit.list();
    }
}
