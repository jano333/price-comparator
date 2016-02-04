package sk.hudak.pricecomparator.middle.service;

import sk.hudak.pricecomparator.middle.to.TescoProductInfoDto;

import java.util.List;

/**
 * Created by jan on 6. 12. 2015.
 */
public interface TescoService {

    List<TescoProductInfoDto> getAllUnprocessedProducts();
}
