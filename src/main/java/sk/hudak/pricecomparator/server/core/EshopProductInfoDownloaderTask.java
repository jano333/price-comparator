package sk.hudak.pricecomparator.server.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.EshopProductParser;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.ProductDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopPriceUpdateDto;

import java.util.Random;

/**
 * Created by jan on 4. 1. 2016.
 */
public abstract class EshopProductInfoDownloaderTask implements Runnable {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private PriceComparatorService service;

    private Boolean stop = Boolean.FALSE;
    private EshopProductParser parser;

    public EshopProductInfoDownloaderTask(PriceComparatorService service) {
        this.service = service;
        this.parser = getEshopParser();
    }

    @Override
    public void run() {
        logger.debug(">> zacinam " + getClass().getSimpleName() + " thread name " + Thread.currentThread().getName());

        while (!shouldStopTask()) {
            doInOneCycle();

            if (!shouldStopTask()) {
                sleepFor();
            }
        }
        logger.debug("<< koncim " + getClass().getSimpleName() + " thread name " + Thread.currentThread().getName());
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

    private void doInOneCycle() {
        logger.debug("starting next round " + getClass().getSimpleName());
        // 1. ziskam jeden produkt, ktoremu sa vytvori/aktualizuje cena pre zvoleny eshop
        //FIXME, prerobit na jednot DTO (aby som to nacital v jednej transakcii)
        ProductInEshopDto productForUpdate = service.findProductForPriceUpdate(getEshopType());
        if (productForUpdate == null) {
            logger.debug("nic nenaslo -> vsetko je aktualne");
            downloadOnePictureOfProduct();
            stopTask();
            return;
        }
        ProductDto product = service.getProductById(productForUpdate.getProductId());

        // 2. preklopenie
        ParserInputData parserInputData = transformToParserInputData(productForUpdate, product);

        // 3. stiahnem aktualne informacie a dopitam zvysne atributy
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);

        // 4. preklopenie
        ProductInEshopPriceUpdateDto updateDto = transfromToProductInEshopPriceUpdateDto(productForUpdate.getId(), productInfo);

        // 5. ulozenie do DB
        service.updateProductInEshopPrice(updateDto);
    }

    /**
     * Metoda stiahne jeden obrazok pre product, ktory este nema obrazok...
     */
    protected void downloadOnePictureOfProduct() {
        //TODO

    }

    private ProductInEshopPriceUpdateDto transfromToProductInEshopPriceUpdateDto(Long id, EshopProductInfo productInfo) {
        ProductInEshopPriceUpdateDto dto = new ProductInEshopPriceUpdateDto();
        dto.setId(id);
        dto.setActionValidTo(productInfo.getActionValidTo());
        dto.setPriceForOneItemInPackage(productInfo.getPriceForOneItemInPackage());
        dto.setPriceForPackage(productInfo.getPriceForPackage());
        dto.setPriceForUnit(productInfo.getPriceForUnit());
        dto.setProductAction(productInfo.getAction());
        return dto;
    }

    private ParserInputData transformToParserInputData(ProductInEshopDto productInEshopDto, ProductDto productDto) {
        return new ParserInputData(
                productDto.getCountOfItemInOnePackage(),
                productDto.getUnit(),
                productDto.getCountOfUnit(),
                productInEshopDto.getEshopProductPage());
    }

    public abstract EshopType getEshopType();

    protected abstract EshopProductParser getEshopParser();

    public void stopTask() {
        logger.debug("marking task " + getClass().getSimpleName() + "as stop ");
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
