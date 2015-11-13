package sk.hudak.pricecomparator.server.factory;

import sk.hudak.pricecomparator.middle.api.model.Category;

/**
 * Created by jan on 13. 10. 2015.
 */
public class CategoryFactory {

    public static Category createRootCategory(final Long id, final String name) {
        return new Category() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public Category getParent() {
                return null;
            }
        };
    }

    public static Category createCategory(final Long id, final String name, final Category parent) {
        return new Category() {
            @Override
            public Long getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public Category getParent() {
                return parent;
            }
        };
    }
}
