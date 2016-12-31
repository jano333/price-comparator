package sk.hudak.pricecomparator.middle.to.eshop;

import sk.hudak.pricecomparator.middle.canonical.EshopType;

import java.io.Serializable;

/**
 * Created by jan on 14. 10. 2015.
 */
public class EshopCreateDto implements Serializable {

    public static final String AT_NAME = "name";
    public static final String AT_HOME_PAGE = "homePage";
    public static final String AT_ESHOP_TYPE = "eshopType";

    private String name; // povinne
    private String homePage; // povinne
    private EshopType eshopType; // povinne

    public EshopType getEshopType() {
        return eshopType;
    }

    public void setEshopType(EshopType eshopType) {
        this.eshopType = eshopType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }
}
