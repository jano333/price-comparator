package sk.hudak.pricecomparator.server.xml.service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;
import sk.hudak.pricecomparator.server.xml.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by jan on 14. 10. 2015.
 */
public class AbstracServiceXmlImpl {

    public static final String PATH_TO_XML_DATA_FILE = "C:\\price-comparator\\db\\data.xml";

    private static XStream xstream;
    protected static XMLDataDb xmlDataDb;


    static {
        xstream = new XStream(new StaxDriver());
        xstream.alias("xmlDataDb", XMLDataDb.class);
        xstream.alias("eshop", EshopXmlEntity.class);
        xstream.alias("category", CategoryXmlEntity.class);
        xstream.alias("product", ProductXmlEntity.class);
        xstream.alias("productInEshop", ProductInEshopXmlEntity.class);

        ClassAliasingMapper mapper = new ClassAliasingMapper(xstream.getMapper());
        mapper.addClassAlias("productId", Long.class);
        xstream.registerLocalConverter(GroupOfProductXmlEntity.class, "productIds", new CollectionConverter(mapper));
        xstream.alias("groupOfProducts", GroupOfProductXmlEntity.class);


        try {
            File dataFile = new File(PATH_TO_XML_DATA_FILE);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                xmlDataDb = new XMLDataDb();
                xstream.toXML(xmlDataDb, new FileOutputStream(dataFile));
            } else {
                xmlDataDb = (XMLDataDb) xstream.fromXML(dataFile);
            }

        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
    }

    private void saveToXml() {
        try {
            File dataFile = new File(PATH_TO_XML_DATA_FILE);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            xstream.toXML(xmlDataDb, new FileOutputStream(dataFile));


        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }

    protected Long generateNewId() {
        return new Random().nextLong();
    }


    protected <T> T readMandatory(Long entityId, Class<T> clazz) {
        //TODO refaktor cez instenace of

        if (CategoryXmlEntity.class.equals(clazz)) {
            for (CategoryXmlEntity category : xmlDataDb.getCategories()) {
                if (category.getId().equals(entityId)) {
                    return (T) category;
                }
            }
        }

        if (EshopXmlEntity.class.equals(clazz)) {
            for (EshopXmlEntity eshop : xmlDataDb.getEshops()) {
                if (eshop.getId().equals(entityId)) {
                    return (T) eshop;
                }
            }
        }

        if (ProductXmlEntity.class.equals(clazz)) {
            for (ProductXmlEntity product : xmlDataDb.getProducts()) {
                if (product.getId().equals(entityId)) {
                    return (T) product;
                }
            }
        }

        if (ProductInEshopXmlEntity.class.equals(clazz)) {
            for (ProductInEshopXmlEntity productInEshop : xmlDataDb.getProductInEshops()) {
                if (productInEshop.getId().equals(entityId)) {
                    return (T) productInEshop;
                }
            }
        }


        throw new RuntimeException("Not found with id " + entityId);
    }

    protected void saveXmlEntity(ProductInEshopXmlEntity entity) {
        //TODO jednu metodu a cez instance of
        xmlDataDb.getProductInEshops().add(entity);
        saveToXml();
    }

    protected void saveXmlEntity(ProductXmlEntity entity) {
        xmlDataDb.getProducts().add(entity);
        saveToXml();
    }

    protected void saveXmlEntity(EshopXmlEntity entity) {
        xmlDataDb.getEshops().add(entity);
        saveToXml();
    }

    protected void saveXmlEntity(GroupOfProductXmlEntity entity) {
        xmlDataDb.getGroupOfProducts().add(entity);
        saveToXml();
    }

    protected void updateXmlEntity(){
        saveToXml();
    }

    protected void saveXmlEntity(CategoryXmlEntity entity) {
        xmlDataDb.getCategories().add(entity);
        saveToXml();
    }

//    protected List<ProductInEshopXmlEntity> getProductsInEshopByProductId(Long id) {
//        List<ProductInEshopXmlEntity> result = new ArrayList<>();
//        for (ProductInEshopXmlEntity productInEshop : xmlDataDb.getProductInEshops()) {
//            if (productInEshop.getProduct().getId().equals(id)) {
//                result.add(productInEshop);
//            }
//        }
//        return result;
//    }


}
