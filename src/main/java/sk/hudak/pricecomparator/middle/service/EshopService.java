package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.*;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public interface EshopService {

    /**
     * @param createDto
     * @return
     * @throws PriceComparatorBusinesException
     */
    Long createEshop(EshopCreateDto createDto) throws PriceComparatorBusinesException;

    /**
     * @param eshopId
     * @return
     */
    EshopDto getEshopById(Long eshopId);

    /**
     * @param eshopId
     * @return
     */
    EshopListDto getEshopListById(Long eshopId);

    /**
     * @param eshopId
     * @return
     */
    EshopIdNameDto getEshopIdNameById(Long eshopId);

    /**
     * @param findDto
     * @return
     */
    PageList<EshopListDto> findEshops(EshopFindDto findDto);

    /**
     * @return
     */
    List<EshopHomePageInfoDto> findAllHomePages();

    /**
     * @return
     */
    List<EshopIdNameDto> findAllEshopsForSelection();

    /**
     * @return
     */
    @Deprecated
    List<EshopListDto> findAllEshops();
}
