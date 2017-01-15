package sk.hudak.pricecomparator.server.picture;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.server.core.ServerConfigNg;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Trieda pre pracu z obrazkami.
 * <p>
 * Created by jan on 6. 1. 2017.
 */
@Named
public class PictureManager {

    private static Logger logger = LoggerFactory.getLogger(PictureManager.class);

    @Inject
    private ServerConfigNg config;

    /**
     * Kontrola voci file systemu.
     *
     * @param newProductId
     * @return true, ak uz existuje obrazok pre dany product, inak false
     */
    public boolean existPictureForNewProduct(Long newProductId) {
        for (String pictureName : getRootDirForNewProductPicture().list()) {
            if (FilenameUtils.getBaseName(pictureName).toLowerCase().equals(String.valueOf(newProductId))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param newProductId bude pouzite pre novy nazov obrazku
     * @param pictureURL
     */
    public void downloadPictureForNewProduct(Long newProductId, String pictureURL) {
        try {
            File targetPictureFile = new File(getRootDirForNewProductPicture(), String.valueOf(newProductId));
            Thumbnails.of(new URL(pictureURL)).size(82, 82).toFile(targetPictureFile);

        } catch (IOException e) {
            logger.error("downloadPictureForNewProduct", e);

        }
    }

    /**
     * @param pictureURL URL obrazku odkial sa ma stiahnut
     * @param pathToSave cela cesta vratane nazvu obrazku, kde sa ma ulozit
     * @return true, ak uspesne inak false
     */
    public boolean downloadPicture(String pictureURL, String pathToSave) {
        try {
            File targetPictureFile = new File(pathToSave);
            Thumbnails.of(new URL(pictureURL)).size(82, 82).toFile(targetPictureFile);
            return true;

        } catch (IOException e) {
            logger.error("downloadPicture", e);
            return false;
        }
    }

    private File getRootDirForNewProductPicture() {
        File rootDir = Paths.get(config.getImagesRootDirectory(), "newProduct").toFile();
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }
        return rootDir;
    }
}
