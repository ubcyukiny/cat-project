package model;

import java.util.ArrayList;

// Represent a shop of an array of foods, that can be purchased
public class Shop {
    private ArrayList<Food> catalogue;
    private Food cannedSalmon = new Food("Canned salmon", 20, 20, 30, 20);
    private Food dryTreats = new Food("Dry Treats", 10, 15, 15, 10);
    private Food dietFood = new Food("Diet food", 25, -10, 25, 25);

    // EFFECTS: initialize a list with food objects
    public Shop() {
        catalogue = new ArrayList<>();
        addItems(cannedSalmon);
        addItems(dryTreats);
        addItems(dietFood);
    }

    // MODIFIES: this
    // EFFECTS: add Food and put it at the end of the shop list
    public void addItems(Food item) {
        catalogue.add(item);
    }

    // REQUIRES: catalogue must not be null
    // EFFECTS: return list
    public ArrayList<Food> getCatalogue() {
        return catalogue;
    }

    // REQUIRES: catalogue must not be null
    // EFFECTS: return size of list
    public int getSize() {
        return catalogue.size();
    }

    public Food getCannedSalmon() {
        return cannedSalmon;
    }

    public Food getDryTreats() {
        return dryTreats;
    }

    public Food getDietFood() {
        return dietFood;
    }
}
