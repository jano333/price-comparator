package sk.hudak.pricecomparator.middle.to.product;

import java.io.Serializable;

/**
 * Created by jan on 17. 10. 2015.
 */
public class ProductListDto implements Serializable {

    public static final String AT_NAME = "name";

    private Long id;
    private String name;
    private String imagePath;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductListDto dto = (ProductListDto) o;

        if (id != null ? !id.equals(dto.id) : dto.id != null) return false;
        if (name != null ? !name.equals(dto.name) : dto.name != null) return false;
        return !(imagePath != null ? !imagePath.equals(dto.imagePath) : dto.imagePath != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductListDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
