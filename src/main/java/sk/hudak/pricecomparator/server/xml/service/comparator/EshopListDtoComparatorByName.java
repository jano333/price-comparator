package sk.hudak.pricecomparator.server.xml.service.comparator;

import sk.hudak.pricecomparator.middle.api.to.EshopListDto;

import java.util.Comparator;

/**
 * Created by jan on 7. 11. 2015.
 */
public class EshopListDtoComparatorByName implements Comparator<EshopListDto> {
    @Override
    public int compare(EshopListDto o1, EshopListDto o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }

}