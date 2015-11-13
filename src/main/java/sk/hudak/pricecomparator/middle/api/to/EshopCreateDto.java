package sk.hudak.pricecomparator.middle.api.to;

/**
 * Created by jan on 14. 10. 2015.
 */
public class EshopCreateDto {

    private String name;
    private String homePage;
    private String parserClassName;

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
