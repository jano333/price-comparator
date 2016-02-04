package sk.hudak.entity.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.EshopCreateDto;
import sk.hudak.pricecomparator.middle.to.EshopListDto;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by jan on 27. 12. 2015.
 */
@Test
@ContextConfiguration(locations = {"classpath:ctx-server.xml"})
public class EshopEntityTest extends AbstractTestNGSpringContextTests {

    @Inject
    private PriceComparatorService service;

    @Test(priority = 1)
    public void testCreateAndRead() {

        EshopCreateDto dto = new EshopCreateDto();
        dto.setName("TestName");
        dto.setHomePage("TestHomePage");
        Long eshop = service.createEshop(dto);
        Assert.assertNotNull(eshop);

        List<EshopListDto> allEshops = service.getAllEshops();
        Assert.assertNotNull(allEshops);
        Assert.assertTrue(allEshops.get(0) != null);
    }
}
