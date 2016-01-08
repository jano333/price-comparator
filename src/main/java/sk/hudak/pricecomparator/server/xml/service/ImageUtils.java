package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.server.core.ServerConfig;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by jan on 23. 12. 2015.
 */
public class ImageUtils {

    public static String findProductImage(final Long productId) {
        File tmp = new File(ServerConfig.getImagesRootDirectory());
        File[] list = tmp.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().equalsIgnoreCase(String.valueOf(productId));
            }
        });
        if (list == null || list.length < 1) {
            return null;
        } else {
            return list[0].getAbsolutePath();
        }
    }
}
