package sk.hudak.entity.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import sk.hudak.pricecomparator.middle.api.canonical.Unit;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.service.ProductInEshopService;
import sk.hudak.pricecomparator.middle.api.to.*;

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
        Long eshopId = service.createEshop(eshopCreateDto);

        ProductCreateDto productCreateDto = new ProductCreateDto();
        productCreateDto.setName("Ariel");
        productCreateDto.setCountOfUnit(new BigDecimal(2));
        productCreateDto.setUnit(Unit.KUS);
        productCreateDto.setCountOfItemInOnePackage(1);
        Long productId = service.createProduct(productCreateDto);

        ProductInEshopCreateDto createDto = new ProductInEshopCreateDto();
        createDto.setProductId(productId);
        createDto.setEshopId(eshopId);
        createDto.setEshopProductPage("http://www.tesco.sk");
        Long productInEshopId = service.createProductInEshop(createDto);
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
