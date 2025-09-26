package annotation.sub5_practice.validator.dynamic_way;

import annotation.sub5_practice.validator.dynamic_way.validator.NotEmpty;
import annotation.sub5_practice.validator.dynamic_way.validator.Range;

public class Team {

    @NotEmpty(message = "name is empty..")
    private String name;

    @Range(min = 1, max = 999, message = "member count is out of range.. 1 <= memberCount <= 999")
    private int memberCount;

    public Team(String name, int memberCount) {
        this.name = name;
        this.memberCount = memberCount;
    }

    public String getName() {
        return name;
    }

    public int getMemberCount() {
        return memberCount;
    }
}
