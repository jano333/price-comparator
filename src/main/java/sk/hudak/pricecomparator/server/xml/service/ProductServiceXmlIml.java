package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.service.ProductService;
import sk.hudak.pricecomparator.middle.api.to.ProductCreateDto;
import sk.hudak.pricecomparator.middle.api.to.ProductDto;
import sk.hudak.pricecomparator.middle.api.to.ProductEditDto;
import sk.hudak.pricecomparator.middle.api.to.ProductListDto;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public class ProductServiceXmlIml extends AbstracServiceXmlImpl implements ProductService {

    @Override
    public Long createProduct(ProductCreateDto dto) {
        return null;
    }

    @Override
    public void updateProduct(ProductEditDto editDto) {
        //TODO
    }

    @Override
    public ProductDto getProduct(Long productId) {
        return null;
    }


    @Override
    public List<ProductListDto> getAllProduct() {
        return null;
    }


}
