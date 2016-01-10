package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.ProductDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopDto;

import java.util.List;

/**
 * Created by jan on 4. 1. 2016.
 */
public abstract class EshopProductInfoDownloaderTask implements Runnable {

    private PriceComparatorService service;

    private Boolean stop = Boolean.FALSE;
    private EshopProductParser parser;

    public EshopProductInfoDownloaderTask(PriceComparatorService service) {
        this.service = service;
        this.parser = getEshopParser();
    }

    private void checkContinue() {
        //TODO vyhodit vynimku a ma skoncit
    }

    @Override
    public void run() {

        // 1. TODO ziskat jeden produkt, ktoremu sa vytvorit/aktualizovat vo zvolenom eshope

        //TODO prerobit tu vraciat len jedno DTO a nieco ako z ProductEshopInfoEntity
        // tam doimplementovat ktore atributy tam budu drzane lebo tam musim dat aj tie, ktore sa budu
        // aktualizovat teda, cena, ci je alebo nie je akcia...

        ProductInEshopDto productForUpdate = service.getProductForPriceUpdate(getEshopType());
        ProductDto product = service.getProduct(productForUpdate.getProductId());

        // 2. TODO preklopit na:
        ParserInputData parserInputData = transformToParserInputData(productForUpdate, product);
        // 3. ziskam stiahnem aktualne informacie a dopitam zvysne atributy
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);







        // ziskam zozonam produktov, ktorym treba urobit update
        List<ProductInEshopDto> productsForUpdate = service.findProductInEshopForPriceUpdate(getEshopType());
        System.out.println(">> pocet produktov pre eshop " + getEshopType() + " na aktualizaciu: " + productsForUpdate.size());


    }

    private ParserInputData transformToParserInputData(ProductInEshopDto productForUpdate, ProductDto product) {
        //TODO impl
        return null;
    }

    //------------- DONE:

    protected abstract EshopType getEshopType();

    protected abstract EshopProductParser getEshopParser();

    public void stopTask() {
        synchronized (stop) {
            this.stop = Boolean.TRUE;
        }
    }

    private boolean shouldStopTask() {
        synchronized (stop) {
            return stop;
        }
    }

}
