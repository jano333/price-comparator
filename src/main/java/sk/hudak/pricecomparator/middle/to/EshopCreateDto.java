package sk.hudak.pricecomparator.middle.to;

import java.io.Serializable;

/**
 * Created by jan on 14. 10. 2015.
 */
public class EshopCreateDto implements Serializable {

    private String name; // povinne
    private String homePage; // nepovinne

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
