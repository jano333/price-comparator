package sk.picture;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by jan on 15. 10. 2016.
 */
public class PictureResizor {

    public static void main(String[] args) throws IOException {
        String sourcePath = "https://secure.ce-tescoassets.com/assets/SK/454/8594018538454/ShotType1_328x328.jpg";

        File target = new File("82_82_ShotType1_328x328.jpg");
        Thumbnails.of(new URL(sourcePath)).size(82, 82).toFile(target);
        System.out.println("done to: " + target.getAbsolutePath());
    }
}
