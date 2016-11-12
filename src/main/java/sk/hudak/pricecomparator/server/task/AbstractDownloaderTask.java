package sk.hudak.pricecomparator.server.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.ProductInEshopDto;
import sk.hudak.pricecomparator.middle.to.ProductInEshopInfoUpdateDto;
import sk.hudak.pricecomparator.server.html.parser.HtmlProductParser;
import sk.hudak.pricecomparator.server.html.parser.ProductParserResultCallback;
import sk.hudak.pricecomparator.server.html.parser.ProductParserResultDto;

import javax.inject.Inject;

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
        final ProductInEshopDto productForUpdate = service.findProductForPriceUpdate(getEshopType());
        if (productForUpdate == null) {
            logger.debug("nic nenaslo -> vsetko je aktualne");
            return false;
        }

        getHtmlParser().parseProductPage(productForUpdate.getEshopProductPage(), new ProductParserResultCallback() {
            @Override
            public void onSucces(ProductParserResultDto parserResult) {
                onSuccesParsing(productForUpdate, parserResult);
            }

            @Override
            public void onProductUnavailable(String productPage) {
                //TODO
            }

            @Override
            public void onProductPageNotFound(String productPage) {
                //TODO impl
            }

            @Override
            public void onError(String productPage, Exception e) {
                //TODO impl
            }
        });

        return true;
    }

    protected void onSuccesParsing(ProductInEshopDto productForUpdate, ProductParserResultDto parserResult) {
        try {
            ProductInEshopInfoUpdateDto updateDto = new ProductInEshopInfoUpdateDto();
            updateDto.setId(productForUpdate.getId());

            updateDto.setPriceForPackage(parserResult.getProductPriceForPackage());
            //TODO dopocitat
            updateDto.setPriceForOneItemInPackage(null);
            //TODO dopocitat
            updateDto.setPriceForUnit(null);
            // nepovinne
            updateDto.setProductAction(parserResult.getProductAction());
            updateDto.setActionValidTo(parserResult.getProductActionValidity());
            updateDto.setProductName(parserResult.getProductName());
            updateDto.setPictureUrl(parserResult.getProductPictureURL());

            // ulozenie do DB
            service.updateInfoOfProductInEshop(updateDto);

        } catch (PriceComparatorBusinesException e) {
            //TODO
            e.printStackTrace();
        }
    }


}
