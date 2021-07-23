package ui;

import model.OptionSettings;
import model.PlayerShip;

import java.util.Scanner;

public class OptionsMenu {
    OptionSettings optionSettings;
    private Scanner userInput;
    private String userIn;
    private Boolean end;

    public OptionsMenu(OptionSettings optionSettings) {
        this.optionSettings = optionSettings;
        runOptionsMenu();
    }

    private void runOptionsMenu() {
        init();

        while (!end) {
            display();

            userIn = userInput.next();
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
    }

    private void init() {
        userInput = new Scanner(System.in);
        userIn = null;
        end = false;
    }

    private void display() {
        System.out.println("Select from: ");
        System.out.println("a -> Turn On Background Noise");
        System.out.println("b -> Turn Off Background Noise");
        System.out.println("c -> Turn On Bullet Noise");
        System.out.println("d -> Turn Off Bullet Noise");
        System.out.println("q -> Return to Start Menu");
    }
}
