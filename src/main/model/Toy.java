package model;

public class Toy {
    private String name;
    private int price;
    private int addHappiness;
    private int addEnergyLevel;
    private int addHunger;

    //constructor
    public Toy(String name, int price, int happyBoost, int energyBoost, int hungerBoost) {
        this.name = name;
        this.price = price;
        addHappiness = happyBoost;
        addEnergyLevel = energyBoost;
        addHunger = hungerBoost;
    }

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