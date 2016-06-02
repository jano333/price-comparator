package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.to.*;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface EshopService {

    Long createEshop(EshopCreateDto dto);

    PageList<EshopListDto> findEshops(EshopFindDto filter);

    //TODO prejset

    List<EshopHomePageInfoDto> findAllHomePages();

    EshopDto getEshopById(Long eshopId);

    List<EshopIdNameDto> findAllEshopsForSelection();

    @Deprecated
    List<EshopListDto> findAllEshops();

    EshopListDto getEshopListDtoById(Long eshopId);

    EshopIdNameDto getEshopIdNameDto(Long eshopId);
}
