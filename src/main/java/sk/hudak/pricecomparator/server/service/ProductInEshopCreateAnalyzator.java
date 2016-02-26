package sk.hudak.pricecomparator.server.service;

import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.to.ProductAnalyzatorDto;

import java.util.regex.Pattern;

/**
 * Created by hudak on 25.2.2016.
 */
public class ProductInEshopCreateAnalyzator {

    public ProductAnalyzatorDto analyzeFromURL(String productInEshopUrl) {
        //TODO
        return null;
    }

    public ProductAnalyzatorDto analyzeFromName(String productName) {

        //TODO
        return null;
    }


    //Danone Fantasia Sladený jogurt a jahody 122 g
    private Unit analyzeUnit(String productName) {
        boolean found = analyzeUnitKilogram(productName);
        System.out.println("found " + found);


        return null;
    }

    /**
     * @param productName
     * @return true, ak sa nasla
     */
    private boolean analyzeUnitKilogram(String productName) {
        if (productName.contains(" g ") || productName.endsWith(" kg ")) {
            return true;
        }
        if (productName.endsWith(" g") || productName.endsWith(" kg")) {
            return true;
        }
        if (Pattern.compile("[0-9]g").matcher(productName).matches()) {
            return true;
        }
        if (Pattern.compile("[0-9]kg").matcher(productName).matches()) {
            return true;
        }
        //TODO dalsie

        return false;
    }

    public static void main(String[] args) {
        new ProductInEshopCreateAnalyzator().analyzeFromName("Danone Fantasia Sladený jogurt a jahody 122 g");
    }
}
