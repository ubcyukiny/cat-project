package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// Represents the User, with balance, inventory and the cat associated
public class User implements Writable {

    private int myBalance;
    private List<Food> inventory;
    private Cat myPet;

    // EFFECTS: construct a user, with balance starting at 100, initialize inventory as list of Food objects
    public User() {
        myBalance = 100;
        inventory = new LinkedList<>();
        createRandomCat();
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


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Balance", myBalance);
        json.put("Inventory", foodsToJson());
        json.put("Cat", myPet.toJson());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: randomly choose a breed from a list of two, initialize a cat with that breed and add it to user
    private void createRandomCat() {
        List<String> breedList = new ArrayList<>();
        breedList.add("Ragdoll");
        breedList.add("British Short hair");
        Random randomizer = new Random();
        String randomBreed = breedList.get(randomizer.nextInt(breedList.size()));
        Cat cat = new Cat(randomBreed, 50, 50,50);
        addCat(cat);
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Food f : inventory) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: setters
    public void setBalance(int balance) {
        myBalance = balance;
    }

    public void setInventory(List<Food> inventory) {
        this.inventory = inventory;
    }

    // EFFECTS: getters
    public int getBalance() {
        return myBalance;
    }

    public List<Food> getInventory() {
        return inventory;
    }

    public Cat getCat() {
        return myPet;
    }


}
