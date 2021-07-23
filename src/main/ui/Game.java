package ui;

import model.OptionSettings;
import model.PlayerShip;

public class Game {
    OptionSettings optionSettings;
    PlayerShip playerShip;

    public Game(OptionSettings optionSettings, PlayerShip playerShip) {
        this.playerShip = playerShip;
        this.optionSettings = optionSettings;
        runGame();
    }

    private void runGame() {
    }
}
