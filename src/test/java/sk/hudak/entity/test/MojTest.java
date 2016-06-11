package sk.hudak.entity.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.ProductIdsFindDto;
import sk.hudak.pricecomparator.middle.to.ResponseDto;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 11. 6. 2016.
 */
@Test
@ContextConfiguration(locations = {"classpath:ctx-server.xml"})
//FIXME premenovat test
public class MojTest extends AbstractTestNGSpringContextTests {
    @Inject
    private PriceComparatorService service;

    @Test(priority = 1)
    public void testCreateAndRead() {

        ProductIdsFindDto productIdsFindDto = new ProductIdsFindDto();
        productIdsFindDto.setOffset(0);
        productIdsFindDto.setCount(20);

        List<Long> productIds = new ArrayList<>();
        productIds.add(322L);
        productIds.add(320L);
        productIds.add(319L);
        productIdsFindDto.setProductIds(productIds);

        PageList<ResponseDto> productInEshopsForProductIds = service.findProductInEshopsForProductIds(productIdsFindDto);

        System.out.println(productInEshopsForProductIds);
    }
}
