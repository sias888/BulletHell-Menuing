package ui;

import Exceptions.InvalidAppearanceException;
import model.PlayerShip;

import java.util.Scanner;

public class ShipMenu {
    PlayerShip playerShip;
    private Scanner userInput;
    private String userIn;
    private Boolean end;

    public ShipMenu(PlayerShip playerShip) {
        this.playerShip = playerShip;
        runShipMenu();
    }

    void runShipMenu() {
        init();

        while (!end) {
            display();

            userIn = userInput.next();
            userIn.toLowerCase();

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
                case "a":
                    handleAppearance();
                    break;
                case "b":
                    handleBullet();
                    break;
                default:
                    System.out.println("Please enter a valid input!");
            }
        }
    }

    void init() {
        userInput = new Scanner(System.in);
        userIn = null;
        end = false;
    }

    void display() {
        System.out.println("Select from: ");
        System.out.println("v -> View Current Config");
        System.out.println("n -> Change Name");
        System.out.println("a -> Change Appearance");
        System.out.println("b -> Change Bullet Appearance");
        System.out.println("d -> Reset to Default");
        System.out.println("q -> Back to Start Menu");
    }

    private void handleName() {
        System.out.println("Enter desired name:");
        userIn = userInput.next();
        playerShip.setName(userIn);
    }

    private void handleAppearance() {
        while (true) {
            printAppearanceOptions();
            userIn = userInput.next();
            try {
                playerShip.setShipAppearance(userIn);
                break;
            } catch (InvalidAppearanceException e) {
                System.out.println("Please enter a valid appearance option!");
            }
        }
    }

    //WIP
    private void printAppearanceOptions() {
        System.out.println("Select from the following options:");
    }

    //WIP: need options for appearance
    private void handleBullet() {
        System.out.println("Enter desired bullet appearance:");
        userIn = userInput.next();
        playerShip.setBulletAppearance(userIn);
    }

}
