package model;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Food> foods;

    // constructor
    public Shop() {
        foods = new ArrayList<Food>();
    }

    public void addItems(Food item) {
        foods.add(item);
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public int getSize() {
        return foods.size();
    }
}
