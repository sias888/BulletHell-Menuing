package ui;

import model.OptionSettings;
import model.PlayerShip;

public class Game {
    OptionSettings optionSettings;
    PlayerShip playerShip;

    //Constructor
    //Requires: Non-null optionsSettings and playerShip
    //Effects: Runs game with given playerCharacter and option settings
    public Game(OptionSettings optionSettings, PlayerShip playerShip) {
        this.playerShip = playerShip;
        this.optionSettings = optionSettings;
        runGame();
    }

    //Does nothing at the moment--stub and implementation will be added in phase 2 and 3.
    private void runGame() {
    }
}
