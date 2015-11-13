package sk.hudak.pricecomparator.server.core;

import sk.hudak.pricecomparator.middle.api.model.Category;
import sk.hudak.pricecomparator.middle.api.model.Eshop;
import sk.hudak.pricecomparator.middle.api.model.Product;
import sk.hudak.pricecomparator.middle.api.model.ProductInEshop;
import sk.hudak.pricecomparator.server.factory.CategoryFactory;
import sk.hudak.pricecomparator.server.factory.ProductFactory;
import sk.hudak.pricecomparator.server.factory.ProductInEshopFactory;
import sk.hudak.pricecomparator.server.parser.FeedoEshopProductParser;
import sk.hudak.pricecomparator.server.parser.HejEshopProductParser;
import sk.hudak.pricecomparator.server.parser.MetroEshopProductParser;
import sk.hudak.pricecomparator.server.parser.TescoEshopProductParser;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jan on 13. 10. 2015.
 */
@Deprecated
public class Init {

//    // eshops
//    public static final long E_METRO_ID = 1l;
//    public static final long E_TESCO_ID = 2l;
//    public static final long E_FEEDO_ID = 3l;
//    public static final long E_HEJ_ID = 4l;
//
//    // products
//    private static final long P_Pampers_2_72_ks = 1l;
//    private static final long P_Finlandia_Vodka_700_ml_ID = 2l;
//    private static final long P_Finlandia_Vodka_1000_ml_ID = 3l;
//    private static final long P_Goral_Vodka_700_ml_ID = 4l;
//    private static final long P_Pampers_2_216_ks = 5l;
//
//    private static final long C_NAPOJE_ID = 1l;
//    private static final long C_LIEHOVINY_ID = 2l;
//    private static final long C_DESTILATY_ID = 3l;
//    private static final long C_VODKA_ID = 4l;
//    private static final Long C_NEZARADENE_ID = 5l;
//
//    private static Set<Eshop> eshops = new HashSet<>();
//    private static Set<Category> categories = new HashSet<>();
//    private static Set<Product> products = new HashSet<>();
//    private static Set<ProductInEshop> productInEshops = new HashSet<>();
//
//    static {
//        init_c_Eshops();
//        init_c_Categories();
//        init_c_Products();
//        init_c_ProductInEshop();
//    }
//
//    private static void init_c_Eshops() {
//        eshops.add(new EshopDefault(E_METRO_ID, "Metro", MetroEshopProductParser.class.getName(), "www.metro.sk"));
//        eshops.add(new EshopDefault(E_TESCO_ID, "Tesco", TescoEshopProductParser.class.getName(), "http://potravinydomov.itesco.sk/"));
//        eshops.add(new EshopDefault(E_FEEDO_ID, "Feedo", FeedoEshopProductParser.class.getName(), "www.feedo.sk"));
//        eshops.add(new EshopDefault(E_HEJ_ID, "Hej", HejEshopProductParser.class.getName(), "www.hej.sk"));
//    }
//
//    private static void init_c_Categories() {
//        categories.add(CategoryFactory.createRootCategory(C_NEZARADENE_ID, "Nezaradene"));
//        categories.add(CategoryFactory.createRootCategory(C_NAPOJE_ID, "Nápoje"));
//        categories.add(CategoryFactory.createCategory(C_LIEHOVINY_ID, "Liehoviny, likéry a aperitívy", getCategoryById(C_NAPOJE_ID)));
//        categories.add(CategoryFactory.createCategory(C_DESTILATY_ID, "Destiláty", getCategoryById(C_LIEHOVINY_ID)));
//        categories.add(CategoryFactory.createCategory(C_VODKA_ID, "Vodka", getCategoryById(C_DESTILATY_ID)));
//    }
//
//    private static void init_c_Products() {
//        products.add(ProductFactory.createKusTypePackage(P_Pampers_2_72_ks, "Pampers_2_72_ks", getCategoryById(C_NEZARADENE_ID), 72));
//        products.add(ProductFactory.createKusTypePackage(P_Pampers_2_216_ks, "Pampers_2_216_ks", getCategoryById(C_NEZARADENE_ID), 216));
//
//        products.add(ProductFactory.create_one_item_700_ml_package(P_Finlandia_Vodka_700_ml_ID, "Finlandia_Vodka_700_ml", getCategoryById(C_VODKA_ID)));
//        products.add(ProductFactory.create_one_item_1000_ml_package(P_Finlandia_Vodka_1000_ml_ID, "Finlandia_Vodka_1000_ml", getCategoryById(C_VODKA_ID)));
//        products.add(ProductFactory.create_one_item_700_ml_package(P_Goral_Vodka_700_ml_ID, "Goral_Vodka_700_ml", getCategoryById(C_VODKA_ID)));
//    }
//
//    private static void init_c_ProductInEshop() {
//
////        Product PAPMERS_2_72 = getProductById(P_Pampers_2_72_ks);
////        productInEshops.add(ProductInEshopFactory.createBasic(1l, PAPMERS_2_72,
////                getEshopById(E_METRO_ID), "https://sortiment.metro.sk/sk/pampers-premium-care-vp-mini-72k/157208p/"));
////        productInEshops.add(ProductInEshopFactory.createBasic(2l, PAPMERS_2_72,
////                getEshopById(E_FEEDO_ID), "https://www.feedo.sk/obchod/plienky-a-doplnky/plienky-jednorazove/pampers-premium-care-2-mini-3-6kg-72ks-detske-plienky/"));
////        productInEshops.add(ProductInEshopFactory.createBasic(3l, PAPMERS_2_72,
////                getEshopById(E_HEJ_ID), "http://www.hej.sk/plienky-pampers-premium-care-mini-72/"));
//
//
////
////        Product Finlandia_Vodka_700_ml = getProductById(P_Finlandia_Vodka_700_ml_ID);
////        productInEshops.add(ProductInEshopFactory.createBasic(4l, Finlandia_Vodka_700_ml,
////                getEshopById(E_METRO_ID), "https://sortiment.metro.sk/sk/finlandia-vodka-40-1x700ml-/159866p/"));
////        productInEshops.add(ProductInEshopFactory.createBasic(5l, Finlandia_Vodka_700_ml,
////                getEshopById(E_TESCO_ID), "http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002006543756"));
////
////        Product Finlandia_Vodka_1000_ml = getProductById(P_Finlandia_Vodka_1000_ml_ID);
////        productInEshops.add(ProductInEshopFactory.createBasic(5l, Finlandia_Vodka_1000_ml,
////                getEshopById(E_METRO_ID), "https://sortiment.metro.sk/sk/finlandia-vodka-40-1x1l/159884p/"));
////        productInEshops.add(ProductInEshopFactory.createBasic(6l, Finlandia_Vodka_1000_ml,
////                getEshopById(E_TESCO_ID), "http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002013482956"));
////
//        Product Goral_Vodka_700_ml = getProductById(P_Goral_Vodka_700_ml_ID);
//        productInEshops.add(ProductInEshopFactory.createBasic(7l, Goral_Vodka_700_ml,
//                getEshopById(E_METRO_ID), "https://sortiment.metro.sk/sk/goral-vodka-master-40-700ml-/149312p/"));
//        productInEshops.add(ProductInEshopFactory.createBasic(8l, Goral_Vodka_700_ml,
//                getEshopById(E_TESCO_ID), "http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002016966873"));
//
//        Product PAPMERS_2_216 = getProductById(P_Pampers_2_216_ks);
//        productInEshops.add(ProductInEshopFactory.createBasic(9l, PAPMERS_2_216,
//                getEshopById(E_FEEDO_ID), "https://www.feedo.sk/obchod/plienky-a-doplnky/plienky-jednorazove/pampers-premium-care-plienky-velkost-2-mini-3-6-kg-216-ks-mesacna-zasoba-f009499/"));
//
//        // Tesco Maslo 82 % 250 g
//        //http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002121105995
//
//        // kacka cela
//        // http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002011571799
//
//        //kacacie stehna
//        //http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002011583532
//
//        // Miko Marlenka Medová torta s kakaom 800 g
//        // http://potravinydomov.itesco.sk/sk-SK/ProductDetail/ProductDetail/2002016223778
//
//    }
//
//    public static Set<ProductInEshop> getProductInEshops() {
//        return productInEshops;
//    }
//
//    public static Product getProductById(Long productId) {
//        for (Product product : products) {
//            if (product.getId().equals(productId)) {
//                return product;
//            }
//        }
//        return null;
//    }
//
//    public static Eshop getEshopById(Long eshopId) {
//        for (Eshop eshop : eshops) {
//            if (eshop.getId().equals(eshopId)) {
//                return eshop;
//            }
//        }
//        return null;
//    }
//
//    public static Category getCategoryById(Long categoryId) {
//        for (Category category : categories) {
//            if (category.getId().equals(categoryId)) {
//                return category;
//            }
//        }
//        return null;
//    }
}
