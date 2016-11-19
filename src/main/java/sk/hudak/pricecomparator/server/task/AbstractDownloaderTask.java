package sk.hudak.pricecomparator.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.ProductInEshopForPriceUpdateDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopInfoUpdateDto;
import sk.hudak.pricecomparator.server.html.parser.HtmlProductParser;
import sk.hudak.pricecomparator.server.html.parser.ProductParserResultCallback;
import sk.hudak.pricecomparator.server.html.parser.ProductParserResultDto;
import sk.hudak.pricecomparator.server.todo.ProductPriceCalculator;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Created by jan on 6. 11. 2016.
 */
public abstract class AbstractDownloaderTask<P extends HtmlProductParser> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private PriceComparatorService service;

    protected abstract P getHtmlParser();

    public abstract EshopType getEshopType();

    /**
     * @return false, ak nie je co aktualizovat, inak true
     */
    public boolean updateProduct() {
        logger.debug("starting next round ");
        // 1. ziskam jeden produkt, ktoremu sa vytvori/aktualizuje cena pre zvoleny eshop
        final ProductInEshopForPriceUpdateDto productForUpdate = service.findProductInEshopForPriceUpdate(getEshopType());
        if (productForUpdate == null) {
            logger.debug("nic nenaslo -> vsetko je aktualne");
            return false;
        }

        getHtmlParser().parseProductPage(productForUpdate.getEshopProductPage(), new ProductParserResultCallback() {
            @Override
            public void onSucces(ProductParserResultDto parserResult) {
                AbstractDownloaderTask.this.onSuccesParsing(productForUpdate, parserResult);
            }

            @Override
            public void onProductUnavailable(String productPage) {
                AbstractDownloaderTask.this.onProductUnavailable(productPage);
            }

            @Override
            public void onProductPageNotFound(String productPage) {
                AbstractDownloaderTask.this.onProductPageNotFound(productPage);
            }

            @Override
            public void onError(String productPage, Exception e) {
                AbstractDownloaderTask.this.onError(productPage, e);
            }
        });

        return true;
    }

    protected void onSuccesParsing(ProductInEshopForPriceUpdateDto productForUpdate, ProductParserResultDto parserResult) {
        BigDecimal productPriceForPackage = parserResult.getProductPriceForPackage();

        BigDecimal priceForOneItemInPackage = ProductPriceCalculator.calculatePriceForOneItemInPackage(
                productPriceForPackage,
                new BigDecimal(productForUpdate.getCountOfItemInOnePackage()));

        BigDecimal priceForUnit = ProductPriceCalculator.calculatePriceForUnit(
                productForUpdate.getUnit(), productForUpdate.getCountOfUnit(), priceForOneItemInPackage);

        ProductInEshopInfoUpdateDto updateDto = new ProductInEshopInfoUpdateDto();
        updateDto.setId(productForUpdate.getId());
        updateDto.setPriceForPackage(productPriceForPackage);
        updateDto.setPriceForOneItemInPackage(priceForOneItemInPackage);
        updateDto.setPriceForUnit(priceForUnit);
        updateDto.setProductAction(parserResult.getProductAction());
        updateDto.setActionValidTo(parserResult.getProductActionValidity());
        updateDto.setProductName(parserResult.getProductName());
        updateDto.setPictureUrl(parserResult.getProductPictureURL());

        // ulozenie do DB
        service.updateInfoOfProductInEshop(updateDto);
    }

    protected void onProductUnavailable(String productPage) {
        //TODO
    }

    protected void onProductPageNotFound(String productPage) {
        //TODO

    }

    protected void onError(String productPage, Exception e) {
        //TODO

    }

}
