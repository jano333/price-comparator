package sk.hudak.pricecomparator.server.html.parser;

/**
 * Created by jan on 5. 11. 2016.
 */
public interface ProductParserResultCallback {

    /**
     * v pripade uspesneho vyparsovania jednotlivych atributov
     *
     * @param parserResult
     */
    void onSucces(ProductParserResultDto parserResult);

    /**
     * ak stranka produktu existuje ale napr. produkt je nedostupny, produkt je vypredany, atd...
     */
    void onProductUnavailable(String productPage);

    /**
     * HTTP 404
     *
     * @param productPage stranka produktu
     */
    void onProductPageNotFound(String productPage);

    /**
     * v pripade chyby pri parsovani
     *
     * @param productPage stranka produktu
     * @param e           dovod chyby
     */
    void onError(String productPage, Exception e);
}
