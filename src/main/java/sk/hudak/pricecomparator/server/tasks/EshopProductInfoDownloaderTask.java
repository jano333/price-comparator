package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.ProductDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopPriceUpdateDto;
import sk.hudak.pricecomparator.server.core.ServerConfig;

import java.util.Random;

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

    @Override
    public void run() {
        System.out.println(">> zacinam " + getClass().getSimpleName() + " thread name " + Thread.currentThread().getName());

        while (!shouldStopTask()) {
            doInOneCycle();

            if (!shouldStopTask()) {
                sleepFor();
            }
        }
        System.out.println("<< koncim " + getClass().getSimpleName() + " thread name " + Thread.currentThread().getName());
    }

    private void sleepFor() {
        int minSecond = ServerConfig.getMinWaitingTimeBeforeDownloadNextPriceInSecond();
        int maxSecond = ServerConfig.getMaxWaitingTimeBeforeDownloadNextPriceInSecond();

        int result = new Random().nextInt((maxSecond - minSecond) + 1) + minSecond;

        try {
            System.out.println("zacinam cakat " + result + " sekund");
            Thread.currentThread().sleep(result * 1000);
            System.out.println("skoncil som cakat");

        } catch (InterruptedException e) {
            e.printStackTrace();
            //TODO log
            stopTask();
        }
    }

    private void doInOneCycle() {
        System.out.println();
        System.out.println("starting next round " + getClass().getSimpleName());
        // 1. ziskam jeden produkt, ktoremu sa vytvori/aktualizuje cena pre zvoleny eshop
        //FIXME, prerobit na jednot DTO (aby som to nacital v jednej transakcii)
        ProductInEshopDto productForUpdate = service.findProductForPriceUpdate(getEshopType());
        if (productForUpdate == null) {
            System.out.println("nic nenaslo -> vsetko je aktualne");
            stopTask();
            return;
        }
        ProductDto product = service.getProduct(productForUpdate.getProductId());

        // 2. preklopenie
        ParserInputData parserInputData = transformToParserInputData(productForUpdate, product);

        // 3. stiahnem aktualne informacie a dopitam zvysne atributy
        EshopProductInfo productInfo = parser.getProductInfo(parserInputData);

        // 4. preklopenie
        ProductInEshopPriceUpdateDto updateDto = transfromToProductInEshopPriceUpdateDto(productForUpdate.getId(), productInfo);

        // 5. ulozenie do DB
        service.updateProductInEshopPrice(updateDto);
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
        System.out.println("marking task " + getClass().getSimpleName() + "as stop ");
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
