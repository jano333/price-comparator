package sk.connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jan on 18. 1. 2017.
 */
public class TestHttpGet {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) {
        try {


            String url = "https://www.feedo.sk/6x-nutrilon-3-pronutra-800g-dojcenske-mlieko/";

            showHttpCode(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showHttpCode(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");
        // nerob redirect ak pride...
        con.setInstanceFollowRedirects(false);

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
//            System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("URL: " + url);
        System.out.println("Response Code : " + responseCode);
        String newURL = con.getHeaderField("Location");
        if (newURL != null) {
            System.out.println("Location: " + newURL);
        }
    }
}
