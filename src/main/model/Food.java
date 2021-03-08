package model;

import org.json.JSONObject;
import persistance.Writable;

// Represent a Food object, having name, price, and stat boosts
public class Food implements Writable {
    private String name;
    private int price;
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

    @Override
    // EFFECTS: return json file with Food's field
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("addHappiness", addHappiness);
        json.put("addEnergyLevel", addEnergyLevel);
        json.put("addHunger", addHunger);
        return json;
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
