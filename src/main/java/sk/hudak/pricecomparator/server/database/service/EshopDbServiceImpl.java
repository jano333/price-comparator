package sk.hudak.pricecomparator.server.database.service;

import sk.hudak.pricecomparator.middle.api.service.EshopService;
import sk.hudak.pricecomparator.middle.api.to.EshopCreateDto;
import sk.hudak.pricecomparator.middle.api.to.EshopDto;
import sk.hudak.pricecomparator.middle.api.to.EshopListDto;
import sk.hudak.pricecomparator.server.database.assembler.EshopAssembler;
import sk.hudak.pricecomparator.server.database.dao.EshopDao;
import sk.hudak.pricecomparator.server.database.facade.EshopFacade;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by jan on 27. 12. 2015.
 */
@Named
public class EshopDbServiceImpl implements EshopService {

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
        //TODO
        return null;
    }
}
