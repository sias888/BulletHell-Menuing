package ui.gui;

import javax.swing.*;
import java.awt.*;

public abstract class GuiFrame extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    JPanel backgroundPanel = new JPanel();


    public GuiFrame() {
    }


    //Modifies: this, children
    //Effects: initialize frame to selected parameters. Set backgound, layout manager, title, and sizes.
    protected void initFrame() {
        ImageIcon imageIcon = new ImageIcon("src/main/resources/Spaceship_01.png");
        setIconImage(imageIcon.getImage());
        setLayout(new BorderLayout());
        setTitle("Placeholder Game");
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    //Modifies: this, children
    //Effects: creates panel which displays background image.
    protected void setBackground() {
        backgroundPanel.setLayout(new GridLayout(0, 1));
        backgroundPanel.setSize(new Dimension(0, 0));

        JLabel background = new JLabel(new ImageIcon("src/main/resources/SpaceBackground.png"));
        backgroundPanel.add(background);

        add(backgroundPanel, BorderLayout.CENTER);
    }

}
