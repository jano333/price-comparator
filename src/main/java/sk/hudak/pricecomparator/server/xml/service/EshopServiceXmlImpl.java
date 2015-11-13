package sk.hudak.pricecomparator.server.xml.service;

import sk.hudak.pricecomparator.middle.api.exeption.PriceComparatorException;
import sk.hudak.pricecomparator.middle.api.service.EshopService;
import sk.hudak.pricecomparator.middle.api.to.EshopCreateDto;
import sk.hudak.pricecomparator.middle.api.to.EshopDto;
import sk.hudak.pricecomparator.middle.api.to.EshopListDto;
import sk.hudak.pricecomparator.server.xml.model.EshopXmlEntity;
import sk.hudak.pricecomparator.server.xml.service.comparator.EshopListDtoComparatorByName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jan on 14. 10. 2015.
 */
public class EshopServiceXmlImpl extends AbstracServiceXmlImpl implements EshopService {

    @Override
    public Long createEshop(EshopCreateDto dto) {
        //TODO validacia

        EshopXmlEntity eshopXmlEntity = new EshopXmlEntity();
        eshopXmlEntity.setId(generateNewId());
        eshopXmlEntity.setName(dto.getName());
        eshopXmlEntity.setHomePage(dto.getHomePage());
        eshopXmlEntity.setParserClassName(dto.getParserClassName());

        saveXmlEntity(eshopXmlEntity);

        return eshopXmlEntity.getId();
    }

    @Override
    public EshopDto getEshopById(Long eshopId) {
        //TODO validacia

        for (EshopXmlEntity eshop : xmlDataDb.getEshops()) {
            if (eshop.getId().equals(eshopId)) {
                EshopDto dto = new EshopDto();
                dto.setId(eshop.getId());
                dto.setName(eshop.getName());
                dto.setHomePage(eshop.getHomePage());
                dto.setParserClassName(eshop.getParserClassName());
                return dto;
            }
        }
        throw new PriceComparatorException("eshop with id " + eshopId + " not found");
    }

    @Override
    public List<EshopListDto> getAllEshops() {
        List<EshopXmlEntity> eshops = xmlDataDb.getEshops();
        List<EshopListDto> result = new ArrayList<>(eshops.size());
        for (EshopXmlEntity eshop : eshops) {
            EshopListDto eshopListDto = new EshopListDto();
            eshopListDto.setId(eshop.getId());
            eshopListDto.setName(eshop.getName());
            eshopListDto.setHomePage(eshop.getHomePage());
            result.add(eshopListDto);
        }
        Collections.sort(result, new EshopListDtoComparatorByName());

        return result;
    }


}
