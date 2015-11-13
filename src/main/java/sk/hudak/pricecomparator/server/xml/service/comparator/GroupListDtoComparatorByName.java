package sk.hudak.pricecomparator.server.xml.service.comparator;

import sk.hudak.pricecomparator.middle.api.to.GroupOfProductListDto;

import java.util.Comparator;

/**
 * Created by jan on 11. 11. 2015.
 */
public class GroupListDtoComparatorByName implements Comparator<GroupOfProductListDto> {
    @Override
    public int compare(GroupOfProductListDto o1, GroupOfProductListDto o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
