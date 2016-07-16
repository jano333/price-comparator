package sk.hudak.pricecomparator.server.eshops.tesco.app;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.middle.to.ProductInEshopCustomListDto;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by jan on 22. 1. 2016.
 */
public class TescoPictureLocalFinder {

    private static final String PICTURE_DIR = "C:\\price-comparator\\tesco\\pictures\\";
    public static final String PRICE_COMPARATOR_IMAGES_DIR = "C:\\price-comparator\\images\\";

    public static void main(String[] args) {

        //ziskam zoznam vsetkych tesco produktov
        List<ProductInEshopCustomListDto> productsInEshop = ServiceLocator.getService().findProductsInEshop(3l);
        System.out.println("pocet produktov: " + productsInEshop.size());

        for (ProductInEshopCustomListDto productInEshopCustomListDto : productsInEshop) {
            String eshopProductPage = productInEshopCustomListDto.getEshopProductPage();
            int index = eshopProductPage.lastIndexOf("/");
            String tescoPictureName = eshopProductPage.substring(index + 1) + ".jpeg";
            File pictureFile = new File(PICTURE_DIR, tescoPictureName);
            // ak nie je stiahnuty tak chod na dalsi
            if (!pictureFile.exists()) {
                continue;
            }
            // ak uz taky mame, chod na dalsi
            if (new File(PRICE_COMPARATOR_IMAGES_DIR +
                    productInEshopCustomListDto.getProductListDto().getId() + ".jpg").exists()) {
                continue;
            }
            try {
                Files.copy(pictureFile.toPath(),
                        new FileOutputStream(
                                new File(PRICE_COMPARATOR_IMAGES_DIR +
                                        productInEshopCustomListDto.getProductListDto().getId() + "_" + tescoPictureName)));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
