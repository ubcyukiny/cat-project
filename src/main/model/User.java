package model;

import java.util.LinkedList;
import java.util.List;

public class User {

    private int myBalance;
    private List<Food> inventory;
    private Cat myPet;

    //Constructor
    public User() {
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

    public void addCat(Cat cat) {
        myPet = cat;
    }

    public int getBalance() {
        return myBalance;
    }

    public List<Food> getInventory() {
        return inventory;
    }

}
