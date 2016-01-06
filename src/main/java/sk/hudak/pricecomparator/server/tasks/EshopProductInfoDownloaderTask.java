package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopDto;
import sk.hudak.pricecomparator.server.parser.MetroEshopProductParser;
import sk.hudak.pricecomparator.server.parser.TescoEshopProductParser;
import sk.hudak.pricecomparator.server.xml.service.PriceComparatorXmlService;

import java.util.List;

/**
 * Created by jan on 4. 1. 2016.
 */
public abstract class EshopProductInfoDownloaderTask implements Runnable {

    private boolean stop = false;
    private EshopProductParser parser;
    private PriceComparatorService service;

    public EshopProductInfoDownloaderTask() {
        this.parser = getEshopParser();
        //TODO DB impl doriesit
        this.service = new PriceComparatorXmlService();
    }

    @Override
    public void run() {
        // ziskam zozonam produktov, ktorym treba urobit update
        List<ProductInEshopDto> productsForUpdate = service.findProductInEshopForPriceUpdate(getEshopType());
        System.out.println(">> pocet produktov pre eshop " + getEshopType() + " na aktualizaciu: " + productsForUpdate.size());


    }

    //------------- DONE:

    public abstract DownloaderEshopType getEshopType();

    public void stopTask() {
        this.stop = true;
    }

    private EshopProductParser getEshopParser() {
        switch (getEshopType()) {
            case TESCO:
                return new TescoEshopProductParser();
            case METRO:
                return new MetroEshopProductParser();
            default:
                throw new PriceComparatorException("Perser for eshop type not found");
        }
    }
}
