package sk.hudak.pricecomparator.server.eshops.ng.feedo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.service.InternalTxService;
import sk.hudak.pricecomparator.server.task.AbstractNewProductDownloader;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by jan on 27. 12. 2016.
 */
//@Named
public class FeedoNewProductDownloaderTask extends AbstractNewProductDownloader {

    private static Logger logger = LoggerFactory.getLogger(FeedoNewProductDownloaderTask.class);

    @Inject
    private FeedoNewProductParser parser;

    @Inject
    private InternalTxService internalTxService;

    @Override
    public void taskJob() {
        logger.debug(">> job stated");
        //TODO
//        if (true) {
//            logger.debug("<< job finished");
//            return;
//        }

        // nacitam zoznam vyhladavcich slov(Nutrilon, Pampers, ...)
        List<String> queryStrings = internalTxService.getListOfSearchQueries();
        logger.debug("count of query string {}", queryStrings.size());

        for (String queryString : queryStrings) {
            processQueryString(queryString);
        }
        logger.debug("<< job finished");
    }

    private void processQueryString(String searchKey) {
        // vyskladanie prvej stranky
        String pageUrl = "https://www.feedo.sk/vysledky-hladania/" + searchKey + "/";
        int countOfPages = parser.getCountOfPages(pageUrl);

        sleepFor();
        internalTxService.addNewProducts(parser.parserPage(pageUrl), getEshopType());

        if (countOfPages > 1) {
            for (int i = 2; i <= countOfPages; i++) {
                // nefunguje, preto robim tak ako je nizsie...
//                pageUrl = "https://www.feedo.sk/vysledky-hladania/" + searchKey + "/#page=" + i;
                pageUrl = "https://www.feedo.sk/vysledky-hladania/" + searchKey + "/filter?strana=" + i;
                sleepFor();
                internalTxService.addNewProducts(parser.parserPage(pageUrl), getEshopType());
            }
        }
    }

    @Override
    public EshopType getEshopType() {
        return EshopType.FEEDO;
    }

    @Override
    public int getTaskPriority() {
        return 2;
    }
}
