package sk.hudak.pricecomparator.client;

/**
 * Created by jan on 14. 10. 2015.
 */
public class PriceComparator {

    public static void main(String[] args) {
//        PriceComparatorService service = new PriceComparatorXmlService();
//
//        for (ProductListDto product : service.getAllProduct()) {
//            System.out.println("Product " + product.getName());
//
//            for (ProductInEshopListDto productInEshop : service.getProductsInEshopByProductId(product.getId())) {
//
//
//                Eshop eshop = productInEshop.getEshop();
//                String parserClassName = eshop.getParserClassName();
//                try {
//                    Class<?> parserClazz = Class.forName(parserClassName);
//                    EshopProductParser instance = (EshopProductParser) parserClazz.newInstance();
//                    EshopProductInfo productInfo = instance.getProductInfo(productInEshop);
//                    System.out.println(eshop.getName() + ": " + ProductInfoToString.toString(productInfo));
//                } catch (ClassNotFoundException e) {
//                    //TODO
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println();

        }


//        List<ProductInEshop> productInEshops = productInEshopService.getAllProductInEshop();
//
//
//        for (ProductInEshop productInEshop : productInEshops) {
//            String parserClassName = productInEshop.getEshop().getParserClassName();
//            try {
//                Class<?> parserClazz = Class.forName(parserClassName);
//                EshopProductParser instance = (EshopProductParser) parserClazz.newInstance();
//                EshopProductInfo productInfo = instance.getProductInfo(productInEshop);
//                System.out.print("Product: " + productInEshop.getProduct().getName());
//                System.out.println(" Eshop: " + productInEshop.getEshop().getName());
//                System.out.println(ProductInfoToString.toString(productInfo));
//                System.out.println();
//
//            } catch (ClassNotFoundException e) {
//                //TODO
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
