package sk.hudak.pricecomparator.server.service.internal;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.service.ProductService;
import sk.hudak.pricecomparator.middle.to.*;
import sk.hudak.pricecomparator.server.assembler.ProductAssembler;
import sk.hudak.pricecomparator.server.dao.ProductDao;
import sk.hudak.pricecomparator.server.facade.ProductFacade;
import sk.hudak.pricecomparator.server.model.ProductEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by jan on 29. 12. 2015.
 */
@Named
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductFacade productFacade;

    @Inject
    private ProductDao productDao;

    @Inject
    private ProductAssembler productAssembler;

    @Override
    public List<ProductIdNameDto> findAllProductForSelection() {
        return productAssembler.transformToListOfProductIdNameDto(productDao.findAllProducts());
    }

    @Override
    public PageList<ProductListDto> findProducts(ProductFindDto filter) {
        return productAssembler.transformToPageListOfProductListDto(productDao.findProducts(filter));
    }

    @Override
    public ProductListDto getProductListDtoById(Long productId) {
        ProductEntity productEntity = productDao.readMandatory(productId);
        return productAssembler.transformToProductListDto(productEntity);
    }

    @Override
    public Long createProduct(ProductCreateDto createDto) {
        return productFacade.createProduct(createDto);
    }

    @Override
    public void updateProduct(ProductUpdateDto updateDto) {
        productFacade.updateProduct(updateDto);
    }

    @Override
    public ProductDto getProduct(Long productId) {
        return productAssembler.transformToProductDto(productDao.readMandatory(productId));
    }

    @Override
    public List<ProductListDto> getAllProduct() {
        return productAssembler.transformToListOfProductListDto(productDao.findAllProducts());
    }

    @Override
    public ProductIdNameDto getProductIdNameDto(Long productId) {
        return productAssembler.transformToProductIdNameDto(productDao.readMandatory(productId));
    }
}
