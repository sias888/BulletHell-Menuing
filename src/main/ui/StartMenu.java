package ui;

import model.OptionSettings;
import model.PlayerShip;

import java.util.Scanner;

public class StartMenu {
    static final String GAME_NAME = "PLACEHOLDER";
    private Scanner userInput;
    private String userIn;
    private Boolean end;

    private PlayerShip playerShip;
    private OptionSettings optionSettings;


    //Constructor for StartMenu class. Runs runMenu() method.
    public StartMenu() {
        runMenu();
    }

    // MODIFIES: this
    // EFFECTS: runs main menu of game. processes user input.
    private void runMenu() {
        init();

        while (!end) {
            displayMenu();

            userIn = userInput.next();
            userIn = userIn.toLowerCase();

            switch (userIn) {
                case "q":
                    System.out.println("Exiting game...");
                    end = true;
                    break;
                case "o":
                    this.runOptionsMenu();
                    break;
                case "m":
                    this.runShipMenu();
                    break;
                case "s":
                    this.runGame();
                    break;
                default:
                    System.out.println("Please enter a valid input!");
            }
        }
    }

    // MODIFIES: This
    // EFFECTS: initializes various variables and prints welcome message.
    void init() {
        userInput = new Scanner(System.in);
        userIn = null;
        end = false;

        playerShip = new PlayerShip();
        optionSettings = new OptionSettings();

        System.out.println("Welcome to " + GAME_NAME);
    }

    // EFFECTS: prints list of options for player to select from
    void displayMenu() {
        System.out.println("Select from:");
        System.out.println("s -> Start");
        System.out.println("m -> My Ship");
        System.out.println("o -> Options");
        System.out.println("q -> Quit");
    }

    //MODIFIES: this.optionsSettings
    //EFFECTS: Allows user to modify optionsSettings through a menu
    void runOptionsMenu() {
       new OptionsMenu(optionSettings);
    }

    //MODIFIES: this.playerShip
    //EFFECTS: Allows user to modify playerShip though a menu
    void runShipMenu() {
        new ShipMenu(playerShip);
    }

    void runGame() {
        new Game(optionSettings, playerShip);
    }


}
