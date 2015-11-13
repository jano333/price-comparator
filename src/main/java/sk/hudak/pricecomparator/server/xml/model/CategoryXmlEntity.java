package sk.hudak.pricecomparator.server.xml.model;

/**
 * Created by jan on 14. 10. 2015.
 */
public class CategoryXmlEntity {

    private Long id;
    private String name;
    private Long parentId;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
