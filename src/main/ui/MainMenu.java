package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends WindowGUI {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;

    MainMenu() {
        super("put text here");
        initializeWindow(null);
    }

    // EFFECTS: initialize GUI window frame for this main menu
    @Override
    protected void initializeWindow(LayoutManager layout) {
        super.initializeWindow(layout);
        getContentPane().setBackground(new Color(200, 200, 160));
        addButton();
        setVisible(true);
    }

    private void addTitle() {
        JLabel titleText = new JLabel("Paint!");

    }

    // MODIFIES: this, button
    // EFFECTS: creates a new anonymous button, and adds to this JFrame
    private void addButton() {
        JButton button = new JButton("Click Anywhere to Begin Painting!");
        button.setBounds((WIDTH / 2) - 125, (HEIGHT / 2) - 50, 250, 100);

        this.add(button, BorderLayout.CENTER);
    }
}
