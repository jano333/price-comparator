package sk.hudak.jef.middle.paging;

import java.io.Serializable;

/**
 * Created by jan on 13. 12. 2014.
 */
public class OrderBy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Target attribute to which sort will be applied.
     */
    private String associationPath;

    /**
     * Alias used by longer attribute paths.
     */
    private String alias;

    /**
     * Direction of sort.
     */
    private OrderDirection direction;

    @Deprecated
    public OrderBy() {
    }

    public OrderBy(String associationPath, OrderDirection direction) {
        super();
        this.associationPath = associationPath;
        this.direction = direction;
    }

    public static OrderBy asc(String associationPath) {
        return new OrderBy(associationPath, OrderDirection.ASC);
    }

    public static OrderBy desc(String associationPath) {
        return new OrderBy(associationPath, OrderDirection.DESC);
    }

    public String getAssociationPath() {
        return associationPath;
    }

    public void setAssociationPath(String associationPath) {
        this.associationPath = associationPath;
    }

    public OrderDirection getDirection() {
        return direction;
    }

    public void setDirection(OrderDirection direction) {
        this.direction = direction;
    }

//	public String getAlias() {
//		if (alias == null) {
//			alias = createAlias(associationPath);
//		}
//		return alias;
//	}
//
//	public void setAlias(String alias) {
//		this.alias = alias;
//	}

    @Override
    public String toString() {
        return "OrderBy [associationPath=" + associationPath + ", alias=" + alias + ", direction=" + direction + "]";
    }

    public boolean isAscending() {
        return OrderDirection.ASC.equals(direction);
    }

    public boolean isDescending() {
        return OrderDirection.DESC.equals(direction);
    }

}

