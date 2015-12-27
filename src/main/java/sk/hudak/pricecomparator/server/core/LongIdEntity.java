package sk.hudak.pricecomparator.server.core;

/**
 * Created by jan on 29. 11. 2015.
 */
public interface LongIdEntity {

    String AT_ID = "id";

    void setId(Long id);

    Long getId();
}
