package model;

public class Cat {
    public static final int maxLevel = 100;
    public static final int minLevel = 0;


    private String breed;
    private int happiness;
    private int hungerLevel;
    private int energyLevel;


    //Constructor
    public Cat(String breed) {
        this.breed = breed;
        happiness = 50;
        hungerLevel = 50;
        energyLevel = 50;
    }

    public void feed(Food food) {
        happiness += food.getAddHappiness();
        hungerLevel += food.getAddHunger();
        energyLevel += food.getAddEnergyLevel();
        levelCheck();
    }


    public void levelCheck() {
        if (happiness > maxLevel) {
            happiness = maxLevel;
        }
        if (hungerLevel > maxLevel) {
            hungerLevel = maxLevel;
        }
        if (energyLevel > maxLevel) {
            energyLevel = maxLevel;
        }
        if (happiness < minLevel) {
            happiness = minLevel;
        }
        if (hungerLevel < minLevel) {
            hungerLevel = minLevel;
        }
        if (energyLevel < minLevel) {
            energyLevel = minLevel;
        }
    }

    // getters

    public String getBreed() {
        return breed;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

}