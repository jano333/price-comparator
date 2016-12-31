package sk.hudak.entity.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.eshop.EshopCreateDto;

import javax.inject.Inject;

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
        Long eshop = null;
        try {
            eshop = service.createEshop(dto);
        } catch (PriceComparatorBusinesException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(eshop);

//        List<EshopListDto> allEshops = service.findAllEshops();
//        Assert.assertNotNull(allEshops);
//        Assert.assertTrue(allEshops.get(0) != null);
    }
}
