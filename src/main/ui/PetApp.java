package ui;

import model.User;

import java.util.Scanner;

public class PetApp {
    private User user;


    public PetApp() {
        runGame();
    }

    private void runGame() {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Welcome to my Virtual Cat Game!");
            System.out.println("===============================");
            System.out.println("play........................[p]");
            System.out.println("quit........................[q]\n");
            Scanner input = new Scanner(System.in);
            String nextMove = input.nextLine();

            if (nextMove == "p") {
                //go to menu
            } else if (nextMove == "q") {
                keepGoing = false;
            }
            else
        }


        // process command

        // initialize cat

        // print shop menu, display

        // check my inventory

        // interact with model.pet

        // view my cat's stats

    }
