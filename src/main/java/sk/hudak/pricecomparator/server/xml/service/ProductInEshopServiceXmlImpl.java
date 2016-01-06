package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.service.EshopService;
import sk.hudak.pricecomparator.middle.api.service.ProductInEshopService;
import sk.hudak.pricecomparator.middle.api.service.ProductService;
import sk.hudak.pricecomparator.middle.api.to.*;
import sk.hudak.pricecomparator.server.tasks.DownloaderEshopType;
import sk.hudak.pricecomparator.server.xml.model.EshopXmlEntity;
import sk.hudak.pricecomparator.server.xml.model.ProductInEshopXmlEntity;
import sk.hudak.pricecomparator.server.xml.service.comparator.EshopListDtoComparatorByName;

import java.util.*;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductInEshopServiceXmlImpl extends AbstracServiceXmlImpl implements ProductInEshopService {

    private EshopService eshopService = new EshopServiceXmlImpl();
    private ProductService productService = new ProductServiceXmlIml();

    @Override
    public Long createProductInEshop(ProductInEshopCreateDto dto) {
        ProductInEshopXmlEntity entity = new ProductInEshopXmlEntity();
        entity.setId(generateNewId());
        entity.setEshopId(dto.getEshopId());
        entity.setProductId(dto.getProductId());
        entity.setEshopProductPage(dto.getEshopProductPage());

        saveXmlEntity(entity);

        return entity.getId();
    }

    @Override
    public List<ProductInEshopListDto> getAllProductInEshop() {
        List<ProductInEshopXmlEntity> productInEshops = xmlDataDb.getProductInEshops();
        List<ProductInEshopListDto> result = new ArrayList<>(productInEshops.size());
        for (ProductInEshopXmlEntity productInEshop : productInEshops) {
            result.add(transformToProductInEshopListDto(productInEshop));
        }
        return result;
    }

    @Override
    public ProductInEshopDto getProductInEshop(Long productId, Long eshopId) {
        List<ProductInEshopXmlEntity> productInEshops = xmlDataDb.getProductInEshops();
        for (ProductInEshopXmlEntity productInEshop : productInEshops) {
            if (productInEshop.getEshopId().equals(eshopId) && productInEshop.getProductId().equals(productId)) {
                return transformToProductInEshopDto(productInEshop);
            }
        }
        return null;
    }

    private ProductInEshopDto transformToProductInEshopDto(ProductInEshopXmlEntity productInEshop) {
        if (productInEshop == null) {
            return null;
        }
        ProductInEshopDto result = new ProductInEshopDto();
        result.setId(productInEshop.getId());
        result.setProductId(productInEshop.getProductId());
        result.setEshopId(productInEshop.getEshopId());
        result.setEshopProductPage(productInEshop.getEshopProductPage());
        return result;
    }


    @Override
    public List<ProductInEshopListDto> getProductsInEshopByProductId(Long productId) {
        List<ProductInEshopListDto> result = new ArrayList<>();
        List<ProductInEshopXmlEntity> productInEshops = xmlDataDb.getProductInEshops();
        for (ProductInEshopXmlEntity productInEshop : productInEshops) {
            if (productInEshop.getProductId().equals(productId)) {
                result.add(transformToProductInEshopListDto(productInEshop));
            }
        }
        return result;
    }

    @Override
    public List<EshopListDto> getEshopsWithoutProduct(Long productId) {

        // zoznam eshopov s danym produktom
        Set<Long> eshopsId = new HashSet<>();
        List<ProductInEshopXmlEntity> productInEshops = xmlDataDb.getProductInEshops();
        for (ProductInEshopXmlEntity productInEshop : productInEshops) {
            if (productInEshop.getProductId().equals(productId)) {
                eshopsId.add(productInEshop.getEshopId());
            }
        }
        List<EshopListDto> result = new ArrayList<>();
        // zoznam vsetkych eshopov
        for (EshopXmlEntity eshop : xmlDataDb.getEshops()) {
            if (!eshopsId.contains(eshop.getId())) {
                EshopListDto dto = new EshopListDto();
                dto.setHomePage(eshop.getHomePage());
                dto.setName(eshop.getName());
                dto.setId(eshop.getId());
                result.add(dto);
            }
        }
        // sortovanie
        Collections.sort(result, new EshopListDtoComparatorByName());
        return result;
    }

    @Override
    public List<EshopListDto> getEshopsWithProduct(Long productId) {
        List<ProductInEshopXmlEntity> productInEshops = xmlDataDb.getProductInEshops();
        List<EshopListDto> result = new ArrayList<>();
        for (ProductInEshopXmlEntity productInEshop : productInEshops) {
            if (productInEshop.getProductId().equals(productId)) {
                EshopDto eshopById = eshopService.getEshopById(productInEshop.getEshopId());
                EshopListDto dto = new EshopListDto();
                dto.setHomePage(eshopById.getHomePage());
                dto.setName(eshopById.getName());
                dto.setId(eshopById.getId());
                result.add(dto);
            }
        }
        // sortovanie
        Collections.sort(result, new EshopListDtoComparatorByName());
        return result;
    }

    @Override
    public List<ProductInEshopDto> getProductsInEshopForDownloaderByProductId(Long productId) {
        List<ProductInEshopXmlEntity> productInEshops = xmlDataDb.getProductInEshops();
        List<ProductInEshopDto> result = new ArrayList<>();
        for (ProductInEshopXmlEntity productInEshop : productInEshops) {
            if (productInEshop.getProductId().equals(productId)) {
                ProductInEshopDto productInEshopDto = new ProductInEshopDto();
                productInEshopDto.setId(productInEshop.getId());
                productInEshopDto.setEshopId(productInEshop.getEshopId());
                productInEshopDto.setProductId(productInEshop.getProductId());
                productInEshopDto.setEshopProductPage(productInEshop.getEshopProductPage());
                result.add(productInEshopDto);
            }
        }
        return result;
    }

    @Override
    public List<ProductInEshopCustomListDto> getProductsInEshop(Long eshopId) {
        List<ProductInEshopXmlEntity> productInEshops = xmlDataDb.getProductInEshops();
        List<ProductInEshopCustomListDto> result = new ArrayList<>(productInEshops.size());
        for (ProductInEshopXmlEntity productInEshop : productInEshops) {
            if (productInEshop.getEshopId().equals(eshopId)) {
                ProductInEshopCustomListDto dto = new ProductInEshopCustomListDto();
                dto.setId(productInEshop.getId());
                dto.setEshopProductPage(productInEshop.getEshopProductPage());
                ProductDto product = productService.getProduct(productInEshop.getProductId());
                ProductListDto productListDto = new ProductListDto();
                productListDto.setId(product.getId());
                productListDto.setName(product.getName());
                productListDto.setImagePath(ImageUtils.findProductImage(product.getId()));
                dto.setProductListDto(productListDto);
                result.add(dto);
            }
        }
        // sortovanie
        Collections.sort(result, new ProductInEshopCustomListDtoComparatorByProductName());

        return result;
    }

    @Override
    public List<ProductInEshopDto> findProductInEshopForPriceUpdate(DownloaderEshopType eshopId) {
        //TODO
        return null;
    }

    private ProductInEshopListDto transformToProductInEshopListDto(ProductInEshopXmlEntity productInEshop) {
        ProductInEshopListDto listDto = new ProductInEshopListDto();
        listDto.setId(productInEshop.getId());
        listDto.setEshopId(productInEshop.getEshopId());
        listDto.setProductId(productInEshop.getProductId());
        listDto.setEshopProductPage(productInEshop.getEshopProductPage());
        return listDto;
    }


}
