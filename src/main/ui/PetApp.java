package ui;

import model.Cat;

import model.Food;
import model.Shop;
import model.User;
import persistance.JsonWriter;
import persistance.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class PetApp {
    private static final String JSON_STORE = "./data/user.json";
    private User user;
    private final Shop shop;
    private final Food cannedSalmon;
    private final Food dryTreats;
    private final Food dietFood;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initialize user and shop, add food objects to shop catalogue, then runs the Pet Game
    public PetApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        shop = new Shop();
        cannedSalmon = new Food("Canned salmon", 20, 20, 30, 20);
        dryTreats = new Food("Dry Treats", 10, 15, 15, 10);
        dietFood = new Food("Diet food", 25, -10, 25, 25);
        shop.addItems(cannedSalmon);
        shop.addItems(dryTreats);
        shop.addItems(dietFood);
        runGame();

    }

    // MODIFIES: this
    // EFFECTS: process user input
    private void runGame() {
        // MENU FORMAT REFERENCES FROM BANK TELLER APP
        boolean keepGoing = true;
        while (keepGoing) {
            displayFirstMenu();
            Scanner input = new Scanner(System.in);
            String nextMove = input.nextLine();

            if (nextMove.equals("q")) {
                keepGoing = false;
            } else {
                processFirstMenuCommand(nextMove);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: display game menu, process command
    private void gameMenu() {
        // FORMAT REFERENCE FROM BANK TELLER APP
        boolean menuRunning = true;
        while (menuRunning) {
            displayGameMenu();
            Scanner input = new Scanner(System.in);
            String nextMove = input.nextLine();
            if (nextMove.equals("q")) {
                saveUser();
                System.exit(0);

            } else {
                processGameMenuCommand(nextMove);
            }

        }
    }

    // EFFECTS: saves the workroom to file
    private void saveUser() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("Saved game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: print cat ASCII art, according to breeds
    private void drawCat(String breed) {
        // REFERENCE: ALL ASCII ARTWORKS FROM https://www.asciiart.eu/animals/cats
        if (breed.equals("Ragdoll")) {
            System.out.println("  ^_^  ");
            System.out.println("( o.o )");
            System.out.println(" > ^ <\n");
        } else {
            System.out.println("  ^_^ ");
            System.out.println("( o o )");
            System.out.println("==_Y_==");
            System.out.println("  `-'");
        }
    }


    // MODIFIES: this
    // EFFECTS: consumes first food object in user's inventory,
    // print different statements when (empty inventory, consumed diet food, and else)
    private void feedCat() {
        if (user.getInventory().isEmpty()) {
            System.out.println("You don't have anything to feed your cat!");
            System.out.println("Head to Shop and buy some food!\n\n");
        } else {
            user.getCat().feed(user.getInventory().get(0));
            if (user.getInventory().get(0).getName().equals("Diet food")) {
                System.out.println("Consumed 1 Diet food");
                System.out.println("Your cat hates it!\n\n");
            } else {
                System.out.println("Consumed 1 " + user.getInventory().get(0).getName());
                System.out.println("Your cat grew friendlier to you!\n\n");
            }
            user.removeFirstItem();
        }
    }


    // EFFECTS: print a list of Food objects in user's inventory
    private void printInventory() {
        System.out.println("My balance: " + user.getBalance());
        List<Food> myInventory = user.getInventory();
        if (myInventory.isEmpty()) {
            System.out.println("Your inventory is empty!\n\n");
        } else {
            for (Food f : myInventory) {
                System.out.println("Item: " + f.getName()
                        + " Energy: " + f.getAddEnergyLevel()
                        + " Happiness: " + f.getAddHappiness()
                        + " Hunger: " + f.getAddHunger() + "\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: process user command
    private void shopMenu() {
        System.out.println("Your Balance: " + user.getBalance());
        printShopCatalogue();
        displayShopMenu();
        Scanner input = new Scanner(System.in);
        String nextMove = input.nextLine();
        processShopMenuCommand(nextMove);
    }

    // REQUIRES: shop's catalogue must not be empty
    // EFFECT: print every item with values of variables in shop catalogue
    private void printShopCatalogue() {
        List<Food> shopInventory = shop.getCatalogue();
        for (Food f : shopInventory) {
            System.out.println("Item: " + f.getName());
            System.out.println(" Price: " + f.getPrice()
                    + " Energy: " + f.getAddEnergyLevel()
                    + " Happiness: " + f.getAddHappiness()
                    + " Hunger: " + f.getAddHunger());
        }
    }


    // REQUIRES: cat must be initialized
    // EFFECTS: print user's pet attributes
    private void viewCat() {
        System.out.println("Breed: " + user.getCat().getBreed());
        System.out.println("Energy: " + user.getCat().getEnergyLevel());
        System.out.println("Happiness: " + user.getCat().getHappiness());
        System.out.println("Hunger: " + user.getCat().getHungerLevel() + "\n");
    }

    // MODIFIES: this
    // EFFECTS: add Food object f into user u's inventory,
    // user u's balance will be deducted by food f's price
    private void purchase(User u, Food f) {
        if (u.canPurchase(f)) {
            u.addItem(f);
            System.out.println(f.getName() + " added to your inventory");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    // EFFECTS: display first menu options
    private void displayFirstMenu() {
        System.out.println("Welcome to my Virtual Cat Game!");
        System.out.println("===============================");
        System.out.println("play........................[p]");
        System.out.println("quit........................[q]\n");
    }

    // MODIFIES: this
    // EFFECTS: process user command on first menu options
    private void processFirstMenuCommand(String nextMove) {
        if (nextMove.equals("p")) {
            if (!loadUser()) {
                System.out.println("Generating cat............");
                user = new User();
                System.out.println("You have a " + user.getCat().getBreed() + " cat!");
            }

            gameMenu();
        } else {
            System.out.println("invalid input.............");
        }
    }

    private boolean loadUser() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded game from " + JSON_STORE);
            return true;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            return false;
        }
    }

    // EFFECTS: display ASCII cat art, game menu options
    private void displayGameMenu() {
        drawCat(user.getCat().getBreed());
        System.out.println("Feed..................[f]");
        System.out.println("Inventory.............[i]");
        System.out.println("Shop..................[s]");
        System.out.println("MyCat.................[c]");
        System.out.println("Quit..................[q]\n");
    }

    // MODIFIES: this
    // EFFECTS: process user command
    private void processGameMenuCommand(String nextMove) {
        switch (nextMove) {
            case "f":
                feedCat();
                break;
            case "i":
                printInventory();
                break;
            case "s":
                shopMenu();
                break;
            case "c":
                viewCat();
                break;
            default:
                System.out.println("invalid input.............");
                break;
        }
    }

    // EFFECTS: display shop menu options
    private void displayShopMenu() {
        System.out.println("Buy canned salmon?...........[cs]");
        System.out.println("Buy dryTreat?................[dt]");
        System.out.println("Buy dietFood?................[df]\n");
    }

    // MODIFIES: this
    // EFFECTS: process user command on shop menu options
    private void processShopMenuCommand(String nextMove) {
        switch (nextMove) {
            case "cs":
                purchase(user, cannedSalmon);
                break;
            case "dt":
                purchase(user, dryTreats);
                break;
            case "df":
                purchase(user, dietFood);
                break;
            default:
                System.out.println("invalid input.............");
                break;
        }
    }
}