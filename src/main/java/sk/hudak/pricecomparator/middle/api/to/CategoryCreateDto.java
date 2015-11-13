package sk.hudak.pricecomparator.middle.api.to;

/**
 * Created by jan on 14. 10. 2015.
 */
public class CategoryCreateDto {

    private String name;
    private Long categoryParentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Long categoryParentId) {
        this.categoryParentId = categoryParentId;
    }
}
