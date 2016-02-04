package sk.hudak.pricecomparator.server.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Specialna entity pouziva pre optimalne vyhladavanie.
 * <p/>
 * Created by jan on 29. 1. 2016.
 */
@Entity
@Table(name = "GROUP_PRODUCT")
public class GroupOfProductFindEntity {

    public static final String AT_GROUP_ID = "groupId";
    public static final String AT_PRODUCT_ID = "productId";

    @EmbeddedId
    private GroupOfProductFindEntityId id;

    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    public GroupOfProductFindEntityId getId() {
        return id;
    }

    public void setId(GroupOfProductFindEntityId id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Embeddable
    class GroupOfProductFindEntityId implements Serializable {

        private Long groupId;
        private Long productId;

        public Long getGroupId() {
            return groupId;
        }

        public void setGroupId(Long groupId) {
            this.groupId = groupId;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GroupOfProductFindEntityId that = (GroupOfProductFindEntityId) o;

            if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
            return !(productId != null ? !productId.equals(that.productId) : that.productId != null);

        }

        @Override
        public int hashCode() {
            int result = groupId != null ? groupId.hashCode() : 0;
            result = 31 * result + (productId != null ? productId.hashCode() : 0);
            return result;
        }
    }
}
