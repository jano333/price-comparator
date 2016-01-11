package sk.hudak.pricecomparator.server.downloader;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.middle.api.EshopProductParser;
import sk.hudak.pricecomparator.middle.api.canonical.ParserInputData;
import sk.hudak.pricecomparator.middle.api.model.EshopProductInfo;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jan on 6. 11. 2015.
 */
public class PriceDownloader {

    private PriceComparatorService service = ServiceLocator.getService();

    public GroupPriceListDto downloadProductInfoForGroup(Long groupOfProductId) {
        GroupOfProductDto groupOfProduct = service.getGroupOfProduct(groupOfProductId);
        // ziskam produkty v danej skupine
        List<ProductListDto> productsInGroup = service.getProductsInGroup(groupOfProductId);

        List<EshopProductPriceDto> eshopProductPriceDtos = new ArrayList<>();
        for (ProductListDto productListDto : productsInGroup) {
            ProductDto productDto = service.getProduct(productListDto.getId());
            eshopProductPriceDtos.addAll(getEshopProductPriceDtos(productDto));
        }
        // zotriedim podla ceny za jednotku
        Collections.sort(eshopProductPriceDtos, new PriceComparator());

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
        List<EshopProductPriceDto> eshopProductPriceDtos = getEshopProductPriceDtos(productDto);

        // zotriedim podla ceny za jednotku
        Collections.sort(eshopProductPriceDtos, new PriceComparator());

        return new ProductPriceListDto(
                productId,
                productDto.getName(),
                eshopProductPriceDtos);
    }

    private List<EshopProductPriceDto> getEshopProductPriceDtos(ProductDto productDto) {
        // ziskam zoznam ehopov v ktorych je zaregistrovany dany produkt
        List<ProductInEshopDto> productInEshopDtos = service.findProductsInEshopForDownloaderByProductId(productDto.getId());
        List<EshopProductPriceDto> eshopProductPriceDtos = new ArrayList<>(productInEshopDtos.size());


        for (ProductInEshopDto productInEshopDto : productInEshopDtos) {
            EshopDto eshopDto = service.getEshopById(productInEshopDto.getEshopId());

            String parserClassName = eshopDto.getParserClassName();
            ParserInputData parserInputData = transformToParserInputData(productDto, productInEshopDto);

            try {
                Class<?> parserClazz = Class.forName(parserClassName);
                EshopProductParser instance = (EshopProductParser) parserClazz.newInstance();
                EshopProductInfo productInfo = instance.getProductInfo(parserInputData);

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
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return eshopProductPriceDtos;
    }

    private ParserInputData transformToParserInputData(ProductDto productDto, ProductInEshopDto productInEshopDto) {
        return new ParserInputData(
                productDto.getCountOfItemInOnePackage(),
                productDto.getUnit(),
                productDto.getCountOfUnit(),
                productInEshopDto.getEshopProductPage());
    }

    private class PriceComparator implements Comparator<EshopProductPriceDto> {

        @Override
        public int compare(EshopProductPriceDto o1, EshopProductPriceDto o2) {
            return o1.getEshopProductInfo().getPriceForUnit().compareTo(
                    o2.getEshopProductInfo().getPriceForUnit());
        }
    }

}
