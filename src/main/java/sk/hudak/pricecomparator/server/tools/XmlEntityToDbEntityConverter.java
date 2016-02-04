package sk.hudak.pricecomparator.server.tools;

import sk.hudak.pricecomparator.client.ServiceLocator;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.EshopCreateDto;
import sk.hudak.pricecomparator.middle.api.to.GroupOfProductCreateDto;
import sk.hudak.pricecomparator.middle.api.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.api.to.ProductInEshopCreateDto;
import sk.hudak.pricecomparator.server.xml.model.EshopXmlEntity;
import sk.hudak.pricecomparator.server.xml.model.GroupOfProductXmlEntity;
import sk.hudak.pricecomparator.server.xml.model.ProductInEshopXmlEntity;
import sk.hudak.pricecomparator.server.xml.model.ProductXmlEntity;
import sk.hudak.pricecomparator.server.xml.service.AbstracServiceXmlImpl;

import java.util.*;

/**
 * Created by jan on 6. 1. 2016.
 */
public class XmlEntityToDbEntityConverter {

    private AbstracServiceXmlImpl xmlService;
    private PriceComparatorService dbService;

    private Map<Long, EshopXmlEntity> xmlEshopMap = new HashMap<>();
    private Map<Long, ProductXmlEntity> xmlProductMap = new HashMap<>();
    private Map<Long, ProductInEshopXmlEntity> xmlProductInEshopMap = new HashMap<>();
    private Map<Long, GroupOfProductXmlEntity> xmlGroupOfProductMap = new HashMap<>();
    //---------
    // key je id pre xml entitu a value je id pre db entitu
    private Map<Long, Long> eshopsMap = new HashMap<>();
    private Map<Long, Long> productsMap = new HashMap<>();
    private Map<Long, Long> productInEshopMap = new HashMap<>();
    private Map<Long, Long> groupOfProductMap = new HashMap<>();

    public static void main(String[] args) {
        if (true) {
            System.out.println("nerobim nic !!! uz zbehol");
            return;
        }

        new XmlEntityToDbEntityConverter().run();
        System.out.println("Done");
    }

    public void run() {
        initServices();
        loadXmlEntities();
        createEshops();
        createProducts();
        createProductsInEshops();
        createGroupOfProducts();
    }

    private void createGroupOfProducts() {
        final Set<Map.Entry<Long, GroupOfProductXmlEntity>> entries = xmlGroupOfProductMap.entrySet();
        System.out.println("Pocet skupin " + entries.size());
        System.out.println();
        for (Map.Entry<Long, GroupOfProductXmlEntity> mapEntity : entries) {
            final GroupOfProductXmlEntity xmlEntity = mapEntity.getValue();

            GroupOfProductCreateDto createDto = new GroupOfProductCreateDto();
            createDto.setName(xmlEntity.getName());
            List<Long> xmlProductIds = xmlEntity.getProductIds();
            List<Long> productIds = new ArrayList<>(xmlProductIds.size());
            for (Long xmlProductId : xmlProductIds) {
                productIds.add(productsMap.get(xmlProductId));
            }
            createDto.setProductIds(productIds);

            Long groupOfProductId = dbService.createGroupOfProduct(createDto);
            groupOfProductMap.put(xmlEntity.getId(), groupOfProductId);
            System.out.println("skupina: " + createDto.getName());
        }
    }

    private void createEshops() {
        final Set<Map.Entry<Long, EshopXmlEntity>> entries = xmlEshopMap.entrySet();
        System.out.println("Pocet eshopov " + entries.size());
        System.out.println();
        for (Map.Entry<Long, EshopXmlEntity> mapEntity : entries) {
            final EshopXmlEntity xmlEntity = mapEntity.getValue();

            EshopCreateDto createDto = new EshopCreateDto();
            createDto.setHomePage(xmlEntity.getHomePage());
            createDto.setName(xmlEntity.getName());

            final Long eshopId = dbService.createEshop(createDto);

            eshopsMap.put(xmlEntity.getId(), eshopId);
            System.out.println("eshop: " + createDto.getName());
        }
    }

    private void createProducts() {
        final Set<Map.Entry<Long, ProductXmlEntity>> entries = xmlProductMap.entrySet();
        System.out.println("Pocet produktov " + entries.size());
        System.out.println();
        for (Map.Entry<Long, ProductXmlEntity> mapEntity : entries) {
            final ProductXmlEntity xmlEntity = mapEntity.getValue();

            ProductCreateDto createDto = new ProductCreateDto();
            createDto.setName(xmlEntity.getName());
            createDto.setCountOfItemInOnePackage(xmlEntity.getCountOfItemInOnePackage());
            createDto.setUnit(xmlEntity.getUnit());
            createDto.setCountOfUnit(xmlEntity.getCountOfUnit());

            final Long productId = dbService.createProduct(createDto);

            productsMap.put(xmlEntity.getId(), productId);
            System.out.println("product: " + createDto.getName());
        }
    }

    private void createProductsInEshops() {
        final Set<Map.Entry<Long, ProductInEshopXmlEntity>> entries = xmlProductInEshopMap.entrySet();
        System.out.println("Pocet produktov v eshope " + entries.size());
        System.out.println();
        for (Map.Entry<Long, ProductInEshopXmlEntity> mapEntity : entries) {
            final ProductInEshopXmlEntity xmlEntity = mapEntity.getValue();

            ProductInEshopCreateDto createDto = new ProductInEshopCreateDto();
            createDto.setEshopProductPage(xmlEntity.getEshopProductPage());
            createDto.setEshopId(eshopsMap.get(xmlEntity.getEshopId()));
            createDto.setProductId(productsMap.get(xmlEntity.getProductId()));

            final Long productInEshopId = dbService.createProductInEshop(createDto);

            productInEshopMap.put(xmlEntity.getId(), productInEshopId);
            System.out.println("product in eshop page: " + createDto.getEshopProductPage());
        }
    }


    private void loadXmlEntities() {
        //xml eshopy
        final List<EshopXmlEntity> eshopXmlEntities = xmlService.xmlDataDb.getEshops();
        for (EshopXmlEntity eshopXmlEntity : eshopXmlEntities) {
            xmlEshopMap.put(eshopXmlEntity.getId(), eshopXmlEntity);
        }

        //xml product
        final List<ProductXmlEntity> products = xmlService.xmlDataDb.getProducts();
        for (ProductXmlEntity product : products) {
            xmlProductMap.put(product.getId(), product);
        }

        // xml product in eshop
        final List<ProductInEshopXmlEntity> productInEshops = xmlService.xmlDataDb.getProductInEshops();
        for (ProductInEshopXmlEntity productInEshop : productInEshops) {
            xmlProductInEshopMap.put(productInEshop.getId(), productInEshop);
        }

        final List<GroupOfProductXmlEntity> groupOfProducts = xmlService.xmlDataDb.getGroupOfProducts();
        for (GroupOfProductXmlEntity groupOfProduct : groupOfProducts) {
            xmlGroupOfProductMap.put(groupOfProduct.getId(), groupOfProduct);
        }
    }

    private void initServices() {
        xmlService = new AbstracServiceXmlImpl();
        dbService = ServiceLocator.getService();
    }
}
