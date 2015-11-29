package sk.hudak.pricecomparator.server.database.dao;

import sk.hudak.pricecomparator.server.database.model.EshopEntity;

import javax.inject.Named;

/**
 * Created by jan on 29. 11. 2015.
 */
@Named
public class EshopDao extends JefDao<EshopEntity> {


    @Override
    protected EshopEntity readMandatory(Long id) {
        //TODO
        return null;
    }
}
