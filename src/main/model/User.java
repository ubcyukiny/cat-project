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

    public boolean canPurchase(Food item) {
        boolean isPurchased = false;
        if (myBalance >= item.getPrice()) {
            isPurchased = true;
        }
        return isPurchased;
    }

    public void addItem(Food item) {
        inventory.add(item);
        myBalance -= item.getPrice();
    }

    public void addCat(Cat cat) {
        myPet = cat;
    }


    public void removeFirstItem() {
        inventory.remove(0);
    }

    //getters

    public int getBalance() {
        return myBalance;
    }

    public List<Food> getInventory() {
        return inventory;
    }

    public Cat getMyPet() {
        return myPet;
    }


}
