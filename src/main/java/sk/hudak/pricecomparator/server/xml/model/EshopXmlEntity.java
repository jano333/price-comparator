package sk.hudak.pricecomparator.server.xml.model;

/**
 * Created by jan on 14. 10. 2015.
 */
public class EshopXmlEntity {

    private Long id;
    private String name;
    private String homePage;
    private String parserClassName;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public void setParserClassName(String parserClassName) {
        this.parserClassName = parserClassName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getParserClassName() {
        return parserClassName;
    }
}
