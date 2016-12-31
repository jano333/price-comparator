package sk.hudak.pricecomparator.server.domain.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.service.ProductService;
import sk.hudak.pricecomparator.middle.to.product.*;
import sk.hudak.pricecomparator.server.domain.assembler.ProductAssembler;
import sk.hudak.pricecomparator.server.domain.dao.ProductDao;
import sk.hudak.pricecomparator.server.domain.facade.ProductFacade;

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
    public Long createProduct(ProductCreateDto createDto) throws PriceComparatorBusinesException {
        return productFacade.createProduct(createDto);
    }

    @Override
    public void updateProduct(ProductUpdateDto updateDto) throws PriceComparatorBusinesException {
        productFacade.updateProduct(updateDto);
    }

    @Override
    public ProductDto getProduct(Long productId) {
        return productAssembler.transformToProductDto(productDao.readMandatory(productId));
    }

    @Override
    public PageList<ProductListDto> findProducts(ProductFindDto filter) {
        return productAssembler.transformToPageListOfProductListDto(productDao.findProducts(filter));
    }

    @Override
    public List<ProductIdNameDto> findAllProductForSelection() {
        return productAssembler.transformToListOfProductIdNameDto(productDao.findAllProducts());
    }

    @Override
    public ProductListDto getProductListById(Long productId) {
        return productAssembler.transformToProductListDto(productDao.readMandatory(productId));
    }

    @Override
    public ProductIdNameDto getProductIdNameById(Long productId) {
        return productAssembler.transformToProductIdNameDto(productDao.readMandatory(productId));
    }
}
