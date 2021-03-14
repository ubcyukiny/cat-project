package model;

import model.Cat;
import model.Food;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User testUser;
    private Food testFoodCanBuy;
    private Food testFoodCantBuy;
    private Food testFood1;
    private Cat testCatToAdd;


    @BeforeEach
    void setup() {
        testUser = new User(LocalDate.now().toString());
    }

    @Test
    void testConstructor() {
        assertEquals(100, testUser.getBalance());
        assertFalse( testUser.getInventory() == null);
    }

    // test can purchase
    @Test
    void testCanPurchase() {
        testFoodCanBuy = new Food("testFoodCanBuy",
                testUser.getBalance(), 20, 20, 20);
        testFoodCantBuy = new Food("testFoodCantBuy",
                testUser.getBalance() + 2, 20, 20, 20);
        assertTrue(testUser.canPurchase(testFoodCanBuy));
        assertFalse(testUser.canPurchase(testFoodCantBuy));
    }
    @Test
    void testAddItem() {
        testFood1 = new Food("testFood1",
                20, 20, 20, 20);
        assertEquals(0, testUser.getInventory().size());
        assertEquals(100, testUser.getBalance());
        testUser.addItem(testFood1);
        assertEquals(100 - testFood1.getPrice(), testUser.getBalance());
        assertEquals(1, testUser.getInventory().size());

    }

    //addCat
    @Test
    void testAddCat() {
        testCatToAdd = new Cat("Ragdoll", 50 , 50, 50);
        testUser.addCat(testCatToAdd);
        assertTrue(testUser.getCat().equals(testCatToAdd));
    }

    //removeFirstItem
    @Test
    void testRemoveFirstItem() {
        testFoodCanBuy = new Food("testFoodCanBuy",
                testUser.getBalance(), 20, 20, 20);
        testFoodCantBuy = new Food("testFoodCantBuy",
                testUser.getBalance() + 2, 20, 20, 20);
        testUser.addItem(testFoodCantBuy); //1st
        testUser.addItem(testFoodCanBuy); // 2nd added
        assertEquals(2, testUser.getInventory().size());
        testUser.removeFirstItem(); //1st will be removed, 2nd remains
        assertEquals(1, testUser.getInventory().size());
        assertTrue(testUser.getInventory().get(0).equals(testFoodCanBuy)); // remaining now become first in list

    }

    @Test

    @Test

}
