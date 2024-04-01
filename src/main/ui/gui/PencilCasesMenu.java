package ui.gui;

import model.BrushesRoom;
import model.PencilCase;
import persistence.JsonReader;
import ui.FileHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// represents the popup menu for pencil cases that displays button for all pencil cases created, of which can be
// clicked to display the brushes inside
public class PencilCasesMenu extends WindowGUI {
    protected static final int WIDTH = 300;
    protected static final int HEIGHT = 450;
    private JPanel brushesPanel;
    private GridLayout brushesPanelGrid = new GridLayout(2, 1);
    private final BrushesRoom brushesRoom = BrushesRoom.getBrushesRoom();
    private JButton addButton;
    private JButton deleteButton;
    private WindowGUI parent;

    // EFFECTS: constructs a PencilCasesMenu and initializes the window
    PencilCasesMenu(WindowGUI parent) {
        super(windowLabel);
        this.parent = parent;
        initializeWindow(new BorderLayout(), BG_COLOR, WIDTH, HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: function for initializing a window, sets
    //          layout of window by given layout, and window background
    //          color by given color, adds scroll bar and pencilcases buttons
    @Override
    protected void initializeWindow(LayoutManager layout, Color color, int width, int height) {
        super.initializeWindow(layout, color, width, height);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setMaximumSize(new Dimension(width, height));
        addBrushesPanel(this);
        addScrollPane();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds a scrollpane to the JFrame
    private void addScrollPane() {
        JScrollPane scrollPane = new JScrollPane(brushesPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
    }

    // MODIFIES: this
    // EFFECTS: adds the brushes panel, which has an add and delete button, and all the pencilcases
    private void addBrushesPanel(WindowGUI parent) {
        brushesPanel = new JPanel(brushesPanelGrid);
        addButton = new JButton("Create a PencilCase");
        addButton.setPreferredSize(new Dimension(100, 100));
        addButton.addActionListener(this);
        brushesPanel.add(addButton);
        deleteButton = new JButton("Delete a PencilCase");
        deleteButton.setPreferredSize(new Dimension(100, 100));
        deleteButton.addActionListener(this);
        brushesPanel.add(deleteButton);
        addCases();
        parent.add(brushesPanel);
    }

    // Inspired from StackOverFlow answer by Scott Smith
    // https://stackoverflow.com/questions/2430008
    // EFFECTS: add cases to GUI, all with separate action listeners
    private void addCases() {
        Map<JButton, Integer> map = new HashMap<>();
        int i = 1;
        for (PencilCase pc : brushesRoom.getCases()) {
            brushesPanelGrid.setRows(brushesPanelGrid.getRows() + 1);
            JButton b = new JButton(pc.getName());
            brushesPanel.add(b);
            this.add(brushesPanel);
            // Add a mapping
            map.put(b, i);
            i++;
            b.addActionListener(e -> {
                System.out.println(pc.getName());
                new BrushesMenu(pc, (PaintingEditorMenu) parent);
                this.dispose();
            });
        }
    }

    // MODIFIES: BrushesRoom
    // EFFECTS: shows an input window for the addButton
    private void addButtonPopup() {
        String name = JOptionPane.showInputDialog(null,
                "Type name of PencilCase",
                windowLabel, JOptionPane.QUESTION_MESSAGE);
        if (name != null && !isNameAlreadyExist(name) && name.length() > 0) {
            brushesRoom.addPencilCase(new PencilCase(name));
            textMenuItemDialog("Successfully created");
            addCases();
            repaint();
            FileHelper.getFileHelper().saveBrushesRoom();
            new PencilCasesMenu(parent);
            this.dispose();
        } else {
            textMenuItemDialog("Invalid PencilCase name");
        }
    }

    // MODIFIES: BrushesRoom
    // EFFECTS: shows an input window for the delete button
    private void deleteButtonPopup() {
        String name = JOptionPane.showInputDialog(null,
                "Type name of PencilCase to delete (case sensitive)",
                windowLabel, JOptionPane.QUESTION_MESSAGE);
        if (name != null && isNameAlreadyExist(name)) {
            brushesRoom.deleteCase(name);
            textMenuItemDialog("Successfully deleted");
            addCases();
            repaint();
            FileHelper.getFileHelper().saveBrushesRoom();
            new PencilCasesMenu(parent);
            this.dispose();
        } else {
            textMenuItemDialog("Invalid PencilCase name");
        }
    }

    // EFFECTS: returns true if case name is in brushesRoom, false otherwise
    private boolean isNameAlreadyExist(String name) {
        boolean invalidName = false;
        for (PencilCase pc : brushesRoom.getCases()) {
            if (name.equals(pc.getName())) {
                invalidName = true;
                break;
            }
        }
        return invalidName;
    }

    // EFFECTS: shows popups for addButton and deleteButton
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addButtonPopup();
        } else if (e.getSource() == deleteButton) {
            deleteButtonPopup();
        }
    }
}
