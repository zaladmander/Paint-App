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

public class PencilCasesMenu extends WindowGUI {
    protected static final int WIDTH = 300;
    protected static final int HEIGHT = 450;
    private JPanel brushesPanel;
    private GridLayout brushesPanelGrid = new GridLayout(2, 1);
    private final BrushesRoom brushesRoom = BrushesRoom.getBrushesRoom();
    private JsonReader jsonReader = new JsonReader(FileHelper.JSON_STORE_BR);
    private JButton addButton;
    private JButton deleteButton;
    private WindowGUI parent;

    PencilCasesMenu(WindowGUI parent) {
        super(windowLabel);
        this.parent = parent;
        brushesRoom.reset();
        try {
            jsonReader.readBrushesRoom();
        } catch (IOException e) {
            System.out.println("Unable to load file: " + FileHelper.JSON_STORE_BR);
        }
        initializeWindow(new BorderLayout(), BG_COLOR, WIDTH, HEIGHT);
    }

    @Override
    protected void initializeWindow(LayoutManager layout, Color color, int width, int height) {
        super.initializeWindow(layout, color, width, height);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setMaximumSize(new Dimension(width, height));
        addBrushesPanel(this);
        addScrollPane();
        this.setVisible(true);
    }

    private void addScrollPane() {
        JScrollPane scrollPane = new JScrollPane(brushesPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addButtonPopup();
        } else if (e.getSource() == deleteButton) {
            deleteButtonPopup();
        }
    }
}
