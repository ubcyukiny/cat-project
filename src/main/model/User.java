package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.util.Objects.isNull;

// Represents the User, with balance, inventory and the cat associated
public class User implements Writable {

    private int myBalance;
    private List<Food> inventory;
    private Cat myPet;
    private LocalDate lastLogin;

    // EFFECTS: construct a user, with balance starting at 100, initialize inventory as list of Food objects
    public User(String date) {
        myBalance = 500;
        inventory = new LinkedList<>();
        addCat(createRandomCat());
        lastLogin = LocalDate.parse(date);
    }


    // MODIFIES: this, myPet
    // EFFECTS: calculate days between lastLogin and current game session date, and decay user's myPet stat
    public void statDecay() {
        // current days - past days
        LocalDate currentLogin = LocalDate.now();
        int differenceInDays = currentLogin.getDayOfYear() - lastLogin.getDayOfYear();
        // change myPet's stat
        myPet.setHappiness(myPet.getHappiness() - differenceInDays * Cat.DECAY_PER_DAY);
        myPet.setHungerLevel(myPet.getHungerLevel() - differenceInDays * Cat.DECAY_PER_DAY);
        myPet.setEnergyLevel(myPet.getEnergyLevel() - differenceInDays * Cat.DECAY_PER_DAY);
    }


    // MODIFIES: this
    // EFFECTS: set last login as current time
    public void saveLastLogin() {
        LocalDate currentDate = LocalDate.now();
        lastLogin = currentDate;
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
    // TODO ADDED FUNCTIONALITY
    public void addCat(Cat cat) {
        if (isNull(myPet) || !myPet.equals(cat)) {
            myPet = cat;
            cat.addOwner(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (myBalance != user.myBalance) {
            return false;
        }
        if (!inventory.equals(user.inventory)) {
            return false;
        }
        return lastLogin.equals(user.lastLogin);
    }

    @Override
    public int hashCode() {
        int result = myBalance;
        result = 31 * result + inventory.hashCode();
        result = 31 * result + lastLogin.hashCode();
        return result;
    }

    // REQUIRES: inventory.size() must be >= 1
    // MODIFIES: this
    // EFFECTS: remove first item in list (inventory)
    public void removeFirstItem() {
        inventory.remove(0);
    }


    @Override
    // returns json file with user's field and data
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Balance", myBalance);
        json.put("Inventory", foodsToJson());
        json.put("Cat", myPet.toJson());
        json.put("Time", lastLogin.toString());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: randomly choose a breed from a list of two, initialize a cat with that breed and add it to user
    private Cat createRandomCat() {
        List<String> breedList = new ArrayList<>();
        breedList.add("Ragdoll");
        breedList.add("British Short hair");
        Random randomizer = new Random();
        String randomBreed = breedList.get(randomizer.nextInt(breedList.size()));
        Cat cat = new Cat(randomBreed, 50, 50, 50);
        return cat;
    }

    // EFFECTS: returns foods in this USER as a JSON array
    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Food f : inventory) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: add Food object f into user u's inventory,
    // user u's balance will be deducted by food f's price
    public void purchase(Food f) {
        if (canPurchase(f)) {
            addItem(f);
            JOptionPane.showMessageDialog(null, f.getName() + " added to your inventory");
            JOptionPane.showMessageDialog(null, "Remaining balance: " + getBalance());
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient Balance!");
        }
    }

    // EFFECTS: return a string of item summary
    public String itemSummary() {
        if (inventory.size() == 0) {
            return "You don't have anything in your inventory!";
        } else {
            String output = "";
            for (Food f : inventory) {
                output = output + "a " + f.getName() + ", ";
            }
            return "You have " + output + "I think.";
        }
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

    public String getLastLoginString() {
        return lastLogin.toString();
    }

}
