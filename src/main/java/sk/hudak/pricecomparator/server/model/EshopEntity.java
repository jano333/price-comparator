package sk.hudak.pricecomparator.server.model;

import sk.hudak.pricecomparator.middle.EshopType;

import javax.persistence.*;

/**
 * Created by jan on 29. 11. 2015.
 */
@Entity
@Table(name = "ESHOP")
public class EshopEntity extends BasicEntity {

    public static final transient String AT_NAME = "name";
    public static final transient String AT_ESHOP_TYPE = "eshopType";
    public static final transient String AT_HOME_PAGE = "homePage";

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ESHOP_SEQ")
    @SequenceGenerator(name = "ESHOP_SEQ", sequenceName = "ESHOP_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "HOME_PAGE") //TODO urobit povinne aj v DB
    private String homePage;

    @Column(name = "ESHOP_TYPE") //TODO urobit povinne aj v DB
    @Enumerated(EnumType.STRING)
    private EshopType eshopType;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
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

    public EshopType getEshopType() {
        return eshopType;
    }

    public void setEshopType(EshopType eshopType) {
        this.eshopType = eshopType;
    }
}
