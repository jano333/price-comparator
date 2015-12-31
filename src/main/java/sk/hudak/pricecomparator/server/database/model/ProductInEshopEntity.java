package sk.hudak.pricecomparator.server.database.model;

import javax.persistence.*;

/**
 * Created by jan on 1. 12. 2015.
 */
@Entity
@Table(name = "PRODUCT_IN_ESHOP")
public class ProductInEshopEntity extends BasicEntity {

    public static final transient String AT_PRODUCT = "product";
    public static final transient String AT_ESHOP = "eshop";

    //TODO
    // urobit generovanie podla http://stackoverflow.com/questions/27314165/generate-ddl-script-at-maven-build-with-hibernate4-jpa-2-1

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "PRODUCT_IN_ESHOP_SEQ")
    @SequenceGenerator(name = "PRODUCT_IN_ESHOP_SEQ", sequenceName = "PRODUCT_IN_ESHOP_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ESHOP_ID")
    private EshopEntity eshop;

    // definuje stranku produktu v danom eshope
    @Column(name = "PRODUCT_PAGE_IN_ESHOP", nullable = false, unique = true)
    private String productPageInEshop;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public EshopEntity getEshop() {
        return eshop;
    }

    public void setEshop(EshopEntity eshop) {
        this.eshop = eshop;
    }

    public String getProductPageInEshop() {
        return productPageInEshop;
    }

    public void setProductPageInEshop(String productPageInEshop) {
        this.productPageInEshop = productPageInEshop;
    }


}
