package ui;

import model.Cat;

import model.Food;
import model.Shop;
import model.User;

import java.util.*;

public class PetApp {
    private final User user;
    private final Shop shop;
    private Cat newCat;
    private final Food cannedSalmon;
    private final Food dryTreats;
    private final Food dietFood;




    public PetApp() {
        user = new User();
        shop = new Shop();
        cannedSalmon = new Food("Canned salmon", 20, 20,30,20);
        dryTreats = new Food("Dry Treats",10,15,15,10);
        dietFood = new Food("Diet food", 25,-10,25,25);
        shop.addItems(cannedSalmon);
        shop.addItems(dryTreats);
        shop.addItems(dietFood);
        runGame();

    }

    private void runGame() {
        // reference from bankTeller app
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Welcome to my Virtual Cat Game!");
            System.out.println("===============================");
            System.out.println("play........................[p]");
            System.out.println("quit........................[q]\n");
            Scanner input = new Scanner(System.in);
            String nextMove = input.nextLine();

            if (nextMove.equals("p")) {
                System.out.println("Generating cat............");
                createRandomCat();
                System.out.println("You have a " + newCat.getBreed() + " cat!");
                displayMenu();
            } else if (nextMove.equals("q")) {
                keepGoing = false;
            } else {
                System.out.println("invalid input.............");
            }
        }
    }

    // initialize cat
    private void createRandomCat() {
        List<String> breedList = new ArrayList<>();
        breedList.add("Ragdoll");
        breedList.add("British Short hair");
        Random randomizer = new Random();
        String randomBreed = breedList.get(randomizer.nextInt(breedList.size()));
        newCat = new Cat(randomBreed);
        user.addCat(newCat);
    }

    // print shop menu, display
    private void displayMenu() {
        // reference from bankTeller app
        boolean menuRunning = true;
        while (menuRunning) {
            drawCat(newCat.getBreed());
            System.out.println("Feed..................[f]");
            System.out.println("Inventory.............[i]");
            System.out.println("Shop..................[s]");
            System.out.println("MyCat.................[c]");
            System.out.println("Quit..................[q]\n");
            Scanner input = new Scanner(System.in);
            String nextMove = input.nextLine();
            if (nextMove.equals("q")) {
                System.exit(0);
            } else {
                processCommand(nextMove);
            }

        }
    }

    private void drawCat(String breed) {
        // ALL ASCII ARTWORKS FROM https://www.asciiart.eu/animals/cats
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

    //processCommand
    private void processCommand(String nextMove) {
        switch (nextMove) {
            case "f":
                feedCat();
                break;
            case "i":
                printInventory();
                break;
            case "s":
                displayShop();
                break;
            case "c":
                viewCat();
                break;
            default:
                System.out.println("invalid input.............");
                break;
        }

    }

    // feed cat
    private void feedCat() {
        if (user.getInventory().isEmpty()) {
            System.out.println("You don't have anything to feed your cat!");
            System.out.println("Head to Shop and buy some food!\n\n");
        } else {
            newCat.feed(user.getInventory().get(0));
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


    // print Inventory
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

    //init shop with 3 items
    private void displayShop() {
        System.out.println("Your Balance: " + user.getBalance());
        printShopCatalogue();
        System.out.println("Buy canned salmon?...........[cs]");
        System.out.println("Buy dryTreat?................[dt]");
        System.out.println("Buy dietFood?................[df]\n");
        Scanner input = new Scanner(System.in);
        String nextMove = input.nextLine();
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
        }
    }

    // print shop catalogue
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


    // view my cat's stats (get Cat's stats)
    private void viewCat() {
        System.out.println("Breed: " + newCat.getBreed());
        System.out.println("Energy: " + newCat.getEnergyLevel());
        System.out.println("Happiness: " + newCat.getHappiness());
        System.out.println("Hunger: " + newCat.getHungerLevel() + "\n");
    }

    private void purchase(User u, Food f) {
        if (u.canPurchase(f)) {
            u.addItem(f);
            System.out.println(f.getName() + " added to your inventory");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }
}