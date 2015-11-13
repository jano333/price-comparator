package sk.hudak.pricecomparator.server.downloader;

import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.*;
import sk.hudak.pricecomparator.server.xml.service.PriceComparatorXmlService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jan on 11. 11. 2015.
 */
public class Downloader2 {

    private PriceComparatorService service = new PriceComparatorXmlService();

    public GroupPriceListDto downloadProductInfoForGroup(Long groupOfProductId) {
        List<EshopProductPriceDto> eshopProductPriceDtos = new ArrayList<>();
        for (ProductListDto productListDto : service.getProductsInGroup(groupOfProductId)) {
            ProductDto productDto = service.getProduct(productListDto.getId());
//            eshopProductPriceDtos.addAll(getEshopProductPriceDtos(productDto));
        }
        // zotriedim podla ceny za jednotku
        Collections.sort(eshopProductPriceDtos, new PriceComparator());

        GroupOfProductDto groupOfProduct = service.getGroupOfProduct(groupOfProductId);

        return new GroupPriceListDto(
                groupOfProduct.getId(),
                groupOfProduct.getName(),
                eshopProductPriceDtos);
    }


    /**
     * Pre dany produkt stiahne zoznam cien pre vsetkych eshopy, v ktorych je zaregistrovany dany
     * produkt. Cenu su zoradene od najnizsej po najvyssiu za jednotku.
     *
     * @param productId id produktu, pre ktory sa maju stiahnut ceny v jednotlivych eshopoch
     * @return
     */
    public ProductPriceListDto downloadProductInfoForProduct(Long productId) {
        // ziskam informacie o produkte
        ProductDto productDto = service.getProduct(productId);
        // ziskam zoznam ehopov, v ktorych je dany produkt zaregistrovany
        List<ProductInEshopDto> productInEshopDtos = service.getProductsInEshopForDownloaderByProductId(productId);


        List<EshopProductPriceDto> eshopProductPriceDtos = new ArrayList<>(productInEshopDtos.size());


        for (ProductInEshopDto productInEshopDto : productInEshopDtos) {
            EshopDto eshopDto = service.getEshopById(productInEshopDto.getEshopId());

            String parserClassName = eshopDto.getParserClassName();
            ParserInputData parserInputData = transformToParserInputData(productDto, productInEshopDto.getEshopProductPage());

            try {
                Class<?> parserClazz = Class.forName(parserClassName);
                EshopProductParser instance = (EshopProductParser) parserClazz.newInstance();
                EshopProductInfo productInfo = instance.getProductInfo(parserInputData);

                EshopProductInfoDto result = EshopProductInfoDto.create(productInfo);

                eshopProductPriceDtos.add(new EshopProductPriceDto(
                        eshopDto.getId(),
                        eshopDto.getName(),
                        productInEshopDto.getEshopProductPage(),
                        productInfo
                ));
                System.out.println("start sleeping");
                Thread.sleep(5 * 1000);
                System.out.println("end sleeping");

            } catch (ClassNotFoundException e) {
                //TODO
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // zotriedim podla ceny za jednotku
        Collections.sort(eshopProductPriceDtos, new PriceComparator());

        return new ProductPriceListDto(
                productId,
                productDto.getName(),
                eshopProductPriceDtos);
    }

    private void getEshopProductPriceDtos(ProductDto productDto) {
        // ziskam zoznam ehopov v ktorych je zaregistrovany dany produkt
        List<ProductInEshopDto> productInEshopDtos = service.getProductsInEshopForDownloaderByProductId(productDto.getId());
        List<EshopProductPriceDto> eshopProductPriceDtos = new ArrayList<>(productInEshopDtos.size());


        for (ProductInEshopDto productInEshopDto : productInEshopDtos) {
            EshopDto eshopDto = service.getEshopById(productInEshopDto.getEshopId());

            ParserInputData parserInputData = transformToParserInputData(productDto, productInEshopDto.getEshopProductPage());

            try {
                Class<?> parserClazz = Class.forName(eshopDto.getParserClassName());
                EshopProductParser instance = (EshopProductParser) parserClazz.newInstance();
                EshopProductInfo productInfo = instance.getProductInfo(parserInputData);


                EshopProductInfoDto result = EshopProductInfoDto.create(productInfo);


                eshopProductPriceDtos.add(new EshopProductPriceDto(
                        eshopDto.getId(),
                        eshopDto.getName(),
                        productInEshopDto.getEshopProductPage(),
                        productInfo
                ));
                System.out.println("start sleeping");
                Thread.sleep(5 * 1000);
                System.out.println("end sleeping");

            } catch (ClassNotFoundException e) {
                //TODO
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ParserInputData transformToParserInputData(ProductDto productDto, String eshopProductPage) {
        return new ParserInputData(
                productDto.getCountOfItemInOnePackage(),
                productDto.getUnit(),
                productDto.getCountOfUnit(),
                eshopProductPage);
    }

    private class PriceComparator implements Comparator<EshopProductPriceDto> {

        @Override
        public int compare(EshopProductPriceDto o1, EshopProductPriceDto o2) {
            return o1.getEshopProductInfo().getPriceForUnit().compareTo(
                    o2.getEshopProductInfo().getPriceForUnit());
        }
    }

}
