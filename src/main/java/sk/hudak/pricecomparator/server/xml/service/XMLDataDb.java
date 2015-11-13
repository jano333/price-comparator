package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.server.xml.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 15. 10. 2015.
 */
public class XMLDataDb {

    private List<EshopXmlEntity> eshops = new ArrayList<>();
    private List<CategoryXmlEntity> categories = new ArrayList<>();
    private List<ProductXmlEntity> products = new ArrayList<>();
    private List<ProductInEshopXmlEntity> productInEshops = new ArrayList<>();
    private List<GroupOfProductXmlEntity> groupOfProducts = new ArrayList<>();

    public List<GroupOfProductXmlEntity> getGroupOfProducts() {
        return groupOfProducts;
    }

    public void setGroupOfProducts(List<GroupOfProductXmlEntity> groupOfProducts) {
        this.groupOfProducts = groupOfProducts;
    }

    public List<EshopXmlEntity> getEshops() {
        return eshops;
    }

    public void setEshops(List<EshopXmlEntity> eshops) {
        this.eshops = eshops;
    }

    public List<CategoryXmlEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryXmlEntity> categories) {
        this.categories = categories;
    }

    public List<ProductXmlEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductXmlEntity> products) {
        this.products = products;
    }

    public List<ProductInEshopXmlEntity> getProductInEshops() {
        return productInEshops;
    }

    public void setProductInEshops(List<ProductInEshopXmlEntity> productInEshops) {
        this.productInEshops = productInEshops;
    }
}
