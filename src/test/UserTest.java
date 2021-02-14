import model.Cat;
import model.Food;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User testUser;
    private Food testFoodCanBuy;
    private Food testFoodCantBuy;
    private Cat testCatToAdd;

    @BeforeEach
    void setup() {
        testUser = new User();
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
        assertEquals(0, testUser.getInventory().size());
        assertEquals(100, testUser.getBalance());
        testUser.addItem(testFoodCanBuy);
        assertEquals(100 - testFoodCanBuy.getPrice(), testUser.getBalance());
        assertEquals(1, testUser.getInventory().size());
        assertEquals(0, testUser.getBalance() - testFoodCanBuy.getPrice());

    }

    //addCat
    @Test
    void testAddCat() {
        assertTrue(testUser.getMyPet() == null);
        testCatToAdd = new Cat("Ragdoll");
        testUser.addCat(testCatToAdd);
        assertFalse( testUser.getMyPet() == null);
    }

    //removeFirstItem
    @Test
    void testRemoveFirstItem() {
        testUser.addItem(testFoodCantBuy); //1st
        testUser.addItem(testFoodCanBuy); // 2nd added
        assertEquals(2, testUser.getInventory().size());
        testUser.removeFirstItem(); //1st will be removed, 2nd remains
        assertEquals(1, testUser.getInventory().size());
        assertTrue(testUser.getInventory().get(0).equals(testFoodCanBuy)); // remaining now become first in list

    }

}
