package sk.hudak.pricecomparator.server.service;

import sk.hudak.pricecomparator.middle.canonical.EshopType;
import sk.hudak.pricecomparator.server.to.NewProductCreateDto;

import java.util.List;

/**
 * Created by jan on 29. 12. 2016.
 */
public interface NewProductService {

    void addNewProducts(List<NewProductCreateDto> result, EshopType eshopType);
}
