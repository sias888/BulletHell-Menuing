package ui.gui;

import model.PlayerShip;
import model.SavedPlayerShipConfigs;
import ui.Game;
import ui.StartMenu;

import javax.swing.*;
import java.awt.*;

// Start-menu's counterpart in GUI form.
public class GuiStart extends GuiFrame {

    private PlayerShip playerShip;
    private SavedPlayerShipConfigs playerShipConfigs;
    private StartMenu startMenu;
    private JPanel buttonPanel = new JPanel();

    //Constructor
    //Instantiates startMenu, gets ship and ship config parameters, and performs initialization functions for gui menu.
    public GuiStart(StartMenu startMenu) {
        super();
        this.startMenu = startMenu;
        loadShipsFromStart();
        initializeStartGraphics();
    }

    //modifies: this
    //Effects: Initializes gui with desired visuals. Makes buttons.
    public void initializeStartGraphics() {
        initFrame();
        setBackground();
        makeButtons();

        setVisible(true);
    }


    //Modifies: this, shipGui
    //Effects: creates multiple interactive buttons which user can select from as they choose.
    private void makeButtons() {
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.setSize(new Dimension(0, 0));

        JButton quitButton = new JButton("Back to console");
        quitButton.addActionListener((event) -> quit());
        quitButton.setFocusable(false);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener((event) -> runGame());
        startButton.setFocusable(false);

        JButton shipButton = new JButton("Ship Options");
        shipButton.addActionListener((event) -> runShipGui());
        shipButton.setFocusable(false);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener((event) -> saveShips());
        saveButton.setFocusable(false);

        JButton loadButton = new JButton("Load From File");
        loadButton.addActionListener((event) -> loadFromFile());
        loadButton.setFocusable(false);

        buttonPanel.add(startButton);
        buttonPanel.add(shipButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    //Modifies: this, startMenu
    //Effects: saves ship and configs before disposing startGui window.
    private void quit() {
        saveShips();
        this.dispose();
    }

    //Modifies: game
    //Effects: runs game with this.playerShip and options settings as parameters.
    private void runGame() {
        new Game(startMenu.getOptionSettings(), playerShip);
    }

    //Modifies: guiShip, this
    //Effects: runs shipGui menu for user access.
    private void runShipGui() {
        GuiShip guiShip = new GuiShip(this);
    }

    //Modifies: this, startMenu, userData
    //Effects: saves ship and playerShipConfig to startMenu and calls startMenu's saveMyShip function to save locally.
    private void saveShips() {
        startMenu.setPlayerShip(playerShip);
        startMenu.setPlayerShipConfigs(playerShipConfigs);
        startMenu.saveMyShip();
    }

    //Modifies: this
    //Effects: gets playerShip and playerShipConfigs from startMenu
    private void loadShipsFromStart() {
        this.playerShip = startMenu.getPlayerShip();
        this.playerShipConfigs = startMenu.getPlayerShipConfigs();
    }

    //Modifies: this, startMenu
    //Effects: calls startMenu's loadMyShip() function and gets results.
    private void loadFromFile() {
        startMenu.loadMyShip();
        loadShipsFromStart();
    }

    public PlayerShip getPlayerShip() {
        return playerShip;
    }

    public void setPlayerShip(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }

    public SavedPlayerShipConfigs getPlayerShipConfigs() {
        return playerShipConfigs;
    }

    public void setPlayerShipConfigs(SavedPlayerShipConfigs playerShipConfigs) {
        this.playerShipConfigs = playerShipConfigs;
    }
}
