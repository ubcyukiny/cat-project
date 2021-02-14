package model;

import model.Pet;

public class User {

    private String username;
    private String password;
    private Pet myPet;

    //Constructor
    public User(String name, String pw) {
        username = name;
        password = pw;

    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
