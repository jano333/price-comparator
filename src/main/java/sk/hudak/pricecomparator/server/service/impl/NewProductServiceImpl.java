package sk.hudak.pricecomparator.server.service.impl;

import sk.hudak.jef.paging.PageData;
import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.to.NewProductFindDto;
import sk.hudak.pricecomparator.middle.to.NewProductListDto;
import sk.hudak.pricecomparator.server.domain.assembler.NewProductAssembler;
import sk.hudak.pricecomparator.server.domain.dao.NewProductDao;
import sk.hudak.pricecomparator.server.domain.facade.NewProductFactory;
import sk.hudak.pricecomparator.server.service.NewProductService;
import sk.hudak.pricecomparator.server.to.NewProductCreateDto;
import sk.hudak.pricecomparator.server.to.NewProductStatus;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by jan on 29. 12. 2016.
 */
@Named
public class NewProductServiceImpl implements NewProductService {

    @Inject
    private NewProductFactory newProductFactory;

    @Inject
    private NewProductDao newProductDao;

    @Inject
    private NewProductAssembler newProductAssembler;

    @Override
    public void addNewProducts(List<NewProductCreateDto> result, EshopType eshopType) {
        //TODO vstupne paramtre pozor ak je null alebo je prazdna... tak nerob nic? alebo vynimka

        for (NewProductCreateDto newProductCreateDto : result) {
            newProductFactory.createIfNotExistYet(newProductCreateDto, eshopType);
        }
    }

    @Override
    public PageData<NewProductListDto> findNewProducts(NewProductFindDto findDto) {
        return newProductAssembler.transformToPageDataOfNewProductListDto(newProductDao.findNewProducts(findDto));
    }

    @Override
    public NewProductListDto getNewProductListDtoById(Long newProductId) {
        return newProductAssembler.transformToNewProductListDto(newProductDao.readMandatory(newProductId));
    }

    @Override
    public void changeNewProductStatus(Long newProductId, NewProductStatus newProductStatus) {
        if (newProductStatus == null) {
            throw new PriceComparatorException("status is null");
        }
        newProductFactory.changeNewProductStatus(newProductId, newProductStatus);
    }
}
