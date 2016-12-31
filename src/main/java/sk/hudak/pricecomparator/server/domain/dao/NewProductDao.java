package sk.hudak.pricecomparator.server.domain.dao;

import sk.hudak.jef.JefDao;
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
        //TODO
        return false;
    }
}
