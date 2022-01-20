package ua.com.alevel.persistence.entityDto;

import ua.com.alevel.persistence.entity.Group;

public record GroupResultSet(Group group, Integer studentCount) implements Comparable<GroupResultSet> {

    @Override
    public Group group() {
        return group;
    }

    @Override
    public Integer studentCount() {
        return studentCount;
    }

    @Override
    public int compareTo(GroupResultSet o) {
        return this.studentCount() - o.studentCount();
    }
}
