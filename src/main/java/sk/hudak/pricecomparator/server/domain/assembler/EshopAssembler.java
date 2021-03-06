package sk.hudak.pricecomparator.server.domain.assembler;

import sk.hudak.jef.PageList;
import sk.hudak.pricecomparator.middle.to.eshop.EshopDto;
import sk.hudak.pricecomparator.middle.to.eshop.EshopHomePageInfoDto;
import sk.hudak.pricecomparator.middle.to.eshop.EshopIdNameDto;
import sk.hudak.pricecomparator.middle.to.eshop.EshopListDto;
import sk.hudak.pricecomparator.server.domain.model.EshopEntity;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 27. 12. 2015.
 */
@Named
public class EshopAssembler {


    public EshopDto transformToEshopDto(EshopEntity eshopEntity) {
        if (eshopEntity == null) {
            return null;
        }

        EshopDto dto = new EshopDto();
        dto.setId(eshopEntity.getId());
        dto.setName(eshopEntity.getName());
        dto.setHomePage(eshopEntity.getHomePage());
        return dto;
    }

    public List<EshopListDto> transformToListOfEshopListDto(List<EshopEntity> listOfEshopEntity) {
        if (listOfEshopEntity == null) {
            return null;
        }
        List<EshopListDto> result = new ArrayList<>(listOfEshopEntity.size());
        for (EshopEntity eshopEntity : listOfEshopEntity) {
            result.add(transformToEshopListDto(eshopEntity));
        }
        return result;
    }

    public EshopListDto transformToEshopListDto(EshopEntity eshopEntity) {
        if (eshopEntity == null) {
            return null;
        }
        EshopListDto result = new EshopListDto();
        result.setId(eshopEntity.getId());
        result.setName(eshopEntity.getName());
        result.setHomePage(eshopEntity.getHomePage());
        return result;
    }

    public List<EshopIdNameDto> transformToListOfEshopIdNameDto(List<EshopEntity> listOfEshopEntity) {
        if (listOfEshopEntity == null) {
            return null;
        }
        List<EshopIdNameDto> result = new ArrayList<>(listOfEshopEntity.size());
        for (EshopEntity eshopEntity : listOfEshopEntity) {
            result.add(transformToEshopIdNameDto(eshopEntity));
        }
        return result;

    }

    public EshopIdNameDto transformToEshopIdNameDto(EshopEntity eshopEntity) {
        if (eshopEntity == null) {
            return null;
        }
        EshopIdNameDto result = new EshopIdNameDto();
        result.setId(eshopEntity.getId());
        result.setName(eshopEntity.getName());
        return result;
    }

    public PageList<EshopListDto> transformToPageListOfEshopListDto(PageList<EshopEntity> eshops) {
        List<EshopListDto> versions = new ArrayList<>(eshops.getEntries().size());
        for (EshopEntity entity : eshops.getEntries()) {
            versions.add(transformToEshopListDto(entity));
        }
        return new PageList<>(versions, eshops.getCurrentPage(), eshops.getAllPageCount());
    }

    public List<EshopHomePageInfoDto> transformToListOfEshopHomePageInfoDto(List<EshopEntity> allEshops) {
        List<EshopHomePageInfoDto> versions = new ArrayList<>(allEshops.size());
        for (EshopEntity entity : allEshops) {
            versions.add(transformToEshopHomePageInfoDto(entity));
        }
        return versions;
    }

    private EshopHomePageInfoDto transformToEshopHomePageInfoDto(EshopEntity entity) {
        EshopHomePageInfoDto dto = new EshopHomePageInfoDto();
        dto.setEshopType(entity.getEshopType());
        dto.setHomePage(entity.getHomePage());
        return dto;
    }
}
