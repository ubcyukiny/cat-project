package model;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String name;
    private int myBalance;
    private List<Food> inventory;
    private Cat myPet;

    //Constructor
    public User(String name) {
        this.name = name;
        myBalance = 100;
        inventory = new LinkedList<Food>();
    }

    public boolean purchase(Food item) {
        boolean isPurchased = false;
        if (myBalance >= item.getPrice()) {
            myBalance -= item.getPrice();
            inventory.add(item);
            isPurchased = true;
        }
        return isPurchased;
    }

    public void useItem(Consumable item)


    public String getName() {
        return name;
    }

    public int getBalance() {
        return myBalance;
    }


}
