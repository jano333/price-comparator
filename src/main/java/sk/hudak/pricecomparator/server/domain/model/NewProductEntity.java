package sk.hudak.pricecomparator.server.domain.model;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.to.NewProductStatus;

import javax.persistence.*;

/**
 * Created by jan on 27. 12. 2016.
 */
@Entity
@Table(name = "NEW_PRODUCT")
public class NewProductEntity extends BasicEntity {

    public static final transient String AT_PRODUCT_URL = "productUrl";

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "NEW_PRODUCT_SEQ")
    @SequenceGenerator(name = "NEW_PRODUCT_SEQ", sequenceName = "NEW_PRODUCT_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String productName;

    @Column(name = "PRODUCT_URL", nullable = false, unique = true)
    private String productUrl;

    @Column(name = "PRODUCT_PICTURE_URL")
    private String productPictureUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private NewProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESHOP_TYPE", nullable = false)
    private EshopType eshopType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductPictureUrl() {
        return productPictureUrl;
    }

    public void setProductPictureUrl(String productPictureUrl) {
        this.productPictureUrl = productPictureUrl;
    }

    public NewProductStatus getStatus() {
        return status;
    }

    public void setStatus(NewProductStatus status) {
        this.status = status;
    }

    public EshopType getEshopType() {
        return eshopType;
    }

    public void setEshopType(EshopType eshopType) {
        this.eshopType = eshopType;
    }
}
