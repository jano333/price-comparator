package sk.hudak.pricecomparator.server.service.internal;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.service.ProductInEshopService;
import sk.hudak.pricecomparator.middle.to.*;
import sk.hudak.pricecomparator.middle.to.internal.StepOneRequestDto;
import sk.hudak.pricecomparator.middle.to.internal.StepOneResponseDto;
import sk.hudak.pricecomparator.server.analyzator.StepOneProcessor;
import sk.hudak.pricecomparator.server.assembler.EshopAssembler;
import sk.hudak.pricecomparator.server.assembler.ProductInEshopAssembler;
import sk.hudak.pricecomparator.server.core.JefValidator;
import sk.hudak.pricecomparator.server.dao.ProductInEshopDao;
import sk.hudak.pricecomparator.server.facade.ProductInEshopFacade;
import sk.hudak.pricecomparator.server.model.EshopEntity;
import sk.hudak.pricecomparator.server.model.ProductEntity;
import sk.hudak.pricecomparator.server.model.ProductInEshopEntity;
import sk.hudak.pricecomparator.server.utils.ImageUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * DB sevice implementacia nad entitou {@link ProductInEshopEntity}.
 * <p/>
 * Created by jan on 30. 12. 2015.
 */
@Named
public class ProductInEshopServiceImpl implements ProductInEshopService {

    @Inject
    private ProductInEshopDao productInEshopDao;

    @Inject
    private ProductInEshopFacade productInEshopFacade;

    @Inject
    private ProductInEshopAssembler productInEshopAssembler;

    @Inject
    private EshopAssembler eshopAssembler;

    @Inject
    private JefValidator val;

    @Inject
    private StepOneProcessor stepOneProcessor;

    @Override
    public PageList<ProductInEshopPriceInfoListDto> findProductsInEshopPriceInfo(ProductInEshopFindDto findDto) {
        PageList<ProductInEshopEntity> productInEshopEntities = productInEshopDao.findProductsInEshop(findDto);
        return productInEshopAssembler.transformToPageListOfProductInEshopPriceInfoListDto(productInEshopEntities);
    }

    @Override
    public PageList<ProductInEshopPriceResultListDto> findPriceInfoInEshopsForProduct(ProductFindDto findDto) {
        PageList<ProductInEshopEntity> productInEshopEntities = productInEshopDao.findPriceInfoInEshopsForProduct(findDto);
        return productInEshopAssembler.transformToPageListOfProductInEshopPriceResultListDto(productInEshopEntities);
    }

    //------------------------------------------------------

    @Override
    public Long createProductInEshop(ProductInEshopCreateDto dto) {
        return productInEshopFacade.createProductInEshop(dto);
    }

    @Override
    public List<ProductInEshopListDto> findAllProductInEshop() {
        List<ProductInEshopEntity> allProductInEshop = productInEshopDao.findAllProductInEshop();
        return productInEshopAssembler.transformToListOfProductInEshopListDto(allProductInEshop);
    }

    @Override
    public ProductInEshopDto getProductInEshop(Long productId, Long eshopId) {
        val.notNull(productId, "productId is null");
        val.notNull(eshopId, "eshopId is null");

        ProductInEshopEntity productInEshopEntity = productInEshopDao.findProductInEshop(productId, eshopId);
        return productInEshopAssembler.transformToProductInEshopDto(productInEshopEntity);
    }

    @Override
    public List<EshopListDto> findEshopsWithProduct(Long productId) {
        val.notNull(productId, "productId is null");

        List<EshopEntity> eshopsWithProduct = productInEshopDao.findEshopsWithProduct(productId);
        return eshopAssembler.transformToListOfEshopListDto(eshopsWithProduct);
    }

    @Override
    public List<EshopListDto> findEshopsWithoutProduct(Long productId) {
        val.notNull(productId, "productId is null");

        List<EshopEntity> eshopsWithoutProduct = productInEshopDao.findEshopsWithoutProduct(productId);
        return eshopAssembler.transformToListOfEshopListDto(eshopsWithoutProduct);
    }

    @Override
    public List<ProductInEshopListDto> findProductsInEshopByProductId(Long productId) {
        val.notNull(productId, "productId is null");

        List<ProductInEshopEntity> productsInEshopByProductId = productInEshopDao.findProductsInEshopByProductId(productId);
        return productInEshopAssembler.transformToListOfProductInEshopListDto(productsInEshopByProductId);
    }

    @Override
    public List<ProductInEshopDto> findProductsInEshopForDownloaderByProductId(Long productId) {
        val.notNull(productId, "productId is null");

        List<ProductInEshopEntity> productsInEshopByProductId = productInEshopDao.findProductsInEshopByProductId(productId);
        return productInEshopAssembler.transformToListOfProductInEshopDto(productsInEshopByProductId);
    }

    @Override
    public List<ProductInEshopCustomListDto> findProductsInEshop(Long eshopId) {
        val.notNull(eshopId, "eshopId is null");

        List<ProductInEshopEntity> productsInEshop = productInEshopDao.old_findProductsInEshop(eshopId);
        return productInEshopAssembler.transformToListOfProductInEshopCustomListDto(productsInEshop);
    }

    @Override
    public List<ProductInEshopPriceInfoListDto> findProductInEshopPriceInfoForEshop(Long eshopId) {
        List<ProductInEshopEntity> productInEshopEntities = productInEshopDao.old_findProductsInEshop(eshopId);
        return productInEshopAssembler.transformToListOfProductInEshopPriceInfoListDto(productInEshopEntities);
    }

    @Override
    public ProductInEshopDto findProductForPriceUpdate(EshopType eshopType) {
        ProductInEshopEntity productInEshop = productInEshopFacade.findProductForPriceUpdate(eshopType);
        return productInEshopAssembler.transformToProductInEshopDto(productInEshop);

    }

    @Override
    public void updateProductInEshopPrice(ProductInEshopPriceUpdateDto updateDto) {
        productInEshopFacade.updateProductInEshopPrice(updateDto);
    }

    @Override
    public boolean existProductWithGivenUrl(String productUrl) {
        return productInEshopFacade.existProductWithGivenUrl(productUrl);
    }

    @Override
    public StepOneResponseDto analyzeProductUrl(StepOneRequestDto stepOneRequestDto) throws PriceComparatorBusinesException {
        return stepOneProcessor.process(stepOneRequestDto);
    }

    @Override
    public ProductInEshopForPictureDownloadInfoDto findUrlOfProductsInEshopWithoutPicture(EshopType eshopType) {

        List<ProductInEshopEntity> productInEshopEntities = productInEshopDao.findProductsInEshopByType(eshopType);

        for (ProductInEshopEntity productInEshopEntity : productInEshopEntities) {
            ProductEntity product = productInEshopEntity.getProduct();
            //FIXME
            String productImage = ImageUtils.findProductImage("C:\\price-comparator\\feedo\\pictures\\", product.getId());
            if (productImage == null) {
                return productInEshopAssembler.transformToProductInEshopForPictureDownloadInfoDto(productInEshopEntity);
            }
        }
        return null;
    }


    @Override
    @Deprecated
    public List<ProductInEshopPriceInfoListDto> old_findProductsInEshopPriceInfo(ProductInEshopFindDto findDto) {
        List<ProductInEshopEntity> productInEshopEntities = productInEshopDao.old_findProductsInEshop(findDto);
        return productInEshopAssembler.transformToListOfProductInEshopPriceInfoListDto(productInEshopEntities);
    }

//        //TODO impl
//        ProductInEshopForPictureDownloadInfoDto result = new ProductInEshopForPictureDownloadInfoDto();
//        result.setProductId(2l);
//        result.setProductInEshopUrl("https://www.feedo.sk/2x-lovela-sensitive-5-l-darcek-lovela-avivaz-2l-zadarmo/");
//
//        return result;
//    }

    //    @Override
//    public List<ProductInEshopPriceResultListDto> old_findPriceInfoInEshopsForProduct(Long productId) {
//        //FIXME vstup dat finDto aby bola moznost nastavovat filtrovanie, vraciat paging nie list
//        List<ProductInEshopEntity> productInEshopEntities = productInEshopDao.old_findPriceInfoInEshopsForProduct(productId);
//        //FIXME premenovat transform metodu
//        return productInEshopAssembler.transformToListOfProductInEshopEntity(productInEshopEntities);

}
