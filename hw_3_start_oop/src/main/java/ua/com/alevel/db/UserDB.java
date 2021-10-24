package ua.com.alevel.db;

import ua.com.alevel.entity.User;
import ua.com.alevel.util.UserList;

import java.util.Arrays;
import java.util.UUID;

public final class UserDB {
    private final UserList userList;
    private static UserDB instance;

    private UserDB() {
        userList = new UserList();
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateID());
        userList.add(user);
    }

    public void update(User user) {
        User current = findById(user.getId());
        current.setName(user.getName());
        current.setAge(user.getAge());
    }

    public void delete(String id) {
        userList.remove(findById(id));
    }

    public User findById(String id) {
        return Arrays.stream(userList.getUsers())
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("user with id " + id + " is not found"));
    }

    public UserList findAll() {
        return userList;
    }

    private String generateID() {
        String id = UUID.randomUUID().toString();
        if (Arrays.stream(userList.getUsers()).anyMatch(user -> user.getId().equals(id))) {
            return generateID();
        }
        return id;
    }
}
