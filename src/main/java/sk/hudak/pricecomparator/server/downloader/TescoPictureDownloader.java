package sk.hudak.pricecomparator.server.downloader;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by jan on 4. 12. 2015.
 */
public class TescoPictureDownloader {

    private static final int DEFAULT_TIMEOUT = 12000;
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";
    private static final String PICTURE_DIR = "C:\\price-comparator\\tesco\\pictures\\";



    public void start() {
        try {
            int i = 1;
            for (String productUrl : getProductsUrl()) {


                File pictureFileOnLocal = getPictureFileOnLocal(productUrl);
                if (pictureFileOnLocal.length() != 0) {
                    System.err.println("k produktu " + productUrl + " uz existuje obrazok");
                    continue;
                }
                System.out.println(productUrl);

                i++;
                if (i == 1000) {
                    break;
                }

                Connection connection = Jsoup.connect(productUrl);
                connection.userAgent(MOZILLA_USER_AGENT_DEFAULT);
                connection.timeout(DEFAULT_TIMEOUT);
                Document document = connection.get();

                Elements elements = document.select("div[class=productImage]");
                if (elements.isEmpty()) {
                    System.err.println("div elements is empty");
                    continue;
                }
                Element imgElement = elements.get(0).child(0);
                String src = imgElement.attr("src");

                downloadPictureAndSave(src, pictureFileOnLocal);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<String> getProductsUrl() throws IOException {
        File unprocesedFile = new File("C:\\price-comparator\\tesco\\unprocessed_products.txt");
        List<String> result = new ArrayList<>();
        for (String line : Files.readAllLines(unprocesedFile.toPath(), StandardCharsets.UTF_8)) {
            StringTokenizer st = new StringTokenizer(line, "|", false);
            if (st.hasMoreElements()) {
                st.nextElement();
                if (st.hasMoreElements()) {
                    String url = (String) st.nextElement();
                    result.add(url);
                }
            }
        }
        return result;
    }

    private void downloadPictureAndSave(String url, File toSave) throws IOException, InterruptedException {
        int waitingTimeInSecond = getWaitingTimeInSecond();
        System.out.println("cakam " + waitingTimeInSecond);
        Thread.sleep(waitingTimeInSecond * 1000);
        URL pictureUrl = new URL(url);
        InputStream inputStream = pictureUrl.openStream();
        BufferedInputStream bis;
        if (!(inputStream instanceof BufferedInputStream)) {
            bis = new BufferedInputStream(inputStream);
        } else {
            bis = (BufferedInputStream) inputStream;
        }
        Files.copy(bis, toSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
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

    private static int getWaitingTimeInSecond() {
        int nextInt = new Random().nextInt(15);
        if (nextInt < 5) {
            return getWaitingTimeInSecond();
        }
        return nextInt;
    }

    public static void main(String[] args) {
        new TescoPictureDownloader().start();
    }
}
