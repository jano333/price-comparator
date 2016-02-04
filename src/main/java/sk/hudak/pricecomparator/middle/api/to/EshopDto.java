package sk.hudak.pricecomparator.middle.api.to;

/**
 * Created by jan on 18. 10. 2015.
 */
public class EshopDto {

    private Long id;
    private String name;
    private String homePage;
    @Deprecated
    private String parserClassName;

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

    public String getParserClassName() {
        return parserClassName;
    }

    public void setParserClassName(String parserClassName) {
        this.parserClassName = parserClassName;
    }
}
