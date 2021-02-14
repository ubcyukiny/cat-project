package model;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Food> catalogue;

    // EFFECTS: initialize a list with food objects
    public Shop() {
        catalogue = new ArrayList<>();
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
}
