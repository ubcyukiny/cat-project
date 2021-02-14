package model;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Food> Catalogue;

    // constructor
    public Shop() {
        Catalogue = new ArrayList<Food>();
    }

    public void addItems(Food item) {
        Catalogue.add(item);
    }

    public ArrayList<Food> getCatalogue() {
        return Catalogue;
    }

    public int getSize() {
        return Catalogue.size();
    }
}
