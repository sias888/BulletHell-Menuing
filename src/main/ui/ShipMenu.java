package ui;


import exceptions.FullConfigListException;
import exceptions.InvalidAppearanceException;
import exceptions.InvalidConfigNumException;
import model.PlayerShip;
import model.SavedPlayerShipConfigs;

import java.util.Scanner;

// UI Functionality and some methods are implemented from Teller App.
// UI menu for all things ship-related.
public class ShipMenu {
    private PlayerShip playerShip;

    private SavedPlayerShipConfigs playerShipConfigs;

    private Scanner scannerInput;
    private String userIn;
    private Boolean end;

    //Constructor
    //Requires: non-null playerShip and playerShipConfig parameters
    //Effects: point this.playerShip to playerShip parameter and point this.playerShipConfig to playerShipConfig
    // parameter, respectively. run the ship menu.
    public ShipMenu(PlayerShip playerShip, SavedPlayerShipConfigs playerShipConfigs) {
        this.playerShip = playerShip;
        this.playerShipConfigs = playerShipConfigs;
        runShipMenu();
    }

    //Modifies: this, startMenu, game
    //Effects: prompt user for changes to ship and bullets. save changes and send to startMenu (which will then send
    //to game).
    public void runShipMenu() {
        init();

        while (!end) {
            display();

            userIn = scannerInput.next();

            switch (userIn) {
                case "q":
                    end = true;
                    break;
                case "v":
                    playerShip.printConfig();
                    break;
                case "n":
                    handleName();
                    break;
                case "e":
                    newSave();
                    break;
                default:
                    runMenuHelper(userIn);
            }
        }
    }

    //Modifies: this, startMenu, game
    //Effect: continues runShipMenu method.
    private void runMenuHelper(String userIn) {
        switch (userIn) {
            case "a":
                handleAppearance();
                break;
            case "b":
                handleBullet();
                break;
            case "d":
                playerShip.setDefault();
                break;
            case "vs":
                handleVewConfigs();
                break;
            case "s":
                handleSave();
                break;
            case "l":
                handleLoad();
                break;
            default:
                System.out.println("Please enter a valid input!");
        }
    }

    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public SavedPlayerShipConfigs getPlayerShipConfigs() {
        return playerShipConfigs;
    }

    //Modifies: this
    //Effects: initializes some variables required for reading user input
    void init() {
        scannerInput = new Scanner(System.in);
        userIn = null;
        end = false;
    }

    //Effects: display ship menu user options
    void display() {
        System.out.println("\nSelect from: ");
        System.out.println("v -> View Current Config");
        System.out.println("n -> Change Name");
        System.out.println("a -> Change Appearance");
        System.out.println("b -> Change Bullet Appearance");
        System.out.println("d -> Reset to Default");
        System.out.println("vs -> View Saved Presets");
        System.out.println("e -> Save a New Ship to an Empty Preset Slot");
        System.out.println("s -> Save Current Config to New Preset Slot");
        System.out.println("l -> Load Config From Saved Presets");
        System.out.println("q -> Back to Start Menu");
    }

    //Modifies: this, game, startMenu
    //Effects: allows user to name playerShip
    private void handleName() {
        System.out.println("Enter desired name:");
        userIn = scannerInput.next();
        playerShip.setName(userIn);
    }

    //Modifies: this, game, startMenu
    //Effects: allows user to select ship appearance from a number of options and saves to playerShip
    private void handleAppearance() {
        while (true) {
            printAppearanceOptions();
            System.out.println("q -> Cancel");
            userIn = scannerInput.next();
            if (userIn.equals("q")) {
                break;
            } else {
                try {
                    playerShip.setShipAppearance(userIn);
                    break;
                } catch (InvalidAppearanceException e) {
                    System.out.println("Please enter a valid appearance option!");
                }
            }
        }
    }

    //Effects: prints out ship appearance options
    private void printAppearanceOptions() {
        System.out.println("\nSelect from the following options:");
        System.out.println("1 -> Default");
        System.out.println("2 -> Jester");
        System.out.println("3 -> Trident");
        System.out.println("4 -> Scorpion");
        System.out.println("5 -> Carrier");
        System.out.println("6 -> Stream");
    }

    //Modifies: this, game, startMenu
    //Effects: allows user to change appearance of bullet from select options. Saves selection to playerShip.
    private void handleBullet() {
        while (true) {
            printAppearanceOptionsBullet();
            System.out.println("q -> Cancel");
            userIn = scannerInput.next();
            if (userIn.equals("q")) {
                break;
            } else {
                try {
                    playerShip.setBulletAppearance(userIn);
                    break;
                } catch (InvalidAppearanceException e) {
                    System.out.println("Please enter a valid appearance option!");
                }
            }
        }

    }

    //Effects: display bullet appearance options
    private void printAppearanceOptionsBullet() {
        System.out.println("\nSelect from the following options:");
        System.out.println("1 -> Blue");
        System.out.println("2 -> Violet");
        System.out.println("3 -> Light Blue");
        System.out.println("4 -> Green");
        System.out.println("5 -> Yellow");
    }

    //Effect: shows player all saved PlayerShip configs from playerShipConfigs.
    private void handleVewConfigs() {
        playerShipConfigs.viewConfigs();
    }

    //Modifies: this, startMenu
    //Effects: saves current playerShip as a config to playerShipConfigs. If not full, create a new config and save.
    //Else, prompt the user as to which config to replace with current config.
    private void handleSave() {
        try {
            playerShipConfigs.addConfig(playerShip);
        } catch (FullConfigListException | CloneNotSupportedException e) {
            boolean end = false;
            while (!end) {
                System.out.println("\nYou've reached the maximum number of saves! Pick one to override.");
                handleVewConfigs();
                System.out.println("q -> Cancel Save");
                userIn = scannerInput.next();
                if ("q".equals(userIn)) {
                    end = true;
                } else {
                    try {
                        playerShipConfigs.overrideConfig(userIn, playerShip);
                        end = true;
                    } catch (InvalidConfigNumException | NumberFormatException | CloneNotSupportedException exception) {
                        System.out.println("Please enter a valid input!");
                    }
                }
            }
        }
    }

    //Modifies: this, game, startMenu
    //Effects: allows user to set playerShip to any playerShip from playerShipConfigs.
    private void handleLoad() {
        while (true) {
            System.out.println("\nSelect save to load from:");
            handleVewConfigs();
            System.out.println("q -> Cancel Load");
            userIn = scannerInput.next();

            if ("q".equals(userIn)) {
                break;
            } else {
                try {
                    int num = Integer.parseInt(userIn);
                    playerShip = playerShipConfigs.getShipFromSlot(num);
                    System.out.println("Config " + num + " has been successfully loaded!");
                    break;
                } catch (NumberFormatException exception) {
                    System.out.println("Please enter a valid input!");
                } catch (InvalidConfigNumException exception) {
                    System.out.println("Please select a non-empty slot!");
                }
            }
        }
    }

    //Modifies: this
    //Effects: creates a new placeholder playerShip with name parameter "New Ship" and saves to an empty slot in
    // playerShipConfigs
    private void newSave() {
        PlayerShip newPlayerShip = new PlayerShip();
        newPlayerShip.setName("New Ship");
        try {
            playerShipConfigs.addConfig(newPlayerShip);
        } catch (FullConfigListException e) {
            System.out.println("No empty slots!");
        } catch (CloneNotSupportedException exception) {
            exception.printStackTrace();
        }
    }
}
