package sk.hudak.pricecomparator.server.downloader;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by jan on 3. 12. 2015.
 */
public class TescoProductDownloader {

    private static final int DEFAULT_TIMEOUT = 12000;
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";
    private static final String PRODUCT_URL_PREFIX = "http://potravinydomov.itesco.sk";


    public void start() {
        try {
            for (int pageNumber = 1; pageNumber <= 31; pageNumber++) {
                int waitingTimeInSecond = getWaitingTimeInSecond();
//                System.err.println("cakam " + waitingTimeInSecond);
                Thread.sleep(waitingTimeInSecond * 1000);
//                System.out.println("Stranka " + pageNumber);


//                String url = "http://potravinydomov.itesco.sk/sk-SK/Promotion/List?pageNo=" + pageNumber + "&SortBy=Default";
//                String url = "http://potravinydomov.itesco.sk/sk-SK/Product/BrowseProducts?taxonomyID=Cat00000068&pageNo="+pageNumber+"&sortBy=Default";
//                String url = "http://potravinydomov.itesco.sk/sk-SK/Product/BrowseProducts?taxonomyID=Cat00000088&pageNo=" + pageNumber + "&sortBy=Default";

                // Jogurty a dezerty
//                String url = "http://potravinydomov.itesco.sk/sk-SK/Product/BrowseProducts?taxonomyID=Cat00000081&pageNo=" + pageNumber + "&sortBy=Default";
//                String url = "http://potravinydomov.itesco.sk/sk-SK/Product/BrowseProducts?taxonomyId=Cat00000104";
//                String url = "http://potravinydomov.itesco.sk/sk-SK/Product/BrowseProducts?taxonomyID=Cat00000376&pageNo=" + pageNumber + "&sortBy=Default";
                // drogeria
//                String url = "http://potravinydomov.itesco.sk/sk-SK/Product/BrowseProducts?taxonomyID=Cat00000514&pageNo=" + pageNumber + "&sortBy=Default";
                String url = "http://potravinydomov.itesco.sk/sk-SK/Product/BrowseProducts?taxonomyID=Cat00000655&pageNo=" + pageNumber + "&sortBy=Default";
//
                Connection connection = Jsoup.connect(url);
                connection.userAgent(MOZILLA_USER_AGENT_DEFAULT);
                connection.timeout(DEFAULT_TIMEOUT);
                Document document = connection.get();

//            Document document = Jsoup.parse(new File("C:\\Users\\jan\\Desktop\\tesco_action_2_page.html"), StandardCharsets.UTF_8.name());

                Elements elements = document.select("div[id=listedProductItems]");
                if (elements.isEmpty()) {
                    System.err.println("dev elements is empty");
                    return;
                }
                Element divElement = elements.get(0);
                Elements ulElements = divElement.children();
                if (ulElements.isEmpty()) {
                    System.err.println("ul elements are empty");
                    return;
                }
                Iterator<Element> ulIterator = ulElements.iterator();
                while (ulIterator.hasNext()) {
                    Element ulElement = ulIterator.next();
                    Elements liElements = ulElement.children();
                    if (liElements.isEmpty()) {
                        continue;
                    }
                    Iterator<Element> liIterator = liElements.iterator();
                    while (liIterator.hasNext()) {
                        Element liElement = liIterator.next();
                        String liHtml = liElement.html();
                        Document liDocument = Jsoup.parse(liHtml);
                        Elements aElements = liDocument.select("h2 a");
                        if (aElements.isEmpty()) {
                            continue;
                        }
                        Element aElement = aElements.get(0);
                        String productName = aElement.text();
//                    System.out.println("nazov produktu: " + productName);
                        String partOfProductLink = aElement.attr("href");
                        String productUrl = PRODUCT_URL_PREFIX + partOfProductLink;
//                    System.out.println("link produktu: " + productUrl);
                        System.out.println(productName + "|" + productUrl);
                    }
                }
//                System.out.println("END");
            }
            System.out.println("Vsetko");


            //listedProductItems


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getWaitingTimeInSecond() {
        int nextInt = new Random().nextInt(8);
        if (nextInt < 5) {
            return getWaitingTimeInSecond();
        }
        return nextInt;
    }

    public static void main(String[] args) {
        new TescoProductDownloader().start();

//        for (int i = 0; i < 50; i++) {
//            System.out.println(getWaitingTimeInSecond());
//        }
    }

}
