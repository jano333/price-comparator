package sk.hudak.pricecomparator.middle.service;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.to.eshop.*;

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

    //TODO UPDATE

    /**
     * @param eshopId
     * @return
     */
    EshopDto getEshop(Long eshopId);

    //TODO DELETE

    /**
     * @param findDto
     * @return
     */
    PageList<EshopListDto> findEshops(EshopFindDto findDto);

    /**
     * Table lazy loading
     *
     * @param eshopId
     * @return
     */
    EshopListDto getEshopListById(Long eshopId);

    /**
     * @return
     */
    List<EshopIdNameDto> findAllEshopsForSelection();

    /**
     * @param eshopId
     * @return
     */
    EshopIdNameDto getEshopIdNameById(Long eshopId);

    /**
     * @return
     */
    List<EshopHomePageInfoDto> findAllHomePages();


}
