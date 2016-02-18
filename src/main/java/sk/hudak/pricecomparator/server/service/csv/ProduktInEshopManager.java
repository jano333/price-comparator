package sk.hudak.pricecomparator.server.service.csv;

import sk.hudak.pricecomparator.middle.service.ProductInEshopService;
import sk.hudak.pricecomparator.middle.to.CsvProductInEshopDto;

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

//@Name
public class ProduktInEshopManager {


    //@Inject
    private CsvStringObjectToDataObjectConverter csvStringObjectToDataObjectConverter;

    //    @Inject
    private ProductInEshopService productInEshopService;


    public void importProduct(String productFromCsvFile) {

        CsvProductInEshopDto csvProductInEshopDto = csvStringObjectToDataObjectConverter.validateAndConvert(productFromCsvFile);


    }


}
