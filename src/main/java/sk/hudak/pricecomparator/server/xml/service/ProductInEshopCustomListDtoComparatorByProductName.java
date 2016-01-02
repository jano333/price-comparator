package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.to.ProductInEshopCustomListDto;

import java.util.Comparator;

/**
 * Created by jan on 2. 1. 2016.
 */
public class ProductInEshopCustomListDtoComparatorByProductName implements Comparator<ProductInEshopCustomListDto> {
    @Override
    public int compare(ProductInEshopCustomListDto o1, ProductInEshopCustomListDto o2) {
        return o1.getProductListDto().getName().compareToIgnoreCase(o2.getProductListDto().getName());
    }
}
