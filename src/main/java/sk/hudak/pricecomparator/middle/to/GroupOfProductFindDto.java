package sk.hudak.pricecomparator.middle.to;

/**
 * Created by jan on 20. 3. 2016.
 */
public class GroupOfProductFindDto extends FindDto {

    public static final String AT_GROUP_NAME = "groupName";

    private Long groupId;
    private String groupName;


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupOfProductFindDto that = (GroupOfProductFindDto) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        return !(groupName != null ? !groupName.equals(that.groupName) : that.groupName != null);

    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupOfProductFindDto{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
