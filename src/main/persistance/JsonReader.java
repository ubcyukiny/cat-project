package persistance;

import model.Cat;
import model.Food;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

// Cited from JsonSerializationDemo

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses User from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {

        User user = new User();
        user.setBalance(jsonObject.getInt("Balance"));
        // retrieve inventory
        addFoods(user, jsonObject);
        // retrieve cat
        // add Cat
        JSONObject catJsonObject = jsonObject.getJSONObject("Cat");
        Cat cat = new Cat(catJsonObject.getString("breed"), catJsonObject.getInt("happiness"),
                catJsonObject.getInt("hungerLevel"), catJsonObject.getInt("energyLevel"));
        user.addCat(cat);
        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses list of food from JSON object and adds them to user
    private void addFoods(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Inventory");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(user, nextFood);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses food from JSON object and adds it to users inventory
    private void addFood(User user, JSONObject jsonObject) {
        Food food = new Food(jsonObject.getString("name"), jsonObject.getInt("price"),
                jsonObject.getInt("addHappiness"), jsonObject.getInt("addEnergyLevel"),
                jsonObject.getInt("addHunger"));
        List<Food> inventory = user.getInventory();
        inventory.add(food);
        user.setInventory(inventory);
    }


}
