package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BrushesRoom;
import model.Canvas;
import model.DrawingRoom;
import ui.FileHelper;

// represents a GUI for the main menu
public class MainMenu extends WindowGUI implements ActionListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 450;

    private JButton newCanvasButton;
    private JButton loadCanvasButton;
    private JButton settingsButton;

    // EFFECTS: constructs a JFrame GUI window for the "main menu"
    public MainMenu() {
        super(windowLabel);
        initializeWindow(new GridLayout(2, 1), BG_COLOR2, WIDTH, HEIGHT);
    }

    // EFFECTS: initialize GUI window frame for this main menu
    @Override
    protected void initializeWindow(LayoutManager layout, Color color, int width, int height) {
        super.initializeWindow(layout, color, width, height);
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        addTitle(this);
        addButtonPanel(this);
        initializeMenuBar(this);
        menuBar.remove(toolsMenu);
        setVisible(true);
        addWindowListener(this);
    }

    // MODIFIES: parent
    // EFFECTS: creates a JLabel with text and image and adds it to given JFrame
    private void addTitle(JFrame parent) {
        JLabel titleText = new JLabel(windowLabel);
        ImageIcon image = new ImageIcon("data/palette.png");
        titleText.setIcon(image);
        titleText.setHorizontalTextPosition(JLabel.CENTER);
        titleText.setVerticalTextPosition(JLabel.BOTTOM);
        titleText.setIconTextGap(-30);
        titleText.setFont(new Font("Spectral", Font.BOLD, 55));
        titleText.setForeground(new Color(0, 0, 0));
        titleText.setHorizontalAlignment(JLabel.CENTER);
        titleText.setVerticalAlignment(JLabel.CENTER);
        parent.add(titleText);
    }

    // EFFECTS: makes a panel for the buttons
    private void addButtonPanel(JFrame parent) {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JPanel buttonPanel2 = new JPanel(new GridLayout(1, 2));
        buttonPanel.setBackground(BG_COLOR2);
        buttonPanel2.setBackground(BG_COLOR2);
        addNewCanvasButton(buttonPanel2);
        addLoadCanvasButton(buttonPanel2);
        buttonPanel.add(buttonPanel2);
        addSettingsButton(buttonPanel);
        parent.add(buttonPanel);
    }

    // MODIFIES: this, button
    // EFFECTS: creates a new anonymous button, and adds to given parent
    private void addNewCanvasButton(JComponent parent) {
        newCanvasButton = new JButton("New Canvas");
        newCanvasButton.setBounds((WIDTH / 2) - 125, (HEIGHT / 2) - 50, 250, 100);
        newCanvasButton.setFocusable(false);
        newCanvasButton.addActionListener(this);
        parent.add(newCanvasButton);
    }

    // MODIFIES: this, button
    // EFFECTS: creates a new anonymous button, and adds to given parent
    // TODO: label text back to "Load Canvas"
    private void addLoadCanvasButton(JComponent parent) {
        loadCanvasButton = new JButton("Quick Draw");
        loadCanvasButton.setBounds((WIDTH / 2) - 125, (HEIGHT / 2) - 50, 250, 100);
        loadCanvasButton.setFocusable(false);
        loadCanvasButton.addActionListener(this);
        parent.add(loadCanvasButton);
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

    // EFFECTS: depending on button pressed, open different windows
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource() == newCanvasButton) {
            //FileHelper.getFileHelper().loadDrawingRoom();
            String width = JOptionPane.showInputDialog(null,
                    "give width of canvas (600 - 1800)",
                    windowLabel, JOptionPane.QUESTION_MESSAGE);
            String height = null;
            if (width != null) {
                height = JOptionPane.showInputDialog(null,
                        "give height of canvas (600 - 1800)",
                        windowLabel, JOptionPane.QUESTION_MESSAGE);
            }
            if (height != null) {
                Canvas canvas = new Canvas("blank", Integer.parseInt(height), Integer.parseInt(width));
                DrawingRoom.getDrawingRoom().addCanvas(canvas);
                new PaintingEditorMenu(canvas);
                this.dispose();
            }
        } else if (e.getSource() == loadCanvasButton) {
            // TODO: use LoadCanvasMenu here
            new PaintingEditorMenu(new Canvas("blank", 800, 1200));
            this.dispose();
        } else if (e.getSource() == settingsButton) {
            textMenuItemDialog("put settings here :) ");
        }
    }
}
