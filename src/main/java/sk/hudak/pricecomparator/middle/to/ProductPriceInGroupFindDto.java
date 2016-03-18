package sk.hudak.pricecomparator.middle.to;

/**
 * Created by jan on 18. 3. 2016.
 */
public class ProductPriceInGroupFindDto extends FindDto {

    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
