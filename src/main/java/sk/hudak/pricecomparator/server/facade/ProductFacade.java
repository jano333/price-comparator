package sk.hudak.pricecomparator.server.facade;

import org.apache.commons.lang3.StringUtils;
import sk.hudak.jef.JefFacade;
import sk.hudak.pricecomparator.middle.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.to.ProductUpdateDto;
import sk.hudak.pricecomparator.server.core.ServerConfig;
import sk.hudak.pricecomparator.server.dao.CategoryDao;
import sk.hudak.pricecomparator.server.dao.ProductDao;
import sk.hudak.pricecomparator.server.model.ProductEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by jan on 29. 12. 2015.
 */
@Named
public class ProductFacade extends JefFacade {

    @Inject
    private ProductDao productDao;

    @Inject
    private CategoryDao categoryDao;

    public Long createProduct(ProductCreateDto createDto) {
        val.notNull(createDto, "createDto is null");
        val.notNullAndNotEmpty(createDto.getName(), "name is null or empty");
        val.maxLength(createDto.getName(), 255, "name is longer than 255 chars");
        //TODO unikatnost mena !!!
        val.notNull(createDto.getUnit(), "unit is null");
        val.notNull(createDto.getCountOfUnit(), "countOfUnit is null");
//        val.gaterThanZero(createDto.getCountOfUnit(), "TODO");
//        val.gaterThanZero(createDto.getCountOfItemInOnePackage(), "TODO");


        ProductEntity product = new ProductEntity();
        product.setName(createDto.getName().trim());
        product.setUnit(createDto.getUnit());
        product.setCountOfUnit(createDto.getCountOfUnit());
        product.setCountOfItemInOnePackage(createDto.getCountOfItemInOnePackage());
        if (createDto.getCategoryId() != null) {
            product.setCategory(categoryDao.readMandatory(createDto.getCategoryId()));
        }

        Long productId = productDao.create(product);

        // TODO spracovanie obrazku
        processImage(createDto, productId);

        return productId;
    }

    public void updateProduct(ProductUpdateDto updateDto) {
        val.notNull(updateDto, "updateDto is null");
        val.notNull(updateDto.getId(), "id is null");
        val.notNullAndNotEmpty(updateDto.getName(), "name is null or empty");
        val.maxLength(updateDto.getName(), 255, "name is longer than 255 chars");
        //TODO unikatnost mena !!!
        val.notNull(updateDto.getUnit(), "unit is null");
        val.notNull(updateDto.getCountOfUnit(), "countOfUnit is null");
//        val.gaterThanZero(createDto.getCountOfUnit(), "TODO");
//        val.gaterThanZero(createDto.getCountOfItemInOnePackage(), "TODO");
        //TODO ostatne

        ProductEntity product = productDao.readMandatory(updateDto.getId());
        product.setName(updateDto.getName().trim());
        product.setUnit(updateDto.getUnit());
        product.setCountOfUnit(updateDto.getCountOfUnit());
        product.setCountOfItemInOnePackage(updateDto.getCountOfItemInOnePackage());
        if (updateDto.getCategoryId() != null) {
            product.setCategory(categoryDao.readMandatory(updateDto.getCategoryId()));
        }
        // TODO spracovanie obrazku

        productDao.update(product);
    }

    private void processImage(ProductCreateDto createDto, Long productId) {
        if (StringUtils.isNotBlank(createDto.getImageLocalPath())) {
            try {
                String imageLocalPath = createDto.getImageLocalPath();
                int indexOfLastDot = imageLocalPath.lastIndexOf(".");
                StringBuilder imagePathOnServer = new StringBuilder();
                imagePathOnServer.append(ServerConfig.getImagesRootDirectory());
                imagePathOnServer.append(productId);
                imagePathOnServer.append(imageLocalPath.substring(indexOfLastDot, imageLocalPath.length()));

                File imageOnServer = new File(imagePathOnServer.toString());
                Files.copy(new ByteArrayInputStream(createDto.getImageContent()), imageOnServer.toPath());


            } catch (IOException e) {
                //TODO
                e.printStackTrace();
            }
        }
    }


}
