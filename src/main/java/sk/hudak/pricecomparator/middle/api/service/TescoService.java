package sk.hudak.pricecomparator.middle.api.service;

import sk.hudak.pricecomparator.middle.api.to.TescoProductInfoDto;

import java.util.List;

/**
 * Created by jan on 6. 12. 2015.
 */
public interface TescoService {

    List<TescoProductInfoDto> getAllUnprocessedProducts();
}
