package sk.hudak.pricecomparator.client.wicket;

import org.apache.wicket.markup.html.image.ContextImage;

import java.io.File;

/**
 * Created by jan on 26. 3. 2016.
 */
public class WU {

    public static ContextImage productImage(String imagePath) {
        String imageName = null;
        ContextImage image = null;
        if (imagePath != null) {
            imageName = new File(imagePath).getName();
            image = new ContextImage("image", "/images/" + imageName);
        } else {
            image = new ContextImage("image", "fake");
            image.setVisible(false);
        }
        return image;
    }

}
