package model;

public class Food {
    private final String name;
    private final int price;
    private int addHappiness;
    private int addEnergyLevel;
    private int addHunger;

    // REQUIRES: name length must be non-zero, price must be >= 0
    // EFFECTS: construct food with its name, price, and 3 attributes boost
    public Food(String name, int price, int happyBoost, int energyBoost, int hungerBoost) {
        this.name = name;
        this.price = price;
        addHappiness = happyBoost;
        addEnergyLevel = energyBoost;
        addHunger = hungerBoost;
    }


    //EFFECTS: getters
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getAddEnergyLevel() {
        return addEnergyLevel;
    }

    public int getAddHappiness() {
        return addHappiness;
    }

    public int getAddHunger() {
        return addHunger;
    }
}
