import model.Food;
import model.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopTest {
    private Shop testFoods;
    private Food testFood;
    @BeforeEach
    void setup() {
        testFoods = new Shop();
        testFood = new Food ("test",20,10,10,10);
    }

    @Test
    void testConstructor() {
        // testFood is empty ,not null
        assertFalse(testFoods == null);
    }

    @Test
    void testGetFoods() {
        //testFood should be empty and initialized not null
        assertFalse(testFoods.getCatalogue() == null);
    }

    @Test
    void testGetSize() {
        assertEquals(0, testFoods.getSize());
    }

    @Test
    void testAddItem(){
        assertEquals(0, testFoods.getSize());
        testFoods.addItems(testFood);
        assertEquals(1, testFoods.getSize());
    }

}
