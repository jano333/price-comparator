package sk.hudak.pricecomparator.server.database.model;

import javax.persistence.*;

/**
 * Created by jan on 29. 11. 2015.
 */
@Entity
@Table(name = "ESHOP")
public class EshopEntity extends BasicEntity {

    public static final transient String AT_NAME = "name";
    public static final transient String AT_PARSER_CLASS_NAME = "parserClassName";

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ESHOP_SEQ")
    @SequenceGenerator(name = "ESHOP_SEQ", sequenceName = "ESHOP_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "PARSER_CLASS_NAME", nullable = false, unique = true)
    private String parserClassName;

    @Column(name = "HOME_PAGE")
    private String homePage;

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
