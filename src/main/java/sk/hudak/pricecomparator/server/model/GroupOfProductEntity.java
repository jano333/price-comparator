package sk.hudak.pricecomparator.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 1. 1. 2016.
 */
@Entity
@Table(name = "GROUP_OF_PRODUCT")
public class GroupOfProductEntity extends BasicEntity {

    public static transient final String AT_NAME = "name";

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "GROUP_OF_PRODUCT_SEQ")
    @SequenceGenerator(name = "GROUP_OF_PRODUCT_SEQ", sequenceName = "GROUP_OF_PRODUCT_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    // zoznam produktov v danej grupe
    @ManyToMany
    @JoinTable(
            name = "GROUP_PRODUCT",
            joinColumns = @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"GROUP_ID", "PRODUCT_ID"})
    )
    private List<ProductEntity> products = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}
