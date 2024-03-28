package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents a GUI for the main menu
public class MainMenu extends WindowGUI implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;

    private JButton button;
    private JLabel titleText;

    // EFFECTS: constructs a JFrame GUI window for the "main menu"
    MainMenu() {
        super(windowLabel);
        initializeWindow(new GridLayout(2, 1), new Color(200, 200, 160), WIDTH, HEIGHT);
    }

    // EFFECTS: initialize GUI window frame for this main menu
    @Override
    protected void initializeWindow(LayoutManager layout, Color color, int width, int height) {
        super.initializeWindow(layout, color, width, height);
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        addTitle();
        addButton();
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

    // MODIFIES: this, button
    // EFFECTS: creates a new anonymous button, and adds to this JFrame
    private void addButton() {
        button = new JButton("Click Anywhere to Begin Painting!");
        button.setBounds((WIDTH / 2) - 125, (HEIGHT / 2) - 50, 250, 100);
        button.setFocusable(false);
        button.addActionListener(this);
        //button.setBorder(new MatteBorder(10, 10, 10, 10, Color.CYAN));

        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            new PaintingEditor();
            this.dispose();
        }
    }
}
