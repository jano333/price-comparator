package sk.hudak.pricecomparator.server.factory;

import sk.hudak.pricecomparator.middle.api.canonical.Unit;
import sk.hudak.pricecomparator.middle.api.model.Category;
import sk.hudak.pricecomparator.middle.api.model.Product;

/**
 * Created by jan on 13. 10. 2015.
 */
@Deprecated
public class ProductFactory {

//    public static final Product create_one_item_500_ml_package(final Long id, final String name, Category category) {
//        return createLiterTypePackage(id, name, category, 1, 500);
//    }
//
//    public static final Product create_one_item_700_ml_package(final Long id, final String name, Category category) {
//        return createLiterTypePackage(id, name, category, 1, 700);
//    }
//
//    public static final Product create_one_item_1000_ml_package(final Long id, final String name, Category category) {
//        return createLiterTypePackage(id, name, category, 1, 1000);
//    }
//
//    public static final Product createLiterTypePackage(final Long id, final String name, final Category category,
//                                                       final long countItemInPackage, final long pocetMililitrov) {
//        return new Product() {
//            @Override
//            public Long getId() {
//                return id;
//            }
//
//            @Override
//            public String getName() {
//                return name;
//            }
//
//            @Override
//            public Unit getUnit() {
//                return Unit.LITER;
//            }
//
//            @Override
//            public long getCountOfItemInOnePackage() {
//                return countItemInPackage;
//            }
//
//            @Override
//            public long getCountOfUnit() {
//                return pocetMililitrov;
//            }
//
//            @Override
//            public Category getCategory() {
//                return category;
//            }
//        };
//    }
//
//    public static final Product createKusTypePackage(final Long id, final String name, final Category category,
//                                                     final long countItemInPackage) {
//        return new Product() {
//            @Override
//            public Long getId() {
//                return id;
//            }
//
//            @Override
//            public String getName() {
//                return name;
//            }
//
//            @Override
//            public Unit getUnit() {
//                return Unit.KUS;
//            }
//
//            @Override
//            public long getCountOfItemInOnePackage() {
//                return countItemInPackage;
//            }
//
//            @Override
//            public long getCountOfUnit() {
//                return -1;
//            }
//
//            @Override
//            public Category getCategory() {
//                return category;
//            }
//        };
//    }


}
