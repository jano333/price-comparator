package sk.hudak.pricecomparator.middle.service;

import sk.hudak.pricecomparator.middle.to.EshopCreateDto;
import sk.hudak.pricecomparator.middle.to.EshopDto;
import sk.hudak.pricecomparator.middle.to.EshopIdNameDto;
import sk.hudak.pricecomparator.middle.to.EshopListDto;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface EshopService {

    Long createEshop(EshopCreateDto dto);

    EshopDto getEshopById(Long eshopId);

    List<EshopListDto> getAllEshops();

    List<EshopIdNameDto> getAllEshopsForSelection();


}
