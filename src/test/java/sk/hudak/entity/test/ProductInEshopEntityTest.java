package sk.hudak.entity.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.service.ProductInEshopService;
import sk.hudak.pricecomparator.middle.to.eshop.EshopCreateDto;
import sk.hudak.pricecomparator.middle.to.eshop.EshopListDto;
import sk.hudak.pricecomparator.middle.to.product.ProductCreateDto;
import sk.hudak.pricecomparator.middle.to.productineshop.ProductInEshopCreateDto;
import sk.hudak.pricecomparator.middle.to.productineshop.ProductInEshopCustomListDto;
import sk.hudak.pricecomparator.middle.to.productineshop.ProductInEshopListDto;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jan on 31. 12. 2015.
 */
@Test
@ContextConfiguration(locations = {"classpath:ctx-server.xml"})
public class ProductInEshopEntityTest extends AbstractTestNGSpringContextTests {

    @Inject
    private PriceComparatorService service;

    @Test(priority = 1)
    public void testCreateAndRead() {

        EshopCreateDto eshopCreateDto = new EshopCreateDto();
        eshopCreateDto.setName("Tesco");
        eshopCreateDto.setHomePage("TestHomePage");
        Long eshopId = null;
        try {
            eshopId = service.createEshop(eshopCreateDto);
        } catch (PriceComparatorBusinesException e) {
            e.printStackTrace();
        }

        ProductCreateDto productCreateDto = new ProductCreateDto();
        productCreateDto.setName("Ariel");
        productCreateDto.setCountOfUnit(new BigDecimal(2));
        productCreateDto.setUnit(Unit.KUS);
        productCreateDto.setCountOfItemInOnePackage(1);
        Long productId = null;
        try {
            productId = service.createProduct(productCreateDto);
        } catch (PriceComparatorBusinesException e) {
            e.printStackTrace();
        }

        ProductInEshopCreateDto createDto = new ProductInEshopCreateDto();
        createDto.setProductId(productId);
        createDto.setEshopId(eshopId);
        createDto.setEshopProductPage("http://www.tesco.sk");
        Long productInEshopId = null;
        try {
            productInEshopId = service.createProductInEshop(createDto);
        } catch (PriceComparatorBusinesException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(productInEshopId);

        ProductInEshopService ser = service;
        List<ProductInEshopListDto> productsInEshopByProductId = ser.findProductsInEshopByProductId(productId);
        Assert.assertNotNull(productsInEshopByProductId);

        List<ProductInEshopCustomListDto> productsInEshop = ser.findProductsInEshop(eshopId);
        Assert.assertNotNull(productsInEshop);

        List<EshopListDto> eshopsWithProduct = ser.findEshopsWithProduct(productId);
        Assert.assertNotNull(eshopsWithProduct);

        List<EshopListDto> eshopsWithoutProduct = ser.findEshopsWithoutProduct(productId);
        Assert.assertNotNull(eshopsWithoutProduct);


    }
}
