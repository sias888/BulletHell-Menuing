package ui.gui;

import exceptions.FullConfigListException;
import exceptions.InvalidConfigNumException;
import model.PlayerShip;
import model.SavedPlayerShipConfigs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiShip extends GuiFrame {
    private PlayerShip playerShip;
    private SavedPlayerShipConfigs playerShipConfigs;
    private GuiStart guiStart;
    private JPanel currentShipPanel = new JPanel();

    public GuiShip(GuiStart guiStart) {
        super();
        this.guiStart = guiStart;
        this.playerShip = guiStart.getPlayerShip();
        this.playerShipConfigs = guiStart.getPlayerShipConfigs();

        initFrame();
        setBackground();

        displayPlayerShips();
        makeButtons();
        seePlayerShipSelection();

        setVisible(true);
    }


    //GUI effect
    //Modifies: this
    //Effects: displays all PlayerShips and names in playerShipConfigs. Also displays buttons that can be used to select
    //a playerShip.
    private void displayPlayerShips() {
        Panel shipPanel = new Panel();
        shipPanel.setLayout(new GridLayout(1, 0));
        shipPanel.setSize(new Dimension(0, 500));

        List<PlayerShip> playerShips = getShips();

        for (PlayerShip playerShip : playerShips) {
            Icon icon = new ImageIcon(playerShip.getShipAppearance());
            JButton shipButton = new JButton();

            shipButton.setText(playerShip.getName());
            shipButton.setHorizontalTextPosition(SwingConstants.CENTER);
            shipButton.setVerticalTextPosition(SwingConstants.BOTTOM);

            shipButton.setIcon(icon);
            shipButton.setHorizontalTextPosition(SwingConstants.CENTER);
            shipButton.setVerticalTextPosition(SwingConstants.TOP);
            shipButton.setFocusable(false);

            shipButton.addActionListener((event) -> selectShip(playerShip));

            shipPanel.add(shipButton);
        }

        add(shipPanel);
    }

    public void seePlayerShipSelection() {
        currentShipPanel.removeAll();

        JLabel currentShip = new JLabel("Selected: " + playerShip.getName());
        currentShip.setHorizontalTextPosition(SwingConstants.CENTER);
        currentShip.setVerticalTextPosition(SwingConstants.TOP);

        ImageIcon icon = new ImageIcon(playerShip.getShipAppearance());
        Image iconImg = icon.getImage().getScaledInstance(180,180, Image.SCALE_FAST);
        ImageIcon currentShipIcon = new ImageIcon(iconImg);
        currentShip.setIcon(currentShipIcon);


        currentShipPanel.add(currentShip);

        add(currentShipPanel, BorderLayout.NORTH);
        setVisible(true);
    }

    private void selectShip(PlayerShip playerShip) {
        this.playerShip = playerShip;
        save();
        seePlayerShipSelection();
    }

    private List<PlayerShip> getShips() {
        List<PlayerShip> playerShips = new ArrayList<>();

        boolean end = false;
        int i = 0;

        while (!end) {
            try {
                playerShips.add(playerShipConfigs.getShipFromSlot(i));
                i++;
            } catch (InvalidConfigNumException exception) {
                end = true;
            }
        }

        return playerShips;
    }

    //GUI effect
    //Modifies: this
    //Effects: Displays buttons that allow user to add current ship to preset list. Will send message if list is full.
    // Also displays quit button.
    private void makeButtons() {
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.setSize(new Dimension(0, 0));

        JButton quitButton = new JButton("Back to Start");
        quitButton.addActionListener((event) -> quit());
        quitButton.setFocusable(false);

        JButton addButton = new JButton("Add Selected to Empty Slot");
        addButton.addActionListener((event) -> {
            try {
                playerShipToSaves();
            } catch (FullConfigListException e) {
                JOptionPane.showMessageDialog(this, "All Slots Full!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        addButton.setFocusable(false);

        buttonPanel.add(addButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void quit() {
        save();
        this.dispose();
    }

    private void save() {
        guiStart.setPlayerShip(playerShip);
        guiStart.setPlayerShipConfigs(playerShipConfigs);
    }

    //Requires: playerShipConfigs is not full
    //Modifies: this, guiStart
    //Effects: Saves current player ship to playerShipConfigs.
    private void playerShipToSaves() throws FullConfigListException {
        try {
            playerShipConfigs.addConfig(playerShip);
        } catch (CloneNotSupportedException exception) {
            exception.printStackTrace();
        }
    }


    public PlayerShip getPlayerShip() {
        return playerShip;
    }


    public SavedPlayerShipConfigs getPlayerShipConfigs() {
        return playerShipConfigs;
    }
}
