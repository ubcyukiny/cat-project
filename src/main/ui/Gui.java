package ui;

import ui.PetApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {
    private JFrame frame;
    private JFrame mainGameFrame;
    private JFrame catMenu;

    private JLabel label;

    private JButton play;
    private JButton save;
    private JButton load;
    private JButton quit;
    private JButton cat;
    private JButton items;
    private JButton shop;


    // Constructor
    public Gui() {
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
        play.setText("PLAY");
        quit.setText("QUIT");
        play.addActionListener(this);
        quit.addActionListener(this);
        buttons.add(play);
        buttons.add(quit);
        frame.add(buttons, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            // Go to next menu, display loads file automatically
            // display another frame?
            frame.setVisible(false); //if you want to save the frame
            frame.dispose(); //if you want to kill the frame
            initializeMainGameFrame();
        }
        if (e.getSource() == quit) {
            // quit application AND SAVES !!!
            // TODO SAVES
            System.exit(0);
        }
        if (e.getSource() == cat) {
            // TODO jump to cat screen, view stats
            mainGameFrame.setVisible(false);
            mainGameFrame.dispose();
            initializeCatMenu();
        }
        if (e.getSource() == save) {
            // TODO initialize save, pops up message
        }
        if (e.getSource() == load) {
            // TODO initialize load
        }
        if (e.getSource() == shop) {
            // TODO go to shop menu
        }
        if (e.getSource() == items) {
            // TODO go to inventory menu
        }
    }

    public void initializeCatMenu() {
        catMenu = new JFrame();
        catMenu.setTitle("Cat Menu");
        catMenu.setSize(500, 500);
        catMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        catMenu.setResizable(false);
        catMenu.setVisible(true);
        // add display panel for stats

        // add quit button
        catMenu.add(quit, BorderLayout.SOUTH);
    }

    public void initializeMainGameFrame() {
        mainGameFrame = new JFrame();
        mainGameFrame.setTitle("Virtual Cat Game");
        mainGameFrame.setSize(500, 500);
        mainGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGameFrame.setResizable(false);
        // add cat pictures
        // from http://clipart-library.com/clipart/Lcd5aX5xi.htm
        // set panels for current save status panel, game and buttons
        // add buttons
        initializeCatImage();
        initializeButtonsForGame();
        // add messages, displays save data file
        mainGameFrame.setVisible(true);


    }

    public void initializeCatImage() {
        JPanel catArea = new JPanel();
        JLabel catLabel = new JLabel();
        ImageIcon catIcon = new ImageIcon("./data/cat.png");
        Image cat = catIcon.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);
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
        shop = new JButton("SHOP");
        save = new JButton("SAVE");
        load = new JButton("LOAD");
        cat.addActionListener(this);
        items.addActionListener(this);
        shop.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        buttons.add(cat);
        buttons.add(items);
        buttons.add(shop);
        buttons.add(save);
        buttons.add(load);
        buttons.add(quit);
        mainGameFrame.add(buttons, BorderLayout.SOUTH);
    }

}
