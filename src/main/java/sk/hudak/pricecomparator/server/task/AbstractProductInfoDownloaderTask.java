package sk.hudak.pricecomparator.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.to.ProductInEshopForPriceUpdateDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopInfoUpdateDto;
import sk.hudak.pricecomparator.middle.to.internal.ProductInEshopUpdateStatus;
import sk.hudak.pricecomparator.server.html.parser.HtmlProductParser;
import sk.hudak.pricecomparator.server.html.parser.ProductParserResultCallback;
import sk.hudak.pricecomparator.server.to.ProductParserResultDto;
import sk.hudak.pricecomparator.server.todo.ProductPriceCalculator;

import java.math.BigDecimal;

/**
 * Created by jan on 6. 11. 2016.
 */
public abstract class AbstractProductInfoDownloaderTask<P extends HtmlProductParser> extends AbtractEshopTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract P getHtmlParser();

    @Override
    public void taskJob() {
        logger.debug(">> job stated");
        boolean isNext = updateProduct();
        while (isNext) {
            sleepFor();
            isNext = updateProduct();
        }
        logger.debug("<< job finished");
    }

    /**
     * @return false, ak nie je co aktualizovat, inak true
     */
    private boolean updateProduct() {
        logger.debug("starting next round ");
        // 1. ziskam jeden produkt, ktoremu sa vytvori/aktualizuje cena pre zvoleny eshop
        final ProductInEshopForPriceUpdateDto productForUpdate = service.findProductInEshopForPriceUpdate(getEshopType());
        if (productForUpdate == null) {
            logger.debug("nic nenaslo -> vsetko je aktualne");
            return false;
        }
        getHtmlParser().parseProductPage(productForUpdate.getEshopProductPage(),
                new ProductParserResultCallbackImpl(productForUpdate));

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

    protected void onProductUnavailable(Long productInEshopId) {
        service.changeUpdateStatus(productInEshopId, ProductInEshopUpdateStatus.UNAVAILABLE);
    }

    protected void onProductPageNotFound(Long productInEshopId) {
        service.changeUpdateStatus(productInEshopId, ProductInEshopUpdateStatus.NOT_EXIST);
    }

    protected void onError(Long productInEshopId, Exception e) {
        logger.error("update product in eshop with id " + productInEshopId + " finishd with error", e);
        service.changeUpdateStatus(productInEshopId, ProductInEshopUpdateStatus.ERROR);
    }


    private class ProductParserResultCallbackImpl implements ProductParserResultCallback {

        private final ProductInEshopForPriceUpdateDto productForUpdate;

        public ProductParserResultCallbackImpl(ProductInEshopForPriceUpdateDto productForUpdate) {
            this.productForUpdate = productForUpdate;
        }

        @Override
        public void onSucces(ProductParserResultDto parserResult) {
            AbstractProductInfoDownloaderTask.this.onSuccesParsing(productForUpdate, parserResult);
        }

        @Override
        public void onProductUnavailable(String productPage) {
            AbstractProductInfoDownloaderTask.this.onProductUnavailable(productForUpdate.getId());
        }

        @Override
        public void onProductPageNotFound(String productPage) {
            AbstractProductInfoDownloaderTask.this.onProductPageNotFound(productForUpdate.getId());
        }

        @Override
        public void onError(String productPage, Exception e) {
            AbstractProductInfoDownloaderTask.this.onError(productForUpdate.getId(), e);
        }
    }
}
