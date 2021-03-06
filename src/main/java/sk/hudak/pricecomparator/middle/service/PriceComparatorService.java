package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.paging.PageData;
import sk.hudak.pricecomparator.middle.to.NewProductFindDto;
import sk.hudak.pricecomparator.middle.to.NewProductListDto;
import sk.hudak.pricecomparator.server.to.NewProductStatus;

/**
 * Created by jan on 15. 10. 2015.
 */
public interface PriceComparatorService extends
        EshopService,
        CategoryService,
        ProductService,
        ProductInEshopService,
        GroupOfProductService {

    String SERVICE_NAME = "PriceComparatorService";


    // new product service
    PageData<NewProductListDto> findNewProducts(NewProductFindDto filter);

    NewProductListDto getNewProductListDtoById(Long newProductId);

    void changeNewProductStatus(Long newProductId, NewProductStatus newProductStatus);
}
