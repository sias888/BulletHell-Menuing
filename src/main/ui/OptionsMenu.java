package ui;

import model.OptionSettings;

import java.util.Scanner;

// UI Functionality and methods are implemented from Teller App.
public class OptionsMenu {
    private OptionSettings optionSettings;
    private Scanner scanner;
    private String userIn;
    private Boolean end;

    //Constructor
    //Requires: non-null optionsSettings parameter
    //Effects: point this.optionsSettings to optionsSettings parameter. runOptionsMenu.
    public OptionsMenu(OptionSettings optionSettings) {
        this.optionSettings = optionSettings;
        runOptionsMenu();
    }

    //Modifies: this, startMenu, game
    //Effects: prompt user to select from a number of sound-related options. Save selection to optionSettings.
    private void runOptionsMenu() {
        init();

        while (!end) {
            display();

            userIn = scanner.next();
            userIn.toLowerCase();

            switch (userIn) {
                case "q":
                    end = true;
                    break;
                case "a":
                    optionSettings.setBackgroundSound(true);
                    break;
                case "b":
                    optionSettings.setBackgroundSound(false);
                    break;
                default:
                    runOptionsMenuHelper(userIn);
            }
        }
    }

    private void runOptionsMenuHelper(String userIn) {
        switch (userIn) {
            case "c":
                optionSettings.setBulletSound(true);
                break;
            case "d":
                optionSettings.setBulletSound(false);
                break;
            default:
                System.out.println("Please enter a valid input!");
        }
    }

    //Modifies: this
    //Effects: initialize variables used for user prompt
    private void init() {
        scanner = new Scanner(System.in);
        userIn = null;
        end = false;
    }

    //Effects: prints options for user to select from.
    private void display() {
        System.out.println("\nSelect from: ");
        System.out.println("a -> Turn On Background Noise");
        System.out.println("b -> Turn Off Background Noise");
        System.out.println("c -> Turn On Bullet Noise");
        System.out.println("d -> Turn Off Bullet Noise");
        System.out.println("q -> Return to Start Menu");
    }
}
