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
        assertFalse(testUser.getInventory() == null);
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
        testCatToAdd = new Cat("Ragdoll", 50, 50, 50);
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
        //     public void statDecay() {
        //        // current days - past days
        //        LocalDate currentLogin = LocalDate.now();
        //        int differenceInDays = currentLogin.getDayOfYear() - lastLogin.getDayOfYear();
        //        myPet.setHappiness(myPet.getHappiness() - differenceInDays * Cat.DECAY_PER_DAY);
        //        myPet.setHungerLevel(myPet.getHungerLevel() - differenceInDays * Cat.DECAY_PER_DAY);
        //        myPet.setEnergyLevel(myPet.getEnergyLevel() - differenceInDays * Cat.DECAY_PER_DAY);
        //    }
    void testStatDecay() {
        // user last login date will be 1 day behind LocalDate.now()
        testUser = new User(LocalDate.now().minusDays(1).toString());
        // now add cat to user, stats are 50,50,50
        testAddCat();
        assertEquals(testUser.getCat().getHungerLevel(), 50);
        assertEquals(testUser.getCat().getHappiness(), 50);
        assertEquals(testUser.getCat().getEnergyLevel(), 50);
        testUser.statDecay();
        assertEquals(testUser.getCat().getHungerLevel(), 50 - Cat.DECAY_PER_DAY);
        assertEquals(testUser.getCat().getHappiness(), 50 - Cat.DECAY_PER_DAY);
        assertEquals(testUser.getCat().getEnergyLevel(), 50 - Cat.DECAY_PER_DAY);
    }

    @Test
    void testSaveLastLogin() {
        // user last login date will be 1 day behind LocalDate.now()
        testUser = new User(LocalDate.now().minusDays(1).toString());
        assertTrue(testUser.getLastLoginString().equals(LocalDate.now().minusDays(1).toString()));
        // save last login date to current day
        testUser.saveLastLogin();
        assertTrue(testUser.getLastLoginString().equals(LocalDate.now().toString()));
    }

}

