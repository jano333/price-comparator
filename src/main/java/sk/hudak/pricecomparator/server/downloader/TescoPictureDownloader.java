package sk.hudak.pricecomparator.server.downloader;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by jan on 4. 12. 2015.
 */
public class TescoPictureDownloader {

    private static final int DEFAULT_TIMEOUT = 12000;
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";


    public static final String PICTURE_DIR = "C:\\price-comparator\\tesco\\pictures\\";

    public void start() {
        try {
            String productUrl = "http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002017045461";
            File pictureFileOnLocal = getPictureFileOnLocal(productUrl);
            if (pictureFileOnLocal.length() != 0) {
                System.err.println("k produktu " + productUrl + " uz existuje obrazok");
                return;
            }

            Connection connection = Jsoup.connect(productUrl);
            connection.userAgent(MOZILLA_USER_AGENT_DEFAULT);
            connection.timeout(DEFAULT_TIMEOUT);
            Document document = connection.get();

            Elements elements = document.select("div[class=productImage]");
            if (elements.isEmpty()) {
                System.err.println("div elements is empty");
                return;
            }
            Element imgElement = elements.get(0).child(0);
            String src = imgElement.attr("src");
            System.out.println(">> " + src);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private File getPictureFileOnLocal(String productUrl) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(productUrl.substring(productUrl.lastIndexOf("/"), productUrl.length() - 1));
        sb.append(".jpeg");

        String pictureName = sb.toString();

        File pictureFile = new File(PICTURE_DIR, pictureName);
        if (!pictureFile.exists()) {
            pictureFile.createNewFile();
        }
        return pictureFile;
    }

    public static void main(String[] args) {
        new TescoPictureDownloader().start();
    }
}
