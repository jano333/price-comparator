package sk.hudak.pricecomparator.middle.api.service;

import sk.hudak.pricecomparator.middle.api.to.EshopCreateDto;
import sk.hudak.pricecomparator.middle.api.to.EshopDto;
import sk.hudak.pricecomparator.middle.api.to.EshopListDto;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface EshopService {

    Long createEshop(EshopCreateDto dto);

    EshopDto getEshopById(Long eshopId);

    List<EshopListDto> getAllEshops();


}
