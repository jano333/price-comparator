package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.service.EshopService;
import sk.hudak.pricecomparator.middle.api.to.EshopCreateDto;
import sk.hudak.pricecomparator.middle.api.to.EshopDto;
import sk.hudak.pricecomparator.middle.api.to.EshopListDto;

import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public class EshopServiceXmlImpl extends AbstracServiceXmlImpl implements EshopService {

    @Override
    public Long createEshop(EshopCreateDto dto) {
        return null;
    }

    @Override
    public EshopDto getEshopById(Long eshopId) {
        return null;
    }

    @Override
    public List<EshopListDto> getAllEshops() {
        return null;
    }


}
