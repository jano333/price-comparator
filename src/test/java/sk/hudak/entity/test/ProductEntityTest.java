package sk.hudak.entity.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import sk.hudak.pricecomparator.middle.api.canonical.Unit;
import sk.hudak.pricecomparator.middle.api.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.api.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jan on 30. 12. 2015.
 */
@Test
@ContextConfiguration(locations = {"classpath:ctx-server.xml"})
public class ProductEntityTest extends AbstractTestNGSpringContextTests {
    @Inject
    private PriceComparatorService service;

    @Test(priority = 1)
    public void testCreateAndRead() {

        ProductCreateDto dto = new ProductCreateDto();
        dto.setName("TestName");
        dto.setCountOfUnit(new BigDecimal(2));
        dto.setUnit(Unit.KUS);
        dto.setCountOfItemInOnePackage(1);
        Long product = service.createProduct(dto);
        Assert.assertNotNull(product);

        List<ProductListDto> allProducts = service.getAllProduct();
        Assert.assertNotNull(allProducts);
        Assert.assertTrue(allProducts.get(0) != null);
    }
}