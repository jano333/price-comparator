package sk.hudak.pricecomparator.middle.to;

/**
 * Created by jan on 17. 10. 2015.
 */
public class EshopListDto {

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
