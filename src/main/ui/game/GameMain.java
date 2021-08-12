package ui.game;

import model.OptionSettings;
import model.PlayerShip;
import ui.gui.GuiFrame;

import javax.swing.*;
import java.awt.*;

//Main game function--empty at the moment
public class GameMain extends JFrame {
    Boolean bulletSound;
    Boolean backgroundSound;
    String shipModel;
    String bulletModel;

    //Constructor
    //Requires: Non-null optionsSettings and playerShip
    //Effects: Runs game with given playerCharacter and option settings
    public GameMain(OptionSettings optionSettings, PlayerShip playerShip) {
        shipModel = playerShip.getShipAppearance();
        bulletModel = playerShip.getBulletAppearance();
        bulletSound = optionSettings.getBulletSound();
        backgroundSound = optionSettings.getBackgroundSound();

        init();

        runGame();

        setVisible(true);
    }

    private void init() {
        ImageIcon imageIcon = new ImageIcon("src/main/resources/Spaceship_01.png");
        setIconImage(imageIcon.getImage());
        setTitle("Placeholder Game");
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    //Does nothing at the moment...
    private void runGame() {

    }
}
