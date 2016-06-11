package sk.hudak.entity.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.to.ProductListDto;

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
        Long product = null;
        try {
            product = service.createProduct(dto);
        } catch (PriceComparatorBusinesException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(product);

        List<ProductListDto> allProducts = service.findAllProduct();
        Assert.assertNotNull(allProducts);
        Assert.assertTrue(allProducts.get(0) != null);
    }
}
