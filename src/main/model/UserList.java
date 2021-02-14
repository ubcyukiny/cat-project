package model;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public void addUser(User user1) {
        users.add(user1);
    }

    public boolean findUser(String username, String password) {
        boolean isFound = false;
        for (User user : users) {
            if (user.getUsername() == username && user.getPassword() == password) {
                isFound = true;
            }
        }
        return isFound;
    }
}

