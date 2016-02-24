package sk.hudak.pricecomparator.server.service.csv;

import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.to.CsvInputStringDto;
import sk.hudak.pricecomparator.middle.to.CsvProductInEshopDto;
import sk.hudak.pricecomparator.server.validator.ProductInEshopValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by jan on 24. 2. 2016.
 */
@Named
public class CsvProductInEshopConverter {

    public static final String CSV_DELIMITER = "|";

    @Inject
    private ProductInEshopValidator productInEshopValidator;

    /**
     * @param productFromCsvFile
     * @return
     * @throws PriceComparatorException
     */
    public List<CsvProductInEshopDto> validateAndConvert(List<String> productFromCsvFile, String csvDelimiter) throws PriceComparatorException {
        if (productFromCsvFile == null || productFromCsvFile.isEmpty()) {
            throw new PriceComparatorException("Input params is null or empty");
        }
        List<CsvProductInEshopDto> result = new ArrayList<>(productFromCsvFile.size());
        for (String csvLine : productFromCsvFile) {
            result.add(validateAndConvert(csvLine, csvDelimiter));
        }
        return result;
    }


    /**
     * @param productFromCsvFile jeden riadok z csv suboru
     * @param csvDelimiter
     * @return objekt ktorych obsahuje validne data z csv suboru
     * @throws PriceComparatorException ak vstupny parameter je null alebo prazdny
     *                                  alebo ak pocet parametrov vo vstupe ktore su oddelene znakom | nie je 6
     *                                  TODO dalsie informacie alebo dat len jeden ze ked je nieco neplatne :-)
     */
    public CsvProductInEshopDto validateAndConvert(String productFromCsvFile, String csvDelimiter) throws PriceComparatorException {
        if (productFromCsvFile == null || productFromCsvFile.trim().isEmpty()) {
            throw new PriceComparatorException("input is null or empty");
        }
        StringTokenizer st = new StringTokenizer(productFromCsvFile, csvDelimiter, false);

        List<String> values = new ArrayList<>();
        while (st.hasMoreTokens()) {
            values.add(st.nextToken());
        }
        if (values.size() != 6) {
            throw new PriceComparatorException("Count of input params in line is not 6");
        }
        CsvInputStringDto stringDto = new CsvInputStringDto();
        stringDto.setEshopType(values.get(0));
        stringDto.setProductName(values.get(1));
        stringDto.setUnit(values.get(2));
        stringDto.setCountOfUnit(values.get(3));
        stringDto.setCountOfItems(values.get(4));
        stringDto.setUrlOfProduct(values.get(5));

        productInEshopValidator.validateEshopType(stringDto.getEshopType());
        productInEshopValidator.validateProductName(stringDto.getProductName());
        productInEshopValidator.validateUnit(stringDto.getUnit());
        productInEshopValidator.validateCountOfUnit(stringDto.getCountOfUnit());
        productInEshopValidator.validateCountOfItems(stringDto.getCountOfItems());
        productInEshopValidator.validateUrlOfProduct(stringDto.getUrlOfProduct());

        CsvProductInEshopDto result = new CsvProductInEshopDto();
        result.setEshopType(EshopType.valueOf(stringDto.getEshopType()));
        result.setProductName(stringDto.getProductName());
        result.setUnit(Unit.valueOf(stringDto.getUnit().toUpperCase()));
        result.setCountOfUnit(new BigDecimal(stringDto.getCountOfUnit()));
        result.setCountOfItems(Integer.valueOf(stringDto.getCountOfItems()));
        result.setUrlOfProduct(stringDto.getUrlOfProduct());
        return result;
    }
}
