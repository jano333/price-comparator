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

    public Long createGroupOfProduct(GroupOfProductCreateDto createDto) {
        validateDto(createDto);

        GroupOfProductEntity entity = new GroupOfProductEntity();
        entity.setName(createDto.getName().trim());
        if (!createDto.getProductIds().isEmpty()) {
            for (Long productId : createDto.getProductIds()) {
                entity.getProducts().add(productDao.readMandatory(productId));
            }
        }
        return groupOfProductDao.create(entity);
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

    private void validateDto(GroupOfProductCreateDto createDto) {
        val.notNull(createDto, "createDto is null");
        val.notNullAndNotEmpty(createDto.getName(), "name of group is null or empty");
    }
}
