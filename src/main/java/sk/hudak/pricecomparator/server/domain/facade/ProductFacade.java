package sk.hudak.pricecomparator.server.domain.facade;

import sk.hudak.jef.JefFacade;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorErrorCodes;
import sk.hudak.pricecomparator.middle.to.product.ProductCreateDto;
import sk.hudak.pricecomparator.middle.to.product.ProductUpdateDto;
import sk.hudak.pricecomparator.server.domain.dao.CategoryDao;
import sk.hudak.pricecomparator.server.domain.dao.ProductDao;
import sk.hudak.pricecomparator.server.domain.model.ProductEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jan on 29. 12. 2015.
 */
@Named
public class ProductFacade extends JefFacade {

    @Inject
    private ProductDao productDao;

    @Inject
    private CategoryDao categoryDao;

    public Long createProduct(ProductCreateDto createDto) throws PriceComparatorBusinesException {
        val.notNull(createDto, "createDto is null");
        val.notNullAndNotEmpty(createDto.getName(), "name is null or empty");
        val.maxLength(createDto.getName(), 255, "name is longer than 255 chars");
        // unikatnost
        if (productDao.existWithName(createDto.getName())) {
            throw new PriceComparatorBusinesException(PriceComparatorErrorCodes.ERR_PRODUCT_WITH_NAME_ALLREADY_EXIST, createDto.getName());
        }
        val.notNull(createDto.getUnit(), "unit is null");
        val.notNull(createDto.getCountOfUnit(), "countOfUnit is null");
        val.greaterThanZero(createDto.getCountOfUnit(), "countOfUnit is negative or zero");
        val.greaterThanZero(createDto.getCountOfItemInOnePackage(), "count of item in one package is negative or zero");

        ProductEntity product = new ProductEntity();
        product.setName(createDto.getName().trim());
        product.setUnit(createDto.getUnit());
        product.setCountOfUnit(createDto.getCountOfUnit());
        product.setCountOfItemInOnePackage(createDto.getCountOfItemInOnePackage());
        if (createDto.getCategoryId() != null) {
            product.setCategory(categoryDao.readMandatory(createDto.getCategoryId()));
        }
        return productDao.create(product);
    }

    public void updateProduct(ProductUpdateDto updateDto) throws PriceComparatorBusinesException {
        val.notNull(updateDto, "updateDto is null");
        val.notNull(updateDto.getId(), "id is null");
        val.notNullAndNotEmpty(updateDto.getName(), "name is null or empty");
        val.maxLength(updateDto.getName(), 255, "name is longer than 255 chars");
        // unikatnost TODO overit...
        if (productDao.existWithName(updateDto.getName(), updateDto.getId())) {
            throw new PriceComparatorBusinesException(PriceComparatorErrorCodes.ERR_PRODUCT_WITH_NAME_ALLREADY_EXIST, updateDto.getName());
        }
        val.notNull(updateDto.getUnit(), "unit is null");
        val.notNull(updateDto.getCountOfUnit(), "countOfUnit is null");
        val.greaterThanZero(updateDto.getCountOfUnit(), "countOfUnit is negative or zero");
        val.greaterThanZero(updateDto.getCountOfItemInOnePackage(), "count of item in one package is negative or zero");
        //TODO este nieco?

        ProductEntity product = productDao.readMandatory(updateDto.getId());
        product.setName(updateDto.getName().trim());
        product.setUnit(updateDto.getUnit());
        product.setCountOfUnit(updateDto.getCountOfUnit());
        product.setCountOfItemInOnePackage(updateDto.getCountOfItemInOnePackage());
        if (updateDto.getCategoryId() != null) {
            //FIXME read robit iba ak su rozdielne id teda aby zbytocne nesiel select
            product.setCategory(categoryDao.readMandatory(updateDto.getCategoryId()));
        }
        productDao.update(product);
    }
}
