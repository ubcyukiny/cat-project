package persistence;

import model.Food;
import static org.junit.jupiter.api.Assertions.assertEquals;

//cited from JsonSerializationDemo
public class JsonTest {
    protected void checkFood(String name, int price, int happyBoost, int energyBoost, int hungerBoost, Food food) {
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
        assertEquals(happyBoost, food.getAddHappiness());
        assertEquals(energyBoost, food.getAddEnergyLevel());
        assertEquals(hungerBoost, food.getAddHunger());
    }
    //     public Food(String name, int price, int happyBoost, int energyBoost, int hungerBoost)
}
