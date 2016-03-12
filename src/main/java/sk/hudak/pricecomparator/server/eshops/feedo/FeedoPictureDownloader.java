package sk.hudak.pricecomparator.server.eshops.feedo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.ProductInEshopForPictureDownloadInfoDto;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;

/**
 * Created by jan on 28. 2. 2016.
 */
@Named
public class FeedoPictureDownloader {
    private static final int DEFAULT_TIMEOUT = 12000;
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";
    private static final String PICTURE_DIR = "C:\\price-comparator\\feedo\\pictures\\";

    @Inject
    private PriceComparatorService service;

    /**
     * Pre vsetky produktu, ktore nemaju obrazujok pre eshop feedo stiahne obrazky.
     */
    public void startDownloading() {
        try {
            ProductInEshopForPictureDownloadInfoDto productUrl;
            while ((productUrl = service.findUrlOfProductsInEshopWithoutPicture(EshopType.FEEDO)) != null) {

                String pictureUrl = downloadPictureUrl(productUrl.getProductInEshopUrl());
                System.out.println("picture url " + pictureUrl);
                if (pictureUrl == null) {
                    System.err.println("src obrazka sa nenaslo");
                    return;
                }

                int waitingTimeInSecond = getWaitingTimeInSecond();
                System.out.println("cakam " + waitingTimeInSecond);
                Thread.sleep(waitingTimeInSecond * 1000);

                downloadPictureAndSave(pictureUrl, new File(PICTURE_DIR, String.valueOf(productUrl.getProductId()) + ".jpg"));

                waitingTimeInSecond = getWaitingTimeInSecond();
                System.out.println("cakam " + waitingTimeInSecond);
                Thread.sleep(waitingTimeInSecond * 1000);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String downloadPictureUrl(String productUrl) {

        try {
            Connection connection = Jsoup.connect(productUrl);
            connection.userAgent(MOZILLA_USER_AGENT_DEFAULT);
            connection.timeout(DEFAULT_TIMEOUT);
            Document document = connection.get();

            Elements elements = document.select("a[class=image]");
            if (elements.isEmpty()) {
                //TODO totoje stav ked produkt uz nie dostupny na danej adrese
                System.err.println("div elements is empty");
                return null;
            }

            String pictureUrl = elements.attr("href");
            return pictureUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void downloadPictureAndSave(String url, File toSave) throws IOException, InterruptedException {

        URL pictureUrl = new URL(url);
        InputStream inputStream = pictureUrl.openStream();
        BufferedInputStream bis;
        if (!(inputStream instanceof BufferedInputStream)) {
            bis = new BufferedInputStream(inputStream);
        } else {
            bis = (BufferedInputStream) inputStream;
        }
        Files.copy(bis, toSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("ulozil som obrazok " + toSave.toString());
    }

    private static int getWaitingTimeInSecond() {
        int nextInt = new Random().nextInt(15);
        if (nextInt < 5) {
            return getWaitingTimeInSecond();
        }
        return nextInt;
    }
}
