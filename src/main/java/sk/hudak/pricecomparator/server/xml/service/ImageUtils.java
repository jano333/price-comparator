package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.server.core.ServerConfig;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by jan on 23. 12. 2015.
 */
public class ImageUtils {

    public static String findProductImage(final Long productId) {
        File tmp = new File(ServerConfig.getImagesRootDirectory());
        String[] list = tmp.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.contains(String.valueOf(productId))) {
                    return true;
                }
                return false;
            }
        });
        if (list == null || list.length < 1) {
            return null;
        } else {
            return new File(tmp, list[0]).getAbsolutePath();
        }
    }
}
