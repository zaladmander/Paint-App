package ui;

import model.Brush;
import model.Canvas;
import model.Dot;
import ui.tools.ColorSelecterTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PaintingEditor extends WindowGUI {
    //TODO: these will be inputs
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;

    private Brush currentBrush = new Brush(20, "Default", 0, 0, 0, 1);
    private List<Dot> currentPath;

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
        setVisible(true);
    }

    private void initializeToolbar() {
        JPanel toolBar = new JPanel();
        toolBar.setBackground(new Color(250, 250, 210));
        toolBar.setPreferredSize(new Dimension(100, 100));
        this.add(toolBar, BorderLayout.NORTH);
    }

    // EFFECTS: initializes all GUI components
    //private void initializeWindow() {
    //    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //    setSize(WIDTH, HEIGHT);
    //    setMinimumSize(new Dimension(WIDTH, HEIGHT));
    //    getContentPane().setBackground(new Color(160, 160, 160));
//
    //    // make the layers
    //    JLayeredPane layers = new JLayeredPane();
    //    //layers.setLayout(new BorderLayout());
    //    setVisible(true);
    //    this.add(layers);
//
    //    initializeToolBar(layers, 1);
    //    initializeDrawingSpace(layers, 0);
//
    //    JPanel test = new JPanel();
    //    test.setBackground(Color.CYAN);
    //    this.add(test, BorderLayout.CENTER);
//
    //    //setLocationRelativeTo(null);
//
    //    //initializeMouseInput(currentBrush);
    //}
//
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
    //private void initializeDrawingSpace(JComponent parent, int level) {
    //    JPanel drawingSpace = new JPanel();
    //    //drawingSpace.setBackground(new Color(255, 255, 255));
    //    drawingSpace.setBackground(BG_COLOR);
    //    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    //    parent.add(drawingSpace);
    //}

    // EFFECTS: initializes all mouse input for drawing on the canvas
    // referenced and inspired by https://www.youtube.com/watch?v=vcgeCYKdyC4 by "TapTap"
    private void initializeMouseInput(Brush currentBrush) {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int red = currentBrush.getRed();
                int green = currentBrush.getGreen();
                int blue = currentBrush.getBlue();
                int brushSize = currentBrush.getSize();
                Color brushColor = new Color(red, green, blue);

                Graphics dot = getGraphics();
                dot.setColor(brushColor);
                dot.fillRect(x - (brushSize / 2), y - (brushSize / 2), brushSize, brushSize);
                dot.dispose();

                currentPath = new ArrayList<>();
                currentPath.add(new Dot(brushColor, e.getX(), e.getY()));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int red = currentBrush.getRed();
                int green = currentBrush.getGreen();
                int blue = currentBrush.getBlue();
                int brushSize = currentBrush.getSize();
                Color brushColor = new Color(red, green, blue);

                Graphics2D line = (Graphics2D) getGraphics();
                line.setColor(brushColor);

                Dot previousDot = currentPath.get(currentPath.size() - 1);
                line.setStroke(new BasicStroke(brushSize));
                line.drawLine(previousDot.getXcoord(), previousDot.getYcoord(), x, y);

                line.dispose();
                currentPath.add(new Dot(brushColor, e.getX(), e.getY()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }
}
