package sk.hudak.pricecomparator.server.database.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.middle.paging.PageData;
import sk.hudak.jef.server.JefDao;
import sk.hudak.pricecomparator.middle.api.to.EshopFindDto;
import sk.hudak.pricecomparator.server.database.model.EshopEntity;

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

    public boolean existWithParserClassName(String parserClassName) {
        Criteria cr = createCriteria(EshopEntity.class);
        cr.add(Restrictions.eq(EshopEntity.AT_PARSER_CLASS_NAME, parserClassName));
        return !cr.list().isEmpty();
    }

    public List<EshopEntity> getAllEshops() {
        Criteria crit = createCriteria(EshopEntity.class);
        addAscOrder(crit, EshopEntity.AT_NAME);
        return crit.list();
    }
}
