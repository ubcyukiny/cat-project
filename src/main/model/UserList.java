package model;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public void addUser(User user1) {
        users.add(user1);
    }

    public User findUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername() == username && user.getPassword() == password) {
                return user;
            }
        }
        return false;
    }
