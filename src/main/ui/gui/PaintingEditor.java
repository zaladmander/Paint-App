package ui.gui;

import model.Brush;
import model.Dot;
import ui.gui.tools.ColorSelecterTool;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PaintingEditor extends WindowGUI implements ActionListener, ChangeListener {
    //TODO: these will be inputs
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;

    private Brush currentBrush = new Brush(20, "Default", 0, 0, 0, 1);
    private List<Dot> currentPath;
    private List<List<Dot>> allPaths = new ArrayList<>();
    private JSlider slider;

    // EFFECTS: constructs a window, default brush, and JFrame, panels, entire GUI
    PaintingEditor() {
        super(windowLabel);
        initializeWindow(new BorderLayout(), new Color(160, 160, 160), WIDTH, HEIGHT);
    }

    // EFFECTS: returns currentBrush
    public Brush getCurrentBrush() {
        return currentBrush;
    }

    // EFFECTS: initialize window GUI frame
    @Override
    protected void initializeWindow(LayoutManager layout, Color color, int width, int height) {
        super.initializeWindow(layout, color, width, height);
        initializeToolbar();
        initializeMenuBar(this);
        initializeDrawingSpace();
        setVisible(true);

        initializeMouseInput(currentBrush);
    }

    private void initializeToolbar() {
        JPanel toolBar = new JPanel();
        toolBar.setBackground(new Color(250, 250, 210));
        toolBar.setPreferredSize(new Dimension(100, 100));

        addSlider(toolBar);
        new ColorSelecterTool(this, toolBar);

        this.add(toolBar, BorderLayout.NORTH);
    }

    private void addSlider(JComponent parent) {
        slider = new JSlider(0, 100);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(25);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);

        parent.add(slider);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        currentBrush.setSize(slider.getValue());
        System.out.println(slider.getValue());
    }

    //private void initializeToolBar(JLayeredPane parent, int level) {
    //    JPanel toolBar = new JPanel();
    //    //toolBar.setLayout(null);
    //    toolBar.setBackground(new Color(250, 250, 210));
    //    toolBar.setPreferredSize(new Dimension(100, 100));
//
    //    new ColorSelecterTool(this, toolBar);
//
    //    parent.add(toolBar, JLayeredPane.DEFAULT_LAYER);
    //}
//
    private void initializeDrawingSpace() {
        JPanel drawingSpace = new JPanel();
        drawingSpace.setBackground(BG_COLOR);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.add(drawingSpace, BorderLayout.CENTER);
    }

    // EFFECTS: initializes all mouse input for drawing on the canvas
    // referenced and inspired by https://www.youtube.com/watch?v=vcgeCYKdyC4 by "TapTap"
    private void initializeMouseInput(Brush currentBrush) {
        PaintingEditorMouseAdapter mouseAdapter;
        mouseAdapter = new PaintingEditorMouseAdapter(currentBrush, currentPath, allPaths, this);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }


}
