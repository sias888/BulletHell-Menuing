package ui.gui;

import model.PlayerShip;
import model.SavedPlayerShipConfigs;
import ui.Game;
import ui.StartMenu;

import javax.swing.*;
import java.awt.*;

public class GuiStart extends GuiFrame {

    private PlayerShip playerShip;
    private SavedPlayerShipConfigs playerShipConfigs;
    private StartMenu startMenu;
    private JPanel buttonPanel = new JPanel();

    public GuiStart(StartMenu startMenu) {
        super();
        this.startMenu = startMenu;
        loadShipsFromStart();
        initializeStartGraphics();
    }

    public void initializeStartGraphics() {
        initFrame();
        setBackground();
        makeButtons();

        setVisible(true);
    }


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

    private void quit() {
        saveShips();
        this.dispose();
    }

    private void runGame() {
        new Game(startMenu.getOptionSettings(), playerShip);
    }

    private void runShipGui() {
        GuiShip guiShip = new GuiShip(this);
    }

    private void saveShips() {
        startMenu.setPlayerShip(playerShip);
        startMenu.setPlayerShipConfigs(playerShipConfigs);
        startMenu.saveMyShip();
    }

    private void loadShipsFromStart() {
        this.playerShip = startMenu.getPlayerShip();
        this.playerShipConfigs = startMenu.getPlayerShipConfigs();
    }

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
