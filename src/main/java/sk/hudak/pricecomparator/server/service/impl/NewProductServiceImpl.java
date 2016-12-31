package sk.hudak.pricecomparator.server.service.impl;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.domain.facade.NewProductFactory;
import sk.hudak.pricecomparator.server.service.NewProductService;
import sk.hudak.pricecomparator.server.to.NewProductCreateDto;

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

    @Override
    public void addNewProducts(List<NewProductCreateDto> result, EshopType eshopType) {
        //TODO vstupne paramtre pozor ak je null alebo je prazdna... tak nerob nic? alebo vynimka

        for (NewProductCreateDto newProductCreateDto : result) {
            newProductFactory.createIfNotExistYet(newProductCreateDto, eshopType);
        }
    }
}
