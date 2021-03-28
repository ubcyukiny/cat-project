package model;

import model.Food;
import model.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        //testFood should be size of 3 and initialized not null
        assertFalse(testFoods.getCatalogue() == null);
        assertTrue(testFoods.getCatalogue().size() == 3);
    }

    @Test
    void testGetSize() {
        assertEquals(3, testFoods.getSize());
    }

    @Test
    void testAddItem(){
        assertEquals(3, testFoods.getSize());
        testFoods.addItems(testFood);
        assertEquals(4, testFoods.getSize());
    }

}
