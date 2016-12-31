package sk.hudak.pricecomparator.middle.to.eshop;

import sk.hudak.pricecomparator.middle.canonical.EshopType;

import java.io.Serializable;

/**
 * Created by jan on 24. 4. 2016.
 */
public class EshopHomePageInfoDto implements Serializable {

    private EshopType eshopType;
    private String homePage;

    public EshopType getEshopType() {
        return eshopType;
    }

    public void setEshopType(EshopType eshopType) {
        this.eshopType = eshopType;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Override
    public String toString() {
        return "EshopHomePageInfoDto{" +
                "eshopType=" + eshopType +
                ", homePage='" + homePage + '\'' +
                '}';
    }
}
