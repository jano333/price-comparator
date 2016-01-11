package sk.hudak.pricecomparator.server.tasks;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.EshopType;
import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.ProductDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopPriceUpdateDto;

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

        // 1. ziskam jeden produkt, ktoremu sa vytvori/aktualizuje cena pre zvoleny eshop
        //FIXME, prerobit na jednot DTO (aby som to nacital v jednej transakcii)
        ProductInEshopDto productForUpdate = service.findProductForPriceUpdate(getEshopType());
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

    //------------- DONE:

    private ParserInputData transformToParserInputData(ProductInEshopDto productInEshopDto, ProductDto productDto) {
        return new ParserInputData(
                productDto.getCountOfItemInOnePackage(),
                productDto.getUnit(),
                productDto.getCountOfUnit(),
                productInEshopDto.getEshopProductPage());
    }

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
