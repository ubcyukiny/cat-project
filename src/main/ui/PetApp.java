package ui;

import model.Food;
import model.Shop;
import model.User;
import persistance.JsonWriter;
import persistance.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;

// Represents the Pet application
public class PetApp extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/user.json";
    private User user;
    private Shop shopInstance;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame currentFrame;
    private JFrame frame;
    private JFrame mainGameFrame;
    private JFrame catMenu;
    private JFrame itemsMenu;
    private JFrame shopMenu;

    private JLabel label;

    private JButton play;
    private JButton save;
    private JButton quit;
    private JButton cat;
    private JButton items;
    private JButton shop;
    private JButton feed;
    private JButton goBackToGameMenu;
    private JButton buyCS;
    private JButton buyDT;
    private JButton buyDF;

    // EFFECTS: initialize user and shop, add food objects to shop catalogue, then runs the Pet Game
    public PetApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        shopInstance = new Shop();
        runGui();
        // runGame();

    }

    private void runGui() {
        initializeFrame();
    }

    // Initialize frame
    public void initializeFrame() {
        frame = new JFrame();
        frame.setTitle("Virtual Cat Game");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        initializeLabels();
        initializeButtons();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        currentFrame = frame;

    }

    // Initialize labels
    public void initializeLabels() {
        label = new JLabel("Welcome to my Virtual Cat Game!", SwingConstants.CENTER);
        frame.add(label, BorderLayout.NORTH);
    }


    // Initialize buttons
    public void initializeButtons() {
        JPanel buttons = new JPanel();
        play = new JButton();
        quit = new JButton();
        goBackToGameMenu = new JButton();
        goBackToGameMenu.setText("BACK");
        goBackToGameMenu.addActionListener(this);
        play.setText("PLAY");
        quit.setText("QUIT");
        play.addActionListener(this);
        quit.addActionListener(this);
        buttons.add(play);
        buttons.add(quit);
        frame.add(buttons, BorderLayout.SOUTH);
    }

    public void initializeCatMenu() {
        catMenu = new JFrame();
        catMenu.setTitle("Cat Menu");
        catMenu.setSize(500, 500);
        catMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        catMenu.setResizable(false);
        catMenu.setLocationRelativeTo(null);
        catMenu.setVisible(true);
        // add display panel for stats
        JLabel catStats = new JLabel();
        catStats.setText(user.getCat().printSummary());
        catMenu.add(catStats);
        // add goBack button
        JPanel buttons = new JPanel();
        buttons.add(goBackToGameMenu);
        catMenu.add(buttons, BorderLayout.SOUTH);
        currentFrame = catMenu;
    }


    public void initializeMainGameFrame() {
        mainGameFrame = new JFrame();
        mainGameFrame.setTitle("Virtual Cat Game");
        mainGameFrame.setSize(500, 500);
        mainGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGameFrame.setResizable(false);
        mainGameFrame.setLocationRelativeTo(null);
        // add cat pictures
        // from http://clipart-library.com/clipart/Lcd5aX5xi.htm
        // set panels for current save status panel, game and buttons
        // add buttons
        initializeCatImage();
        initializeButtonsForGame();
        // add messages, displays save data file
        mainGameFrame.setVisible(true);
        currentFrame = mainGameFrame;


    }

    public void initializeShopMenu() {
        shopMenu = new JFrame();
        shopMenu.setTitle("Shop Menu");
        shopMenu.setSize(500, 500);
        shopMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopMenu.setResizable(false);
        shopMenu.setLocationRelativeTo(null);
        shopMenu.setVisible(true);
        // display Shop Menu
        JLabel shopCatalogue = new JLabel();
        shopCatalogue.setText(printShopCatalogue());
        shopMenu.add(shopCatalogue);
        // display Buttons buy which one
        initializeButtonsForShop();
        currentFrame = shopMenu;
    }

    public void initializeButtonsForShop() {
        JPanel buttons = new JPanel();
        buyCS = new JButton("BUY CS");
        buyDT = new JButton("BUY DT");
        buyDF = new JButton("BUY DF");
        buyCS.addActionListener(this);
        buyDT.addActionListener(this);
        buyDF.addActionListener(this);
        buttons.add(buyCS);
        buttons.add(buyDT);
        buttons.add(buyDF);
        buttons.add(goBackToGameMenu);
        shopMenu.add(buttons, BorderLayout.SOUTH);
    }

    public void initializeItemsMenu() {
        itemsMenu = new JFrame();
        itemsMenu.setTitle("Virtual Cat Game");
        itemsMenu.setSize(500, 500);
        itemsMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itemsMenu.setResizable(false);
        itemsMenu.setLocationRelativeTo(null);
        itemsMenu.setVisible(true);

        //Display Items
        JLabel myItem = new JLabel();
        myItem.setText(user.itemSummary());
        itemsMenu.add(myItem);
        //ADD go back button
        itemsMenu.add(goBackToGameMenu, BorderLayout.SOUTH);

    }

    public void initializeCatImage() {
        JPanel catArea = new JPanel();
        JLabel catLabel = new JLabel();
        ImageIcon catIcon = new ImageIcon("./data/cat.png");
        Image cat = catIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        catLabel.setText("ARTWORK FROM http://clipart-library.com/clipart/Lcd5aX5xi.htm");
        ImageIcon catResized = new ImageIcon(cat);
        catLabel.setIcon(catResized);
        catLabel.setHorizontalTextPosition(JLabel.CENTER);
        catLabel.setVerticalTextPosition(JLabel.TOP);
        catArea.add(catLabel);
        mainGameFrame.add(catArea);

    }

    public void initializeButtonsForGame() {
        JPanel buttons = new JPanel();
        cat = new JButton("CAT");
        items = new JButton("ITEMS");
        feed = new JButton("FEED");
        shop = new JButton("SHOP");
        save = new JButton("SAVE");
        cat.addActionListener(this);
        items.addActionListener(this);
        feed.addActionListener(this);
        shop.addActionListener(this);
        save.addActionListener(this);
        buttons.add(cat);
        buttons.add(items);
        buttons.add(feed);
        buttons.add(shop);
        buttons.add(save);
        buttons.add(quit);
        mainGameFrame.add(buttons, BorderLayout.SOUTH);
    }

    /*
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

     */
    /*
    // MODIFIES: this
    // EFFECTS: display game menu, process command, saves user file when quit
    private void gameMenu() {
        // FORMAT REFERENCE FROM BANK TELLER APP
        boolean menuRunning = true;
        while (menuRunning) {
            displayGameMenu();
            Scanner input = new Scanner(System.in);
            String nextMove = input.nextLine();
            if (nextMove.equals("q")) {
                user.saveLastLogin();
                saveUser();
                System.exit(0);

            } else {
                processGameMenuCommand(nextMove);
            }

        }
    }

     */

    // EFFECTS: saves the User to file
    private void saveUser() {
        try {
            user.saveLastLogin();
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
            JOptionPane.showMessageDialog(null, "You don't have anything to feed your cat!");
        } else {
            user.getCat().feed(user.getInventory().get(0));
            if (user.getInventory().get(0).getName().equals("Diet food")) {
                JOptionPane.showMessageDialog(null, "Consumed 1 Diet Food, your cat hates it!");
            } else {
                JOptionPane.showMessageDialog(null, "Consumed 1 "
                        + user.getInventory().get(0).getName() + "\n\n Your cat grew friendlier to you!");
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
    // TODO MOD LATER
    /*
    private void shopMenu() {
        System.out.println("Your Balance: " + user.getBalance());
        printShopCatalogue();
        displayShopMenu();
        Scanner input = new Scanner(System.in);
        String nextMove = input.nextLine();
        processShopMenuCommand(nextMove);
    }

     */

    // REQUIRES: shop's catalogue must not be empty
    // EFFECT: print every item with values of variables in shop catalogue
    private String printShopCatalogue() {
        List<Food> shopInventory = shopInstance.getCatalogue();
        String output = "";
        for (Food f : shopInventory) {
            output = output + ("<html>" + "Item: " + f.getName());
            output = output + (" Price: " + f.getPrice()
                    + " Energy: " + f.getAddEnergyLevel()
                    + " Happiness: " + f.getAddHappiness()
                    + " Hunger: " + f.getAddHunger() + "<br />");
        }
        return output;
    }


    // REQUIRES: cat must be initialized
    // EFFECTS: print user's pet attributes
    private void viewCat() {
        System.out.println("Breed: " + user.getCat().getBreed());
        System.out.println("Energy: " + user.getCat().getEnergyLevel());
        System.out.println("Happiness: " + user.getCat().getHappiness());
        System.out.println("Hunger: " + user.getCat().getHungerLevel() + "\n");
    }


    // EFFECTS: display first menu options
    private void displayFirstMenu() {
        System.out.println("Welcome to my Virtual Cat Game!");
        System.out.println("===============================");
        System.out.println("play........................[p]");
        System.out.println("quit........................[q]\n");
    }
    /*
    // MODIFIES: this
    // EFFECTS: process user command on first menu options
    private void processFirstMenuCommand(String nextMove) {
        if (nextMove.equals("p")) {
            if (!loadUser()) {
                System.out.println("Generating cat............");
                user = new User(LocalDate.now().toString());
                System.out.println("You have a " + user.getCat().getBreed() + " cat!");
            }

            gameMenu();
        } else {
            System.out.println("invalid input.............");
        }
    }

     */

    // MODIFIES: this
    // EFFECTS: load user from user.json file saved, return true if successful, false if unable to read
    private boolean loadUser() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded game from " + JSON_STORE);
            // after user is loaded, calculate the stat decay
            user.statDecay();
            return true;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
            return false;
        }
    }
    /*

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


     */

    // EFFECTS: display shop menu options
    private void displayShopMenu() {
        System.out.println("Buy canned salmon?...........[cs]");
        System.out.println("Buy dryTreat?................[dt]");
        System.out.println("Buy dietFood?................[df]\n");
    }

    // MODIFIES: this
    // TODO MOD LATER
    // EFFECTS: process user command on shop menu options
    /*
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
    */
    public void firstMenuActionPerfomed(ActionEvent e) {
        if (e.getSource() == play) {
            playResponse();
        }
        if (e.getSource() == quit) {
            quitResponse();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        firstMenuActionPerfomed(e);
        gameMenuActionPerformed(e);
        shopMenuActionPerformed(e);
    }

    public void shopMenuActionPerformed(ActionEvent e) {
        if (e.getSource() == buyCS) {
            user.purchase(shopInstance.getCannedSalmon());
        }
        if (e.getSource() == buyDF) {
            user.purchase(shopInstance.getDietFood());
        }
        if (e.getSource() == buyDT) {
            user.purchase(shopInstance.getDryTreats());
        }
    }

    public void gameMenuActionPerformed(ActionEvent e) {
        if (e.getSource() == cat) {
            initializeCatMenu();
        }
        if (e.getSource() == feed) {
            feedCat();
        }
        if (e.getSource() == save) {
            saveUser();
            JOptionPane.showMessageDialog(null, "SAVING GAME FILE");
        }
        if (e.getSource() == shop) {
            initializeShopMenu();
        }
        if (e.getSource() == items) {
            initializeItemsMenu();
        }
        if (e.getSource() == goBackToGameMenu) {
            mainGameFrame.setVisible(true);
        }
    }

    private void quitResponse() {
        if (!isNull(user)) {
            user.saveLastLogin();
            saveUser();
            JOptionPane.showMessageDialog(null, "SAVING GAME FILE");
        }
        JOptionPane.showMessageDialog(null, "QUITING GAME");
        System.exit(0);
    }

    private void playResponse() {
        if (loadUser()) {
            JOptionPane.showMessageDialog(null, "LOADING LAST SAVE");
        } else {
            user = new User(LocalDate.now().toString());
        }
        // Go to next menu, display loads file automatically
        // display another frame?
        initializeMainGameFrame();
    }
}