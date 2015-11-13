package sk.hudak.pricecomparator.server.xml.service.comparator;

import sk.hudak.pricecomparator.middle.api.to.ProductListDto;

import java.util.Comparator;

/**
 * Created by jan on 11. 11. 2015.
 */
public class ProductListDtoComparatorByName implements Comparator<ProductListDto> {
    @Override
    public int compare(ProductListDto o1, ProductListDto o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
