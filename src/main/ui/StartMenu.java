package ui;

import exceptions.FullConfigListException;
import exceptions.InvalidAppearanceException;
import model.OptionSettings;
import model.PlayerShip;
import model.SavedPlayerShipConfigs;
import persistence.Reader;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// UI Functionality and some methods are implemented from Teller App.
public class StartMenu {
    static final String GAME_NAME = "PLACEHOLDER";
    private Scanner scanner;
    private String userIn;
    private Boolean end;

    private PlayerShip playerShip = new PlayerShip();
    private SavedPlayerShipConfigs playerShipConfigs = new SavedPlayerShipConfigs();
    private OptionSettings optionSettings = new OptionSettings();


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

            userIn = scanner.next();
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
                default:
                    runHelper(userIn);
            }
        }
    }

    private void runHelper(String userIn) {
        switch (userIn) {
            case "s":
                this.runGame();
                break;
            case "ss":
                this.saveMyShip();
                break;
            case "l":
                System.out.println("Loading saved file will overwrite current My Ship settings. Press y to proceed "
                        + "or any other key to go back.");
                String loadUserIn = scanner.next();
                if (loadUserIn.equals("y")) {
                    this.loadMyShip();
                }
                break;
            default:
                System.out.println("Please enter a valid input!");
        }
    }

    // MODIFIES: This
    // EFFECTS: initializes various variables and prints welcome message.
    void init() {
        scanner = new Scanner(System.in);
        userIn = null;
        end = false;

        System.out.println("Welcome to " + GAME_NAME);
    }

    // EFFECTS: prints list of options for player to select from
    void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("s -> Start");
        System.out.println("m -> My Ship");
        System.out.println("o -> Options");
        System.out.println("ss -> Save My Ship Settings");
        System.out.println("l -> Load My Ship Settings");
        System.out.println("q -> Quit");
    }

    //MODIFIES: this.optionsSettings
    //EFFECTS: Allows user to modify optionsSettings through a menu
    void runOptionsMenu() {
        new OptionsMenu(optionSettings);
    }

    //MODIFIES: this.playerShip
    //EFFECTS: Allows user to modify playerShip and saved ship configs though a menu
    void runShipMenu() {
        ShipMenu shipMenu = new ShipMenu(playerShip, playerShipConfigs);
        playerShipConfigs = shipMenu.getPlayerShipConfigs();
        playerShip = shipMenu.getPlayerShip();
    }

    //MODIFIES: this, game
    //Effect: Runs game. WIP.
    void runGame() {
        new Game(optionSettings, playerShip);
    }

    //Modifies: userData.json
    //Effects: writes MyShip data to userData.json
    void saveMyShip() {
        Writer writer = new Writer("data/userData.json");
        try {
            writer.open();
            writer.write(playerShip, playerShipConfigs);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    //Modifies: this
    //Effects: loads data from userData.json and saves to playerShip and playerShipConfigs
    void loadMyShip() {
        Reader reader = new Reader("data/userData.json");

        try {
            reader.read();
        } catch (InvalidAppearanceException | FullConfigListException e) {
            System.out.println("File held corrupt data...");
        } catch (CloneNotSupportedException exception) {
            System.out.println("Something went wrong...");
        } catch (IOException e) {
            System.out.println("File does not exist...");
        }
        playerShip = reader.getPlayerShip();
        playerShipConfigs = reader.getSavedPlayerShipConfigs();
    }
}
