package sk.hudak.pricecomparator.middle.api.model;

/**
 * Created by jan on 13. 10. 2015.
 */
public interface Category {

    Long getId();

    /**
     * @return nazov kategorie
     */
    String getName();

    /**
     * @return v pripade root kategorie je to null
     */
    Category getParent();
}
