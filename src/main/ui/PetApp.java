package ui;

import model.Cat;

import model.Food;
import model.User;

import java.util.*;

public class PetApp {
    private User user;


    public PetApp() {
        runGame();
    }

    private void runGame() {
        // reference from bankTeller app
        user = new User();
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
        breedList.add("Persian");
        Random randomizer = new Random();
        String randomBreed = breedList.get(randomizer.nextInt(breedList.size()));
        Cat newCat = new Cat(randomBreed);
        user.addCat(newCat);
    }

    // print shop menu, display
    private void displayMenu() {
        // reference from bankTeller app
        boolean menuRunning = true;
        while (menuRunning) {
            drawCat();
            System.out.println("Feed..................[f]");
            System.out.println("Play..................[p]");
            System.out.println("Inventory.............[i]");
            System.out.println("Shop..................[s]");
            System.out.println("MyCat.................[c]");
            System.out.println("Quit..................[q]\n");
            Scanner input = new Scanner(System.in);
            String nextMove = input.nextLine();
            if (nextMove.equals("q")) {
                menuRunning = false;
            } else {
                processCommand(nextMove);
            }

        }
    }

    private void drawCat() {
        System.out.println("  ^_^ ");
        System.out.println("( o.o )");
        System.out.println(" > ^ <\n");
    }

    //processCommand
    private void processCommand(String nextMove) {
        if (nextMove.equals("f")) {
            feedCat();
        } else if (nextMove.equals("p")) {
            playCat();
        } else if (nextMove.equals("i")) {
            printInventory();
        } else if (nextMove.equals("s")) {
            initShop();
            printShopCatalogue();
            displayShopMenu();
        } else if (nextMove.equals("c")) {
            viewCat();
        } else {
            System.out.println("invalid input.............");
        }

    }

    // feed cat

    // play cat

    // print Inventory
    private void printInventory() {

        List<Food> myInventory = user.getInventory();
        for (Food f : myInventory) {
            System.out.println("Item: " + f.getName()
                    + " Energy: " + f.getAddEnergyLevel()
                    + " Happiness: " + f.getAddHappiness()
                    + " Hunger: " + f.getAddHunger() + "\n");
        }
    }

    //init shop with 3 items

    // print shop catalogue

    // display shop menu (buy, back)

    // view my cat's stats (get Cat's stats)


}