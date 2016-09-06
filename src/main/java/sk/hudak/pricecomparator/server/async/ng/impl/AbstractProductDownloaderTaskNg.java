package sk.hudak.pricecomparator.server.async.ng.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.ProductDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceUpdateDto;
import sk.hudak.pricecomparator.server.async.ng.*;
import sk.hudak.pricecomparator.server.core.ServerConfig;

import java.util.Random;

/**
 * Created by jan on 9. 7. 2016.
 */
public abstract class AbstractProductDownloaderTaskNg implements EshopTaskNg {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected PriceComparatorService service;
    protected EshopTaskCallbackNg callback;

    private Boolean stop = Boolean.FALSE;

    private EshopProductParserNg parser;

    protected abstract EshopProductParserNg createEshopProductParser();

    @Override
    public void run(PriceComparatorService service, EshopTaskCallbackNg callback) {
        logger.debug("starting task");
        this.service = service;
        this.callback = callback;
        if (parser == null) {
            parser = createEshopProductParser();
        }


//        TescoTaskEventNg tescoTaskEventNg = new TescoTaskEventNg();
//        TescoProductPriceUpdaterCallbackNg callback1 = (TescoProductPriceUpdaterCallbackNg) callback;
//        callback1.onEvent(tescoTaskEventNg);

        while (!shouldStopTask()) {
            doInOneCycle();

            if (!shouldStopTask()) {
                sleepFor();
            }
        }

        logger.debug("finished task");
    }

    private void doInOneCycle() {
        logger.debug("starting next round ");
        //TODO prerobit(aby neboli zbytocne preklapacky naco???)  a to takto:

        // 1. service metoda vrati id produktu v eshope, ktory je potrebne aktualizovat
        // ak pride null tak vsetko je aktualne

        // 2. service metoda pre dane id vrati uz naplneni objekt EshopParserRequestNg !!!

        // 3. tento request podsunie do parsera a ten mi vrati response

        // 4. service pre dany response aktualizuje produkt


        // 1. ziskam jeden produkt, ktoremu sa vytvori/aktualizuje cena pre zvoleny eshop
        //FIXME, prerobit na jednot DTO (aby som to nacital v jednej transakcii)
        ProductInEshopDto productForUpdate = service.findProductForPriceUpdate(getEshopType());
        if (productForUpdate == null) {
            logger.debug("nic nenaslo -> vsetko je aktualne");
            stopTask();
            return;
        }
        ProductDto product = service.getProductById(productForUpdate.getProductId());

        // 2. preklopenie
        EshopParserRequestNg eshopParserRequest = createEshopParserRequestNg(productForUpdate, product);

        // 3. stiahnem aktualne informacie a dopitam zvysne atributy
        EshopParserResponseNg response = parser.parseEshopProductInfo(eshopParserRequest);

        // 4. preklopenie
        ProductInEshopPriceUpdateDto updateDto = transfromToProductInEshopPriceUpdateDto(productForUpdate.getId(), response);

        // 5. ulozenie do DB
        service.updateProductInEshopPrice(updateDto);
    }

    private ProductInEshopPriceUpdateDto transfromToProductInEshopPriceUpdateDto(Long id, EshopParserResponseNg response) {
        ProductInEshopPriceUpdateDto dto = new ProductInEshopPriceUpdateDto();
        dto.setId(id);
        dto.setPriceForPackage(response.getPriceForPackage());
        dto.setPriceForOneItemInPackage(response.getPriceForOneItemInPackage());
        dto.setPriceForUnit(response.getPriceForUnit());
        dto.setProductAction(response.getAction());
        dto.setActionValidTo(response.getActionValidTo());
        return dto;
    }

    private EshopParserRequestNg createEshopParserRequestNg(ProductInEshopDto productInEshopDto, ProductDto productDto) {
        return new EshopParserRequestNg()
                .setUnit(productDto.getUnit())
                .setCountOfUnit(productDto.getCountOfUnit())
                .setCountOfItemInOnePackage(productDto.getCountOfItemInOnePackage())
                .setEshopProductPage(productInEshopDto.getEshopProductPage());
    }

    private boolean shouldStopTask() {
        synchronized (stop) {
            return stop;
        }
    }

    private void sleepFor() {
        int minSecond = ServerConfig.getMinWaitingTimeBeforeDownloadNextPriceInSecond();
        int maxSecond = ServerConfig.getMaxWaitingTimeBeforeDownloadNextPriceInSecond();

        int result = new Random().nextInt((maxSecond - minSecond) + 1) + minSecond;

        try {
            logger.debug("zacinam cakat " + result + " sekund");
            Thread.currentThread().sleep(result * 1000);
            logger.debug("skoncil som cakat");

        } catch (InterruptedException e) {
            e.printStackTrace();
            //TODO log
            stopTask();
        }
    }

    /**
     * Toto zatial nie je podporovane...
     */
    public void stopTask() {
        logger.debug("marking task as stop ");
        synchronized (stop) {
            this.stop = Boolean.TRUE;
        }
    }
}
