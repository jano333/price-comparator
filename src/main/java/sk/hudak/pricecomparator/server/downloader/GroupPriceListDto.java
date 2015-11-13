package sk.hudak.pricecomparator.server.downloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jan on 7. 11. 2015.
 */
@Deprecated
public class GroupPriceListDto {

    private Long groupId;
    private String groupName;
    private List<EshopProductPriceDto> eshopProductPriceDtos = new ArrayList<>();

    public GroupPriceListDto(Long groupId, String groupName, List<EshopProductPriceDto> eshopProductPriceDtos) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.eshopProductPriceDtos = eshopProductPriceDtos;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<EshopProductPriceDto> getEshopProductPriceDtos() {
        return eshopProductPriceDtos;
    }
}
