package sk.hudak.pricecomparator.server.service.internal;

import sk.hudak.pricecomparator.middle.service.EshopService;
import sk.hudak.pricecomparator.middle.to.EshopCreateDto;
import sk.hudak.pricecomparator.middle.to.EshopDto;
import sk.hudak.pricecomparator.middle.to.EshopIdNameDto;
import sk.hudak.pricecomparator.middle.to.EshopListDto;
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
    public Long createEshop(EshopCreateDto dto) {
        return eshopFacade.createEshop(dto);
    }

    @Override
    public EshopDto getEshopById(Long eshopId) {
        return eshopAssembler.transformToEshopDto(eshopDao.readMandatory(eshopId));
    }

    @Override
    public List<EshopListDto> getAllEshops() {
        return eshopAssembler.transformToListOfEshopListDto(eshopDao.getAllEshops());
    }

    @Override
    public List<EshopIdNameDto> getAllEshopsForSelection() {
        return eshopAssembler.transformToListOfEshopIdNameDto(eshopDao.getAllEshops());
    }
}
