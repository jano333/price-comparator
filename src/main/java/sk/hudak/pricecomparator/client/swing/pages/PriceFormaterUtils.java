package sk.hudak.pricecomparator.client.swing.pages;

import sk.hudak.pricecomparator.middle.api.to.ProductInEshopPriceResultListDto;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jan on 1. 2. 2016.
 */
public class PriceFormaterUtils {

    public static String createText(List<ProductInEshopPriceResultListDto> result) {
        SimpleDateFormat dateFormater = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateTimeFormater = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");


        StringBuilder sb = new StringBuilder();
        for (ProductInEshopPriceResultListDto eshopProductPriceDto : result) {
            // nazov eshopu
            sb.append(eshopProductPriceDto.getEshopName());
            // cena za jednotku
            sb.append("\t ").append(eshopProductPriceDto.getPriceForUnit()).append(" € za jednotku");
            // cena za balenie
            sb.append("\t ").append(eshopProductPriceDto.getPriceForPackage()).append(" € za balenie");
            // akcia
            sb.append("\t ").append(eshopProductPriceDto.getProductAction().name());
            // akcia platna do
            if (eshopProductPriceDto.getActionValidTo() != null) {
                sb.append(" ").append(dateFormater.format(eshopProductPriceDto.getActionValidTo()));
            }
            // aktualizovane o
            sb.append("\takt.: ");
            if (eshopProductPriceDto.getLastUpdatedPrice() == null) {
                sb.append("---").append("\t");
            } else {
                sb.append(dateTimeFormater.format(eshopProductPriceDto.getLastUpdatedPrice())).append("\t");
            }
            sb.append(eshopProductPriceDto.getProductEshopPage()).append(" ");

//            sb.append("image path: ");
//            sb.append(eshopProductPriceDto.getEshopProductInfo().getProductImageUrl());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
