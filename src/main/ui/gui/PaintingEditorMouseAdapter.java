package ui.gui;

import model.Brush;
import model.Dot;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PaintingEditorMouseAdapter extends MouseAdapter {
    private Brush currentBrush;
    private List<Dot> currentPath;
    private List<List<Dot>> allPaths;
    private PaintingEditor editor;

    PaintingEditorMouseAdapter(Brush currentBrush, List<Dot> currentPath,
                               List<List<Dot>> allPaths, PaintingEditor editor) {
        super();
        this.currentBrush = currentBrush;
        this.currentPath = currentPath;
        this.allPaths = allPaths;
        this.editor = editor;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int red = currentBrush.getRed();
        int green = currentBrush.getGreen();
        int blue = currentBrush.getBlue();
        int brushSize = currentBrush.getSize();
        Color brushColor = new Color(red, green, blue);

        Graphics dot = editor.getGraphics();
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

        Graphics2D line = (Graphics2D) editor.getGraphics();
        line.setColor(brushColor);

        Dot previousDot = currentPath.get(currentPath.size() - 1);
        line.setStroke(new BasicStroke(brushSize));
        line.drawLine(previousDot.getXcoord(), previousDot.getYcoord(), x, y);

        line.dispose();
        currentPath.add(new Dot(brushColor, e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        allPaths.add(currentPath);
        currentPath = null;
    }
}
