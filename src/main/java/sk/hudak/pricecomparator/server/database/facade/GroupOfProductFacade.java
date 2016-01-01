package sk.hudak.pricecomparator.server.database.facade;

import sk.hudak.jef.server.JefFacade;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductCreateDto;
import sk.hudak.pricecomparator.server.database.dao.GroupOfProductDao;
import sk.hudak.pricecomparator.server.database.dao.ProductDao;
import sk.hudak.pricecomparator.server.database.model.GroupOfProductEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

/**
 * Created by jan on 1. 1. 2016.
 */
@Named
public class GroupOfProductFacade extends JefFacade {

    @Inject
    private GroupOfProductDao groupOfProductDao;

    @Inject
    private ProductDao productDao;

    public Long createGroupOfProduct(GroupOfProductCreateDto dto) {
        validateDto(dto);

        GroupOfProductEntity entity = new GroupOfProductEntity();
        entity.setName(dto.getName().trim());
        if (!dto.getProductIds().isEmpty()) {
            for (Long productId : dto.getProductIds()) {
                //TODO zatial ani na GUI nepodporovane, neimplementujem
            }
        }
        return groupOfProductDao.create(entity);
    }

    private void validateDto(GroupOfProductCreateDto dto) {
        //TODO
    }

    public void addProductsToGroup(Set<Long> productsIdToBeAdded, Long groupOfProductId) {
        val.notNull(productsIdToBeAdded, "productsIdToBeAdded is null");

        GroupOfProductEntity entity = groupOfProductDao.readMandatory(groupOfProductId);
        //TODO ak uz tam je tak nepridavat, aby nevznikali duplicity hodi na DB je to poriesene
        //TODO cez unique constrain
        for (Long productId : productsIdToBeAdded) {
            entity.getProducts().add(productDao.readMandatory(productId));
        }

        groupOfProductDao.update(entity);
    }
}
