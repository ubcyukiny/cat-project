package persistence;


import model.Food;
import model.User;

import org.junit.jupiter.api.Test;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//cited from JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User(LocalDate.now().toString());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyInventory() {
        try {
            User user = new User(LocalDate.now().toString());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventory.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInventory.json");
            user = reader.read();
            assertEquals(500, user.getBalance());
            assertEquals(0, user.getInventory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNonemptyInventory() {
        try {
            User user = new User(LocalDate.now().toString());
            List<Food> inventory = new LinkedList<>();
            inventory.add(new Food("Diet food", 25, -10, 25, 25));
            inventory.add(new Food ("Canned salmon", 20 ,20, 30, 20));
            user.setInventory(inventory);
            JsonWriter writer = new JsonWriter("./data/testWriterNonemptyInventory.json");
            writer.open();
            writer.write(user);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterNonemptyInventory.json");
            user = reader.read();
            assertEquals(500, user.getBalance());
            List<Food> foods = user.getInventory();
            assertEquals(2, foods.size());
            checkFood("Diet food", 25, -10, 25 ,25, inventory.get(0));
            checkFood("Canned salmon", 20, 20, 30, 20, inventory.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}