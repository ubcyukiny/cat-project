package model;

public class Food {
    private final String name;
    private final int price;
    private int addHappiness;
    private int addEnergyLevel;
    private int addHunger;

    //constructor
    public Food(String name, int price, int happyBoost, int energyBoost, int hungerBoost) {
        this.name = name;
        this.price = price;
        addHappiness = happyBoost;
        addEnergyLevel = energyBoost;
        addHunger = hungerBoost;
    }


    //getters
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
