package model;

import java.util.LinkedList;
import java.util.List;

public class User {

    private int myBalance;
    private List<Food> inventory;
    private Cat myPet;

    // EFFECTS: construct a user, with balance starting at 100, initialize inventory as list of Food objects
    public User() {
        myBalance = 100;
        inventory = new LinkedList<>();
    }

    // EFFECTS: return true if balance >= price of Food object, false otherwise
    public boolean canPurchase(Food item) {
        boolean isPurchased = false;
        if (myBalance >= item.getPrice()) {
            isPurchased = true;
        }
        return isPurchased;
    }

    // MODIFIES: this
    // EFFECTS: add Food object and place it at the end of the list(inventory),
    // balance is deducted by the price of food
    public void addItem(Food item) {
        inventory.add(item);
        myBalance -= item.getPrice();
    }

    // MODIFIES: this
    // EFFECTS: set myPet to cat(input)
    public void addCat(Cat cat) {
        myPet = cat;
    }

    // REQUIRES: inventory.size() must be >= 1
    // MODIFIES: this
    // EFFECTS: remove first item in list (inventory)
    public void removeFirstItem() {
        inventory.remove(0);
    }

    // EFFECTS: getters
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
