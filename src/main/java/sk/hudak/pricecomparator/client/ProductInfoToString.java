package sk.hudak.pricecomparator.client;


import sk.hudak.pricecomparator.server.tobedeleted.EshopProductInfo;

import java.text.SimpleDateFormat;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductInfoToString {
    public static final String toString(EshopProductInfo eshopProductInfo) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            return "EshopProductInfo{" +
                    "inAction=" + eshopProductInfo.getAction() +
                    ", actionTo=" + (eshopProductInfo.getActionValidTo() != null ? sdf.format(eshopProductInfo.getActionValidTo()) : "") +
                    ", cenaZaBalenie='" + eshopProductInfo.getPriceForPackage() + '\'' +
                    ", cenaZaJednotku='" + eshopProductInfo.getPriceForUnit() + '\'' +
                    ", cenaZaKusVBaleni='" + eshopProductInfo.getPriceForOneItemInPackage() + '\'' +
                    '}';


        } catch (Exception e) {
            //TODO vynimka
            e.printStackTrace();
            return eshopProductInfo.toString();

        }

    }
}
