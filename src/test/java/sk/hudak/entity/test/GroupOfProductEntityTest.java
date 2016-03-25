package sk.hudak.entity.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import sk.hudak.pricecomparator.middle.canonical.Unit;
import sk.hudak.pricecomparator.middle.service.PriceComparatorService;
import sk.hudak.pricecomparator.middle.to.GroupOfProductCreateDto;
import sk.hudak.pricecomparator.middle.to.GroupOfProductDto;
import sk.hudak.pricecomparator.middle.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.to.ProductListDto;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jan on 1. 1. 2016.
 */
@Test
@ContextConfiguration(locations = {"classpath:ctx-server.xml"})
public class GroupOfProductEntityTest extends AbstractTestNGSpringContextTests {

    @Inject
    private PriceComparatorService service;

    @Test(priority = 1)
    public void testCreateAndRead() {
        ProductCreateDto productCreateDto = new ProductCreateDto();
        productCreateDto.setName("Ariel");
        productCreateDto.setCountOfUnit(new BigDecimal(2));
        productCreateDto.setUnit(Unit.KUS);
        productCreateDto.setCountOfItemInOnePackage(1);
        Long productId1 = service.createProduct(productCreateDto);

        productCreateDto = new ProductCreateDto();
        productCreateDto.setName("Palmex");
        productCreateDto.setCountOfUnit(new BigDecimal(9));
        productCreateDto.setUnit(Unit.KUS);
        productCreateDto.setCountOfItemInOnePackage(1);
        Long productId2 = service.createProduct(productCreateDto);

        System.out.println("-----------");

        GroupOfProductCreateDto createDto = new GroupOfProductCreateDto();
        createDto.setName("Dospely praci prasok");
        Long groupOfProductId = service.createGroupOfProduct(createDto);
        Assert.assertNotNull(groupOfProductId);

        Set<Long> productIdSet = new HashSet<>();
        productIdSet.add(productId1);
        service.addProductsToGroup(productIdSet, groupOfProductId);

        GroupOfProductDto groupOfProduct = service.findGroupOfProductById(groupOfProductId);
        Assert.assertNotNull(groupOfProductId);
        List<Long> productIds = groupOfProduct.getProductIds();
        Assert.assertNotNull(groupOfProductId);
        Assert.assertFalse(productIds.isEmpty());

        List<ProductListDto> productsInGroup = service.findProductsInGroup(groupOfProductId);
        Assert.assertTrue(productsInGroup.size() == 1);

        List<ProductListDto> productsNotInGroup = service.findProductsNotInGroup(groupOfProductId);
        Assert.assertTrue(productsNotInGroup.size() == 1);


    }
}
