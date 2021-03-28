package model;

import org.json.JSONObject;
import persistance.Writable;

import java.time.*;

// Represents a Cat object with breed and happiness, hunger level and energy level
public class Cat implements Writable {
    public static final int MAX_LEVEL = 100;
    public static final int MIN_LEVEL = 0;
    public static final int DECAY_PER_DAY = 5;

    private final String breed;
    private int happiness;
    private int hungerLevel;
    private int energyLevel;


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
        if (happiness > MAX_LEVEL) {
            happiness = MAX_LEVEL;
        }
        if (hungerLevel > MAX_LEVEL) {
            hungerLevel = MAX_LEVEL;
        }
        if (energyLevel > MAX_LEVEL) {
            energyLevel = MAX_LEVEL;
        }
        if (happiness < MIN_LEVEL) {
            happiness = MIN_LEVEL;
        }
        if (hungerLevel < MIN_LEVEL) {
            hungerLevel = MIN_LEVEL;
        }
        if (energyLevel < MIN_LEVEL) {
            energyLevel = MIN_LEVEL;
        }
    }


    @Override
    // EFFECTS: return a json file with Cat's field
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("breed", breed);
        json.put("happiness", happiness);
        json.put("hungerLevel", hungerLevel);
        json.put("energyLevel", energyLevel);
        return json;
    }

    public String printSummary() {
        String output = "<html>" + "Breed: " + breed + "<br />" + "Happiness: " + happiness + "<br />"
                + "Hunger Level: " + hungerLevel + "<br />" + "Energy: " + energyLevel;
        return output;
    }

    // EFFECTS: setters
    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
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