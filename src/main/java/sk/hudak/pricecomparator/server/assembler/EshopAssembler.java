package sk.hudak.pricecomparator.server.assembler;

import sk.hudak.pricecomparator.middle.to.EshopDto;
import sk.hudak.pricecomparator.middle.to.EshopListDto;
import sk.hudak.pricecomparator.server.model.EshopEntity;

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
}
