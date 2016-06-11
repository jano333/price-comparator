package sk.hudak.pricecomparator.server.service.internal;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.exeption.PriceComparatorBusinesException;
import sk.hudak.pricecomparator.middle.service.EshopService;
import sk.hudak.pricecomparator.middle.to.*;
import sk.hudak.pricecomparator.server.assembler.EshopAssembler;
import sk.hudak.pricecomparator.server.dao.EshopDao;
import sk.hudak.pricecomparator.server.facade.EshopFacade;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by jan on 27. 12. 2015.
 */
@Named
public class EshopServiceImpl implements EshopService {

    @Inject
    private EshopFacade eshopFacade;

    @Inject
    private EshopDao eshopDao;

    @Inject
    private EshopAssembler eshopAssembler;

    @Override
    public PageList<EshopListDto> findEshops(EshopFindDto findDto) {
        return eshopAssembler.transformToPageListOfEshopListDto(eshopDao.findEshops(findDto));
    }

    @Override
    public List<EshopHomePageInfoDto> findAllHomePages() {
        return eshopAssembler.transformToListOfEshopHomePageInfoDto(eshopDao.findAllEshops());
    }

    @Override
    public Long createEshop(EshopCreateDto createDto) throws PriceComparatorBusinesException {
        return eshopFacade.createEshop(createDto);
    }

    @Override
    public EshopDto getEshopById(Long eshopId) {
        return eshopAssembler.transformToEshopDto(eshopDao.readMandatory(eshopId));
    }

    @Override
    public List<EshopListDto> findAllEshops() {
        return eshopAssembler.transformToListOfEshopListDto(eshopDao.findAllEshops());
    }

    @Override
    public EshopListDto getEshopListById(Long eshopId) {
        return eshopAssembler.transformToEshopListDto(eshopDao.readMandatory(eshopId));
    }

    @Override
    public EshopIdNameDto getEshopIdNameById(Long eshopId) {
        return eshopAssembler.transformToEshopIdNameDto(eshopDao.readMandatory(eshopId));
    }

    @Override
    public List<EshopIdNameDto> findAllEshopsForSelection() {
        return eshopAssembler.transformToListOfEshopIdNameDto(eshopDao.findAllEshops());
    }
}
