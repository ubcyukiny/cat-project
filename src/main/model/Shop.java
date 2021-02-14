package model;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Food> consumables;

    // constructor
    public Shop() {
        consumables = new ArrayList<Food>();
    }

    public void addItems(Food item) {
        consumables.add(item);
    }


}
