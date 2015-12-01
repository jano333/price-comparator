package sk.hudak.pricecomparator.server.database.model;

import sk.hudak.pricecomparator.server.core.LongIdEntity;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by jan on 1. 12. 2015.
 */
public abstract class BasicEntity implements LongIdEntity {

    @Column(name = "CREATED", nullable = false)
    private Date created;

    @Column(name = "LAST_MODIFIED", nullable = false)
    private Date lastModified;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
