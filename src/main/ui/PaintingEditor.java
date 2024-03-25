package ui;

import javax.swing.*;
import java.awt.*;

public class PaintingEditor extends JFrame {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    PaintingEditor() {
        super("Paint!");
        initializeWindow();
    }

    private void initializeWindow() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
