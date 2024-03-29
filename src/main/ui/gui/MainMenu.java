package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a GUI for the main menu
public class MainMenu extends WindowGUI implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;

    private JButton paintButton;
    private JButton settingsButton;
    private JLabel titleText;
    private JPanel buttonPanel;

    // EFFECTS: constructs a JFrame GUI window for the "main menu"
    public MainMenu() {
        super(windowLabel);
        initializeWindow(new GridLayout(2, 1), new Color(200, 200, 160), WIDTH, HEIGHT);
    }

    // EFFECTS: initialize GUI window frame for this main menu
    @Override
    protected void initializeWindow(LayoutManager layout, Color color, int width, int height) {
        super.initializeWindow(layout, color, width, height);
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        addTitle();
        addButtonPanel();
        setVisible(true);
    }

    private void addTitle() {
        titleText = new JLabel();
        titleText.setText(windowLabel);
        //titleText.setBounds(0, 0, WIDTH, HEIGHT);
        titleText.setFont(new Font("Arial", Font.ITALIC, 50));
        titleText.setHorizontalAlignment(JLabel.CENTER);
        titleText.setVerticalAlignment(JLabel.CENTER);
        this.add(titleText);
    }

    // EFFECTS: makes a panel for the buttons
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        addPaintButton(buttonPanel);
        addSettingsButton(buttonPanel);
        this.add(buttonPanel);
    }

    // MODIFIES: this, button
    // EFFECTS: creates a new anonymous button, and adds to given parent
    private void addPaintButton(JComponent parent) {
        paintButton = new JButton("Click Anywhere to Begin Painting!");
        paintButton.setBounds((WIDTH / 2) - 125, (HEIGHT / 2) - 50, 250, 100);
        paintButton.setFocusable(false);
        paintButton.addActionListener(this);
        //button.setBorder(new MatteBorder(10, 10, 10, 10, Color.CYAN));

        parent.add(paintButton);
    }

    // MODIFIES: this, button
    // EFFECTS: creates a new anonymous button, and adds to given parent
    private void addSettingsButton(JComponent parent) {
        settingsButton = new JButton("Settings");
        settingsButton.setBounds((WIDTH / 2) - 125, (HEIGHT / 2) - 50, 250, 100);
        settingsButton.setFocusable(false);
        settingsButton.addActionListener(this);

        parent.add(settingsButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == paintButton) {
            new PaintingEditor();
            this.dispose();
        } else if (e.getSource() == settingsButton) {
            textMenuItemDialog("put settings here :) ");
        }
    }
}
