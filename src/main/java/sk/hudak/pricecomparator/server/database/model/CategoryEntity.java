package sk.hudak.pricecomparator.server.database.model;

import javax.persistence.*;

/**
 * Created by jan on 30. 11. 2015.
 */
@Entity
@Table(name = "CATEGORY")
public class CategoryEntity extends BasicEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "CATEGORY_SEQ")
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_CATEGORY_ID", updatable = false)
    private CategoryEntity parentCategory;

    public boolean isRoot() {
        return parentCategory == null;
    }

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

    public CategoryEntity getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
    }
}
