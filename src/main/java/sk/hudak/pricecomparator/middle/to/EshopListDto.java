package sk.hudak.pricecomparator.middle.to;

import java.io.Serializable;

/**
 * Created by jan on 17. 10. 2015.
 */
public class EshopListDto implements Serializable {

    public static final String AT_NAME = "name";
    public static final String AT_HOME_PAGE = "homePage";

    private Long id;
    private String name;
    private String homePage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
