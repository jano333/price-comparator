package sk.hudak.pricecomparator.middle.to;

import java.io.Serializable;

/**
 * Created by jan on 16. 3. 2016.
 */
public abstract class IdNameDto implements Serializable {

    public static final String AT_ID = "id";
    public static final String AT_NAME = "name";

    private Long id;
    private String name;

    public IdNameDto() {
    }

    public IdNameDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

        IdNameDto idNameDto = (IdNameDto) o;

        if (id != null ? !id.equals(idNameDto.id) : idNameDto.id != null) return false;
        return !(name != null ? !name.equals(idNameDto.name) : idNameDto.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IdNameDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
