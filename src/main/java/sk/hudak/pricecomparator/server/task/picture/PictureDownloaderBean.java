package sk.hudak.pricecomparator.server.task.picture;

import net.coobird.thumbnailator.Thumbnails;

import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by jan on 19. 11. 2016.
 */
@Named
public class PictureDownloaderBean {

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
            //TODO
            e.printStackTrace();
            return false;
        }

    }
}
