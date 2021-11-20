package ua.com.alevel;

import ua.com.alevel.db.impl.GroupDBImpl;
import ua.com.alevel.entity.Group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IoNioMain {
    public static void main(String[] args) {


        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Group group = new Group();
            group.setId(String.valueOf(i));
            group.setName("group" + i);
            group.setTeacherName("teacher" + i);
            groups.add(group);
        }
//        List<String[]> list = Parser.convertToStrings(Group.class, groups);
//        for (String[] strings : list) {
//            System.out.println(Arrays.toString(strings));
//        }
        GroupDBImpl groupDB = GroupDBImpl.getInstance();

//        for (Group group : groups) {
//            groupDB.create(group);
//        }
//        Group group = groups.get(5);
//        group.setName("other");
//        groupDB.update(group);

        try {
            for (Group group : groupDB.findAll()) {
                System.out.println(group.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Collection<Group> other = Parser.convertToEntities(Group.class, list);
//        for (Group group : other) {
//            System.out.println(group.toString());
//        }
    }
}
