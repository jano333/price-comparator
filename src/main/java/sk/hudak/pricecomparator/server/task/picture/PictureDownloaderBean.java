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
     */
    public void downloadPicture(URL pictureURL, String pathToSave) {
        //TODO
        try {
            Thumbnails.of(pictureURL).size(82, 82).toFile(new File(pathToSave));

        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }

    }
}
