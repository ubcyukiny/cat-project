package persistence;

import model.Food;
import model.User;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//cited from JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyInventory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInventory.json");
        try {
            User user = reader.read();
            assertEquals(100, user.getBalance());
            assertEquals(0, user.getInventory().size());
            assertTrue(LocalDate.now().toString().equals(user.getLastLoginString()));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNonemptyInventory() {
        JsonReader reader = new JsonReader("./data/testReaderNonemptyInventory.json");
        try {
            User user = reader.read();
            assertEquals(100, user.getBalance());
            List<Food> inventory = user.getInventory();
            assertEquals(2, inventory.size());
            assertTrue(LocalDate.now().toString().equals(user.getLastLoginString()));
            checkFood("Canned salmon", 20, 20, 30, 20, inventory.get(0));
            checkFood("Diet food", 25, -10, 25 ,25, inventory.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}