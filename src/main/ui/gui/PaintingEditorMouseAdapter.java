package ui.gui;

import model.Brush;
import model.Dot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

// referenced and inspired by https://www.youtube.com/watch?v=vcgeCYKdyC4 by "TapTap"
// represents a mouse adapter for painting editor, allows drawing
public class PaintingEditorMouseAdapter extends MouseAdapter {
    private Brush currentBrush;
    private List<Dot> currentPath;
    private List<List<Dot>> allPaths;
    private JComponent parent;

    // EFFECTS: creates PaintingEditorMouseAdapter
    PaintingEditorMouseAdapter(Brush currentBrush, List<Dot> currentPath,
                               List<List<Dot>> allPaths, JComponent parent) {
        super();
        this.currentBrush = currentBrush;
        this.currentPath = currentPath;
        this.allPaths = allPaths;
        this.parent = parent;
    }

    // EFFECTS: creates a dot at mouse x and y
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY() - 150;
        int red = currentBrush.getRed();
        int green = currentBrush.getGreen();
        int blue = currentBrush.getBlue();
        int brushSize = currentBrush.getSize();
        Color brushColor = new Color(red, green, blue);

        Graphics dot = parent.getGraphics();
        dot.setColor(brushColor);
        dot.fillRect(x - (brushSize / 2), y - (brushSize / 2), brushSize, brushSize);
        dot.dispose();

        currentPath = new ArrayList<>();
        currentPath.add(new Dot(brushColor, e.getX(), e.getY()));
        System.out.println("x: " + x + ", y: " + y);
    }

    // EFFECTS: creates a line at mouse x and y to release point
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY() - 150;
        int red = currentBrush.getRed();
        int green = currentBrush.getGreen();
        int blue = currentBrush.getBlue();
        int brushSize = currentBrush.getSize();
        Color brushColor = new Color(red, green, blue);

        Graphics2D line = (Graphics2D) parent.getGraphics();
        line.setColor(brushColor);

        Dot previousDot = currentPath.get(currentPath.size() - 1);
        line.setStroke(new BasicStroke(brushSize));
        line.drawLine(previousDot.getXcoord(), previousDot.getYcoord() - 150, x, y);

        line.dispose();
        currentPath.add(new Dot(brushColor, e.getX(), e.getY()));
    }

    // EFFECTS: adds brush stroke to list of all strokes
    @Override
    public void mouseReleased(MouseEvent e) {
        allPaths.add(currentPath);
        currentPath = null;
    }
}
