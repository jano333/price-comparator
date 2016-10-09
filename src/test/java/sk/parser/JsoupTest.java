package sk.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by jan on 9. 10. 2016.
 */
public class JsoupTest {

    private static final int DEFAULT_TIMEOUT = 10000;
    private static final String MOZILLA_USER_AGENT_DEFAULT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0";
//    public static final String url = "https://www.mojalekaren.sk/nutrilon-3-pronutra-balenia-6x-800g/";
    public static final String url = "https://www.mojalekaren.sk/nutrilon-1-800g/";

    public static void main(String[] args) {


        Connection connection = Jsoup.connect(url);
        connection.userAgent(MOZILLA_USER_AGENT_DEFAULT);
//        connection.cookies(getCookies());
        connection.timeout(DEFAULT_TIMEOUT);

        try {
            Document document = connection.get();

            Elements select = document.select("div[class=\"detailProductInformation\"] button[class=\"buy-cz\"]");

            System.out.println("pocet je " + select.size());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
