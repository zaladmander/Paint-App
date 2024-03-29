package ui.gui;

import model.Brush;
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

// represents the popup menu for brushes in a pencilcase
public class BrushesMenu extends WindowGUI {
    protected static final int WIDTH = 300;
    protected static final int HEIGHT = 450;
    private JPanel brushesPanel;
    private GridLayout brushesPanelGrid = new GridLayout(2, 1);
    private final BrushesRoom brushesRoom = BrushesRoom.getBrushesRoom();
    private JsonReader jsonReader = new JsonReader(FileHelper.JSON_STORE_BR);
    private JButton addButton;
    private JButton deleteButton;
    private PencilCase pc;
    private PaintingEditorMenu parent;

    // EFFECTS: construct a BrushesMenu
    BrushesMenu(PencilCase pencilCase, PaintingEditorMenu parent) {
        super(windowLabel);
        pc = pencilCase;
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
        addButton = new JButton("Create a Brush");
        addButton.setPreferredSize(new Dimension(100, 100));
        addButton.addActionListener(this);
        brushesPanel.add(addButton);
        deleteButton = new JButton("Delete a Brush");
        deleteButton.setPreferredSize(new Dimension(100, 100));
        deleteButton.addActionListener(this);
        brushesPanel.add(deleteButton);
        addBrushes();
        parent.add(brushesPanel);
    }

    // Inspired from StackOverFlow answer by Scott Smith
    // https://stackoverflow.com/questions/2430008
    // EFFECTS: add brushes to GUI, all with separate action listeners
    private void addBrushes() {
        Map<JButton, Integer> map = new HashMap<>();
        int i = 1;
        for (Brush br : pc.getBrushes()) {
            brushesPanelGrid.setRows(brushesPanelGrid.getRows() + 1);
            JButton b = new JButton(br.getName());
            brushesPanel.add(b);
            this.add(brushesPanel);
            // Add a mapping
            map.put(b, i);
            i++;
            b.addActionListener(e -> {
                System.out.println(br.getName());
                parent.setCurrentBrush(br);
                parent.initializeMouseInput(br);
                this.dispose();
            });
        }
    }

    // EFFECTS: shows an input window for the addButton
    private void addButtonPopup() {
        String name = JOptionPane.showInputDialog(null,
                "Type name of Brush",
                windowLabel, JOptionPane.QUESTION_MESSAGE);
        if (name != null && !isNameAlreadyExist(name) && name.length() > 0) {
            // TODO: inputs
            Brush brush = new Brush(parent.getCurrentBrush().getSize(), name, parent.getCurrentBrush().getRed(),
                    parent.getCurrentBrush().getGreen(), parent.getCurrentBrush().getBlue(), 1);
            pc = BrushesRoom.getBrushesRoom().getCaseWithName(pc.getName());
            BrushesRoom.getBrushesRoom().getCaseWithName(pc.getName()).addBrush(brush);
            textMenuItemDialog("Successfully created");
            addBrushes();
            repaint();
            FileHelper.getFileHelper().saveBrushesRoom();
            new BrushesMenu(pc, parent);
            this.dispose();
        } else {
            textMenuItemDialog("Invalid Brush name");
        }
    }

    // EFFECTS: shows an input window for the delete button
    private void deleteButtonPopup() {
        String name = JOptionPane.showInputDialog(null,
                "Type name of Brush to delete (case sensitive)",
                windowLabel, JOptionPane.QUESTION_MESSAGE);
        if (name != null && isNameAlreadyExist(name)) {
            pc = BrushesRoom.getBrushesRoom().getCaseWithName(pc.getName());
            brushesRoom.deleteBrush(name, pc);
            textMenuItemDialog("Successfully deleted");
            addBrushes();
            repaint();
            FileHelper.getFileHelper().saveBrushesRoom();
            new BrushesMenu(pc, parent);
            this.dispose();
        } else {
            textMenuItemDialog("Invalid PencilCase name");
        }
    }

    private boolean isNameAlreadyExist(String name) {
        boolean invalidName = false;
        for (Brush b : pc.getBrushes()) {
            if (name.equals(b.getName())) {
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
