package sk.hudak.pricecomparator.server.service.csv;

import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.CsvProductInEshopDto;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * UC import noveho produktu
 * BEGIN
 *  validujem nazov eshopu(ci sedi s tym co mam v enum)
 *      ak nie log ERROR a chod na dalsi
 *  validujem merna jednota voci enum
 *      ak nie log ERROR a chod na dalsi
 *  validujem pocet jednotiek ci je cislo
 *      ak nie log ERROR a chod na dalsi
 *  validujem pocet kusov ci je cislo
 *      ak nie log ERROR a chod na dalsi
 *  validujem nazov produktu na minimalne 3 znaky
 *      ak nie log ERROR a chod na dalsi
 *  validujem url produktu na URL patter
 *      ak nie log ERROR a chod na dalsi
 *  overit ci dana url uz existuje,
 *      ak ano log ALREADY_EXIST a chod na dalsi
 *  zoberiem nazov produktu a zistim ci taky existuje
 *      ak ano tak pridavam dany eshop k uz existujucemu produktu
 *      ak nie, vytvram novy produkt a pridavam k nemu dany eshop
 * END
 *
 *
 * </pre>
 * Created by hudak on 18.2.2016.
 */

@Named
public class ProduktInEshopManager {

    public static final String DEFAULT_CSV_DELIMITER = "|";

    //@Inject
    private CsvProductInEshopConverter csvProductInEshopConverter;

    @Inject
    private PriceComparatorService priceComparatorService;

    public void importProductsFromCsvFile(File csvFile) {
        importProductsFromCsvFile(csvFile, DEFAULT_CSV_DELIMITER);
    }

    public void importProductsFromCsvFile(File csvFile, String csvDelimiter) {
        List<String> csvLines;
        try {
            csvLines = Files.readAllLines(csvFile.toPath(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new PriceComparatorException("Error while reading from file " + csvFile.getAbsolutePath(), e);
        }
        importProductsFromString(csvLines, csvDelimiter);
    }


    public void importProductFromString(String productFromCsvFile) {
        importProductFromString(productFromCsvFile, "|");
    }

    public void importProductFromString(String productFromCsvFile, String csvDelimiter) {
        List<String> result = new ArrayList<>(1);
        result.add(productFromCsvFile);
        importProductsFromString(result, csvDelimiter);

    }

    public void importProductsFromString(List<String> productsFromCsvFile) {
        importProductsFromString(productsFromCsvFile, "|");
    }

    public void importProductsFromString(List<String> productsFromCsvFile, String csvDelimiter) {
        List<CsvProductInEshopDto> csvProductInEshopDtoList = csvProductInEshopConverter.validateAndConvert(productsFromCsvFile, csvDelimiter);
        //TODO impl

    }

    public void importProdutFromExcelFile(File excelFile) {
        //TODO impl
    }


}
