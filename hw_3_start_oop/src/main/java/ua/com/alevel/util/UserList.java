package ua.com.alevel.util;

import ua.com.alevel.entity.User;

public class UserList {
    private User[] users;
    private int size;

    public UserList() {
        size = 0;
        users = new User[size];
    }

    public User[] getUsers() {
        return users;
    }

    public int size() {
        return size;
    }

    public void add(User user) {
        increaseSize();
        users[size - 1] = user;
    }

    public void remove(User user) {
        for (int i = 0; i < users.length; i++) {
            if(users[i].equals(user)){
                remove(i);
            }
        }
    }

    public void remove(int index) {
        checkBounds(index);
        User[] updateUsers = new User[size -1];
        for (int i = 0, j=0; i < users.length; i++) {
            if(i!=index){
                updateUsers[j++]=users[i];
            }
        }
        users = updateUsers;
        size--;
    }

    public void print(){
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    private void increaseSize() {
        User[] increasedUsers = new User[size + 1];
        System.arraycopy(users, 0, increasedUsers, 0, size);
        users = increasedUsers;
        size++;
    }

    private void checkBounds(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("Unsuitable index: " + index);
        }
    }

}
