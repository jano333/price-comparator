package sk.hudak.pricecomparator.server.analyzator;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.EshopHomePageInfoDto;

import javax.inject.Inject;
import javax.inject.Named;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by jan on 24. 4. 2016.
 */
@Named
public class EshopTypeResolver {

    @Inject
    private PriceComparatorService service;

    /**
     * @param productUrl
     * @return null, ak je nepodporovany
     */
    public EshopType resolveEshop(String productUrl) {

        List<EshopHomePageInfoDto> homepages = service.findAllHomePages();
        for (EshopHomePageInfoDto homepage : homepages) {
            try {
                String inputHost = new URL(productUrl).getHost();
                String dbHost = new URL(homepage.getHomePage()).getHost();
                if (dbHost.equalsIgnoreCase(inputHost)) {
                    return homepage.getEshopType();
                }
            } catch (MalformedURLException e) {
                //TODO
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
