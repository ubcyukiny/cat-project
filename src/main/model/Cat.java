package model;

public class Cat {
    public static final int maxLevel = 100;
    public static final int minLevel = 0;


    private String breed;
    private int happiness;
    private int hungerLevel;
    private int energyLevel;

    // REQUIRES: breed has a non-zero length
    // EFFECTS: construct a cat, with this.breed set to breed(input),
    // happiness, hungryLevel and energyLevel are initialized at 50
    public Cat(String breed) {
        this.breed = breed;
        happiness = 50;
        hungerLevel = 50;
        energyLevel = 50;
    }

    // MODIFIES: this
    // EFFECTS: change happiness, hungerLevel, energyLevel, according to food(input)'s attributes
    public void feed(Food food) {
        happiness += food.getAddHappiness();
        hungerLevel += food.getAddHunger();
        energyLevel += food.getAddEnergyLevel();
        levelCheck();
    }

    // MODIFIES: this
    // EFFECTS : adjust happiness, hungerLevel, energyLevel when outside minLevel or maxLevel
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

    // EFFECTS: getters
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