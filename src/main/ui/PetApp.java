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
    private static final int FRAME_WIDTH = 550;
    private static final int FRAME_HEIGHT = 550;
    private User user;
    private Shop shopInstance;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame currentFrame;
    private JFrame firstGameFrame;
    private JFrame mainGameFrame;
    private JFrame statusFrame;
    private JFrame itemsMenu;
    private JFrame shopMenu;

    private JLabel label;

    private JButton play;
    private JButton save;
    private JButton quit;
    private JButton status;
    private JButton items;
    private JButton shop;
    private JButton feed;
    private JButton goBackToGameMenu;
    private JButton buyCS;
    private JButton buyDT;
    private JButton buyDF;

    // EFFECTS: initialize JsonWriter, JsonReader, shop, then runs the Pet Game
    public PetApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        shopInstance = new Shop();
        runGui();
    }

    // MODIFIES: this
    // EFFECTS: initialize first game frame
    private void runGui() {
        initializeFirstGameFrame();
    }

    // MODIFIES: this
    // EFFECTS: make first game frame, add labels and buttons for first game frame
    public void initializeFirstGameFrame() {
        firstGameFrame = new JFrame();
        setFrameDefault(firstGameFrame,"Virtual Cat Game");
        initializeLabelsForFirstGameFrame();
        initializeButtonsForFirstGameFrame();
        firstGameFrame.setVisible(true);
        currentFrame = firstGameFrame;

    }

    // MODIFIES: JFrame f
    // EFFECTS: Helper, setFrame title to title, frame default properties, size according to constant
    public void setFrameDefault(JFrame f, String title) {
        f.setTitle(title);
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: initialize label for first game frame, add this label to first game frame
    public void initializeLabelsForFirstGameFrame() {
        label = new JLabel("Welcome to my Virtual Cat Game!", SwingConstants.CENTER);
        firstGameFrame.add(label, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: initialize button panel, add button play and quit to panel, add button panel to first game frame
    public void initializeButtonsForFirstGameFrame() {
        JPanel buttons = new JPanel();
        play = new JButton("PLAY");
        quit = new JButton("QUIT");
        play.addActionListener(this);
        quit.addActionListener(this);
        buttons.add(play);
        buttons.add(quit);
        firstGameFrame.add(buttons, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initialize main game frame, add cat image and buttons for main game frame
    public void initializeMainGameFrame() {
        mainGameFrame = new JFrame();
        setFrameDefault(mainGameFrame, "Virtual Cat Game");
        initializeCatImage();
        initializeButtonsForGameMenu();
        mainGameFrame.setVisible(true);
        currentFrame = mainGameFrame;
    }

    // MODIFIES: this
    // EFFECTS: make status frame with go back to game menu button, display status of user and save and cat
    public void initializeStatusFrame() {
        statusFrame = new JFrame();
        setFrameDefault(statusFrame, "Status");
        //  display status
        JLabel statusLabel = new JLabel();
        statusLabel.setText(displayLastLogin() + displayBalance() + user.getCat().printSummary());
        statusFrame.add(statusLabel);
        // add go back button
        JPanel buttons = new JPanel();
        buttons.add(goBackToGameMenu);
        statusFrame.add(buttons, BorderLayout.SOUTH);
        statusFrame.setVisible(true);
        currentFrame = statusFrame;
    }


    // MODIFIES: this
    // EFFECTS: make shop frame, display shop catalogue and add buy button for food listed, also go back button
    public void initializeShopMenu() {
        shopMenu = new JFrame();
        setFrameDefault(shopMenu, "Shop Menu");
        // make label to display Shop catalogue
        JLabel shopCatalogue = new JLabel();
        shopCatalogue.setText(getShopCatalogue());
        shopMenu.add(shopCatalogue);
        // display Buttons buy which one
        initializeButtonsForShop();
        shopMenu.setVisible(true);
        currentFrame = shopMenu;
    }

    // MODIFIES: this
    // EFFECTS: make button panel, consists of buying canned salmon, dry treat or dry food, add panel to shopMenu
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

    // MODIFIES: this
    // EFFECTS: make Inventory frame, display current hold items, with go back to main game frame button
    public void initializeItemsMenu() {
        itemsMenu = new JFrame();
        setFrameDefault(itemsMenu, "Inventory");
        //Display Items
        JLabel myItem = new JLabel();
        myItem.setText(user.itemSummary());
        itemsMenu.add(myItem);
        //Add go back button
        itemsMenu.add(goBackToGameMenu, BorderLayout.SOUTH);
        itemsMenu.setVisible(true);
        currentFrame = itemsMenu;
    }

    // MODIFIES: this
    // EFFECTS: add Pusheen cat png to main game frame according to cat's happiness
    public void initializeCatImage() {
        ImageIcon catIcon;
        JPanel catArea = new JPanel();
        JLabel catLabel = new JLabel();
        if (user.getCat().getHappiness() <= 50) {
            catIcon = new ImageIcon("./data/pusheen-grumpy.png");
        } else if (user.getCat().getHappiness() <= 90) {
            catIcon = new ImageIcon("./data/pusheen-happy.png");
        } else {
            catIcon = new ImageIcon("./data/pusheen-love.png");
        }
        Image cat = catIcon.getImage().getScaledInstance(FRAME_WIDTH - 200, FRAME_HEIGHT - 200,
                Image.SCALE_SMOOTH);
        catLabel.setText("Pusheen artwork from Claire Belton and Andrew Duff");
        ImageIcon catResized = new ImageIcon(cat);
        catLabel.setIcon(catResized);
        catLabel.setHorizontalTextPosition(JLabel.CENTER);
        catLabel.setVerticalTextPosition(JLabel.TOP);
        catArea.add(catLabel);
        mainGameFrame.add(catArea);
    }

    // MODIFIES: this
    // EFFECTS: initialize buttons for main game menu
    public void initializeButtonsForGameMenu() {
        JPanel buttons = new JPanel();
        status = new JButton("STATUS");
        items = new JButton("ITEMS");
        feed = new JButton("FEED");
        shop = new JButton("SHOP");
        save = new JButton("SAVE");
        goBackToGameMenu = new JButton("BACK");
        status.addActionListener(this);
        items.addActionListener(this);
        feed.addActionListener(this);
        shop.addActionListener(this);
        save.addActionListener(this);
        goBackToGameMenu.addActionListener(this);
        buttons.add(status);
        buttons.add(items);
        buttons.add(feed);
        buttons.add(shop);
        buttons.add(save);
        buttons.add(quit);
        mainGameFrame.add(buttons, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: saves the User to file, throw exception if file not found
    private void saveUser() {
        try {
            user.saveLastLogin();
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,"Saved game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Unable to write to file: " + JSON_STORE);
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

    // REQUIRES: shop's catalogue must not be empty
    // EFFECT: print every item with values of variables in shop catalogue
    private String getShopCatalogue() {
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

    // MODIFIES: this
    // EFFECTS: load user from user.json file saved, return true if successful, false otherwise, pop up msg will display
    private boolean loadUser() {
        try {
            user = jsonReader.read();
            JOptionPane.showMessageDialog(null,"Loaded game from " + JSON_STORE);
            // after user is loaded, calculate the stat decay
            user.statDecay();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Unable to read from file: " + JSON_STORE);
            return false;
        }
    }


    // MODIFIES: this
    // EFFECTS: if play or quit button is clicked, run methods associated
    public void firstMenuActionPerfomed(ActionEvent e) {
        if (e.getSource() == play) {
            currentFrame.dispose();
            playResponse();
        }
        if (e.getSource() == quit) {
            currentFrame.dispose();
            quitResponse();
        }
    }

    // MODIFIES: this
    // EFFECTS: do action if buttons are clicked, divided into first menu, main game menu and shop menu
    @Override
    public void actionPerformed(ActionEvent e) {
        firstMenuActionPerfomed(e);
        gameMenuActionPerformed(e);
        shopMenuActionPerformed(e);
    }

    // MODIFIES: this
    // EFFECTS: purchase canned salmon, dry food, or dry treat according to the button clicked
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

    // MODIFIES: this
    // EFFECTS: do response if status, feed, save, shop, items, go back buttons are clicked
    public void gameMenuActionPerformed(ActionEvent e) {
        if (e.getSource() == status) {
            mainGameFrame.setVisible(false);
            initializeStatusFrame();
        }
        if (e.getSource() == feed) {
            gameMenuFeedResponse();
        }
        if (e.getSource() == save) {
            saveUser();
        }
        if (e.getSource() == shop) {
            mainGameFrame.setVisible(false);
            initializeShopMenu();
        }
        if (e.getSource() == items) {
            mainGameFrame.setVisible(false);
            initializeItemsMenu();
        }
        if (e.getSource() == goBackToGameMenu) {
            currentFrame.dispose();
            initializeMainGameFrame();
        }
    }

    // MODIFIES: this
    // EFFECTS: feed cat, refresh main game frame to see cat picture updated
    private void gameMenuFeedResponse() {
        feedCat();
        mainGameFrame.dispose();
        initializeMainGameFrame();
    }

    // MODIFIES: this
    // EFFECTS: save when quit, display pop up msg
    private void quitResponse() {
        if (!isNull(user)) {
            user.saveLastLogin();
            saveUser();
        }
        JOptionPane.showMessageDialog(null, "QUITING GAME");
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: initialize main game frame, and if cannot find user, create an user
    private void playResponse() {
        if (!loadUser()) {
            user = new User(LocalDate.now().toString());
            JOptionPane.showMessageDialog(null, "GENERATING NEW SAVE");
        }
        initializeMainGameFrame();
    }

    // EFFECTS: return a line of user balance
    private String displayBalance() {
        return "<html>" + "Balance: " + user.getBalance() + "<br>";
    }

    // EFFECTS: return a line of user last login time
    private String displayLastLogin() {
        return "<html>" + "Last login at :" + user.getLastLoginString() + "<br>";
    }
}