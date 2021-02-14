
import model.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {
    private Food testFood;

    @Test
    void testFoodConstructor() {
        testFood = new Food("test", 20, 10, 5, 10);
        assertEquals("test", testFood.getName());
        assertEquals(20, testFood.getPrice());
        assertEquals(10, testFood.getAddHappiness());
        assertEquals(5, testFood.getAddEnergyLevel());
        assertEquals(10, testFood.getAddHunger());

    }
}
