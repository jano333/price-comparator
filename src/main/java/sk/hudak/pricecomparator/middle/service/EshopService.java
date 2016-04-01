package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.to.*;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface EshopService {

    PageList<EshopListDto> findEshops(EshopFindDto filter);

    //TODO prejset

    Long createEshop(EshopCreateDto dto);

    EshopDto getEshopById(Long eshopId);

    List<EshopIdNameDto> findAllEshopsForSelection();


    @Deprecated
    List<EshopListDto> findAllEshops();
}
