package sk.hudak.pricecomparator.server.facade;

import org.apache.commons.lang3.StringUtils;
import sk.hudak.jef.JefFacade;
import sk.hudak.pricecomparator.middle.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.to.ProductEditDto;
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

    public Long createProduct(ProductCreateDto dto) {
        validateDto(dto);

        ProductEntity product = new ProductEntity();
        product.setName(dto.getName().trim());
        product.setCountOfItemInOnePackage(dto.getCountOfItemInOnePackage());
        product.setUnit(dto.getUnit());
        product.setCountOfUnit(dto.getCountOfUnit());
        if (dto.getCategoryId() != null) {
            product.setCategory(categoryDao.readMandatory(dto.getCategoryId()));
        }

        Long productId = productDao.create(product);

        // TODO spracovanie obrazku
        processImage(dto, productId);

        return productId;
    }

    private void processImage(ProductCreateDto dto, Long productId) {
        if (StringUtils.isNotBlank(dto.getImageLocalPath())) {
            try {
                String imageLocalPath = dto.getImageLocalPath();
                int indexOfLastDot = imageLocalPath.lastIndexOf(".");
                StringBuilder imagePathOnServer = new StringBuilder();
                imagePathOnServer.append(ServerConfig.getImagesRootDirectory());
                imagePathOnServer.append(productId);
                imagePathOnServer.append(imageLocalPath.substring(indexOfLastDot, imageLocalPath.length()));

                File imageOnServer = new File(imagePathOnServer.toString());
                Files.copy(new ByteArrayInputStream(dto.getImageContent()), imageOnServer.toPath());


            } catch (IOException e) {
                //TODO
                e.printStackTrace();
            }
        }
    }

    private void validateDto(ProductCreateDto dto) {
        val.notNull(dto, "dto is null");
        val.notNullAndNotEmpty(dto.getName(), "name is null or empty");
        val.maxLength(dto.getName(), 255, "name is longer than 255 chars");
        //TODO ostatne parametre
    }

    public void updateProduct(ProductEditDto editDto) {
        //TODO
    }
}
