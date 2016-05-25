package sk.hudak.pricecomparator.client.wicket;

import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.File;
import java.io.Serializable;

/**
 * Created by jan on 26. 3. 2016.
 */
public class WU {

    public static PageParameters param(String name, Serializable value) {
        PageParameters params = new PageParameters();
        params.add(name, value);
        return params;
    }

    public static boolean isDevelopmentMode() {
        return "development".equals(System.getProperty("wicket.configuration"));
    }

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
