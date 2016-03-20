package sk.hudak.pricecomparator.middle.to;

/**
 * Created by jan on 20. 3. 2016.
 */
public class GroupOfProductFindDto extends FindDto {

    public static final String AT_GROUP_NAME = "groupName";

    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
