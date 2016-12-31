package sk.hudak.pricecomparator.middle.to.eshop;

import sk.hudak.pricecomparator.middle.to.FindDto;

/**
 * Created by jan on 27. 12. 2015.
 */
public class EshopFindDto extends FindDto {

    public static final String AT_ESHOP_NAME = "name";

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
