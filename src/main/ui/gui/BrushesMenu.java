package ui.gui;

import model.BrushesRoom;
import model.PencilCase;
import persistence.JsonReader;
import ui.FileHelper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BrushesMenu extends WindowGUI {
    protected static final int WIDTH = 300;
    protected static final int HEIGHT = 450;
    private JPanel brushesPanel;
    private GridLayout brushesPanelGrid = new GridLayout(0, 1);
    private final BrushesRoom brushesRoom = BrushesRoom.getBrushesRoom();
    private JsonReader jsonReader = new JsonReader(FileHelper.JSON_STORE_BR);

    BrushesMenu() {
        super(windowLabel);
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

    private void addBrushesPanel(BrushesMenu parent) {
        brushesPanel = new JPanel(brushesPanelGrid);
        addCases(brushesPanel);
        parent.add(brushesPanel);
    }

    // EFFECTS: add cases to GUI
    private void addCases(JPanel parent) {
        for (PencilCase pc : brushesRoom.getCases()) {
            brushesPanelGrid.setRows(brushesPanelGrid.getRows() + 1);
            parent.add(new PencilCaseButton(pc, this));
            System.out.println(pc);
        }
    }
}
