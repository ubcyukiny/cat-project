package model;

import org.json.JSONObject;
import persistance.Writable;
import java.time.LocalDate;

public class Cat implements Writable {
    public static final int maxLevel = 100;
    public static final int minLevel = 0;


    private String breed;
    private int happiness;
    private int hungerLevel;
    private int energyLevel;
    LocalDate date; // Create a date object

    // REQUIRES: breed has a non-zero length
    // EFFECTS: construct a cat, with this.breed set to breed(input),
    // happiness, hungryLevel and energyLevel are initialized at 50
    public Cat(String breed, int happiness, int hungerLevel, int energyLevel) {
        this.breed = breed;
        this.happiness = happiness;
        this.hungerLevel = hungerLevel;
        this.energyLevel = energyLevel;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("breed", breed);
        json.put("happiness", happiness);
        json.put("hungerLevel", hungerLevel);
        json.put("energyLevel", energyLevel);
        return json;
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