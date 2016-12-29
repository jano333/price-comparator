package sk.hudak.pricecomparator.server.service;

import sk.hudak.pricecomparator.server.to.NewProductInfoDto;

import java.util.List;

/**
 * Created by jan on 29. 12. 2016.
 */
public interface InternalTxService {

    List<String> getListOfSearchQueries();

    void addNewProducts(List<NewProductInfoDto> result);
}
