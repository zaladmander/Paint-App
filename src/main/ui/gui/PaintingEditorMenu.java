package ui.gui;

import model.Brush;
import model.Dot;
import ui.gui.tools.ColorSelecterTool;
import model.Canvas;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

// represents the painting window where users can draw things
public class PaintingEditorMenu extends WindowGUI implements ActionListener, ChangeListener {
    private Brush currentBrush = new Brush(20, "Default", 0, 0, 0, 1);
    private JSlider slider;
    private final int width;
    private final int height;
    private JPanel drawingSpace;
    private PaintingEditorMouseAdapter mouseAdapter;

    // EFFECTS: constructs a window, default brush, and JFrame, panels, entire GUI
    PaintingEditorMenu(Canvas canvas) {
        super(windowLabel);
        width = canvas.getWidth();
        height = canvas.getHeight();
        initializeWindow(new BorderLayout(), new Color(160, 160, 160), width, height);
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
        addWindowListener(this);
    }

    // EFFECTS: initialize a toolbar for the JFrame
    private void initializeToolbar() {
        JPanel toolBar = new JPanel();
        toolBar.setBackground(new Color(250, 250, 210));
        toolBar.setPreferredSize(new Dimension(100, 100));
        addSlider(toolBar);
        new ColorSelecterTool(this, toolBar);
        this.add(toolBar, BorderLayout.NORTH);
    }

    // EFFECTS: adds a slider bar to the JFrame
    private void addSlider(JComponent parent) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setOpaque(false);
        JLabel text = new JLabel("Brush Size");
        text.setFont(new Font("Spectral", Font.BOLD, 15));
        text.setHorizontalAlignment(JLabel.CENTER);
        slider = new JSlider(0, 200);
        slider.setValue(currentBrush.getSize());
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(50);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        panel.add(text);
        panel.add(slider);
        parent.add(panel);
    }

    // EFFECTS: initializes the drawing space
    private void initializeDrawingSpace() {
        drawingSpace = new JPanel();
        drawingSpace.setBackground(BG_COLOR);
        setPreferredSize(new Dimension(width, height));
        this.add(drawingSpace, BorderLayout.CENTER);
    }

    // EFFECTS: initializes all mouse input for drawing on the canvas
    // referenced and inspired by https://www.youtube.com/watch?v=vcgeCYKdyC4 by "TapTap"
    public void initializeMouseInput(Brush currentBrush) {
        removeMouseListener(mouseAdapter);
        removeMouseMotionListener(mouseAdapter);
        mouseAdapter = new PaintingEditorMouseAdapter(currentBrush, drawingSpace);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    // EFFECTS: sets currentBrush to given brush
    public void setCurrentBrush(Brush brush) {
        this.currentBrush = brush;
    }

    // EFFECTS: returns currentBrush
    public Brush getCurrentBrush() {
        return currentBrush;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        currentBrush.setSize(slider.getValue());
    }

    @Override
    public void windowIconified(WindowEvent e) {
        mouseAdapter.redrawAllPaths();

    }
}
