import model.Cat;
import model.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatTest {
    private Cat testCat;
    private Food testFood;
    private Food testMaxFood;
    private Food testMinFood;

    @BeforeEach
    void runBefore(){
        testCat = new Cat("Ragdoll");
        testFood = new Food("test", 20, 5,-15,10);
        testMaxFood = new Food("MaxFood", 20, 55,55,55);
        testMinFood = new Food("MinFood", 20, -55,-55,-55);
    }
    //test Constructor
    @Test
    void testConstructor() {
        assertEquals("Ragdoll", testCat.getBreed());
        assertEquals(50, testCat.getHappiness());
        assertEquals(50, testCat.getEnergyLevel());
        assertEquals(50, testCat.getHungerLevel());
    }

    //test feed
    @Test
    void testFeed() {
        testCat.feed(testFood);
        assertEquals(55, testCat.getHappiness());
        assertEquals(35, testCat.getEnergyLevel());
        assertEquals(60, testCat.getHungerLevel());
    }

    //test max level check
    @Test
    void  testLevelCheckMax() {
        testCat.feed(testMaxFood);
        assertEquals(100, testCat.getHappiness());
        assertEquals(100,testCat.getEnergyLevel());
        assertEquals(100,testCat.getHungerLevel());
    }

    //test min level check
    @Test
    void  testLevelCheckMin() {
        testCat.feed(testMinFood);
        assertEquals(0, testCat.getHappiness());
        assertEquals(0,testCat.getEnergyLevel());
        assertEquals(0,testCat.getHungerLevel());
    }
}
