package sk.hudak.pricecomparator.server.domain.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import sk.hudak.jef.JefDao;
import sk.hudak.jef.paging.PageData;
import sk.hudak.pricecomparator.middle.to.NewProductFindDto;
import sk.hudak.pricecomparator.server.domain.model.NewProductEntity;

import javax.inject.Named;

/**
 * Created by jan on 29. 12. 2016.
 */
@Named
public class NewProductDao extends JefDao<NewProductEntity> {


    @Override
    public NewProductEntity readMandatory(Long id) {
        return readMandatory(id, NewProductEntity.class);
    }

    /**
     * @param productUrl
     * @return true, ak dany product s danou URL uz existuje
     */
    public boolean exist(String productUrl) {
        Criteria crit = createCriteria(NewProductEntity.class);
        crit.add(Restrictions.eq(NewProductEntity.AT_PRODUCT_URL, productUrl));
        crit.setMaxResults(1);
        return !crit.list().isEmpty();
    }

    public PageData<NewProductEntity> findNewProducts(NewProductFindDto findDto) {
        Criteria crit = createCriteria(NewProductEntity.class);
        if (findDto.getStatus() != null) {
            crit.add(Restrictions.eq(NewProductEntity.AT_STATUS, findDto.getStatus()));
        }

        //TODO if poriesit ale zatial chcem stale ak celkovy pocet...
//        if(findDto.getPaging().isDoCount()){
        crit.setProjection(Projections.rowCount());
        int allRecordsCount = ((Long) crit.uniqueResult()).intValue();
        crit.setProjection(null);
//        }
        return createPageData(crit, findDto.getPaging(), allRecordsCount);
    }
}
