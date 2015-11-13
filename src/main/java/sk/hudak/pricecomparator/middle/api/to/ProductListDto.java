package sk.hudak.pricecomparator.middle.api.to;

import java.io.Serializable;

/**
 * Created by jan on 17. 10. 2015.
 */
public class ProductListDto implements Serializable {

    private Long id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductListDto dto = (ProductListDto) o;

        if (id != null ? !id.equals(dto.id) : dto.id != null) return false;
        return !(name != null ? !name.equals(dto.name) : dto.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
